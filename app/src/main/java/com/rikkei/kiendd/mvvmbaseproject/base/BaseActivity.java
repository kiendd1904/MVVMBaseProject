package com.rikkei.kiendd.mvvmbaseproject.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.data.network.NetworkCheckerInterceptor;
import com.rikkei.kiendd.mvvmbaseproject.data.network.model.ApiObjectResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.network.model.RequestError;
import com.rikkei.kiendd.mvvmbaseproject.ui.ViewController;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.utils.RxBus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {

    protected T binding;

    protected ViewController mViewController;

    public static long lastClickTime = System.currentTimeMillis();

    private View focusedViewOnActionDown;
    private boolean touchWasInsideFocusedView, hasMove;
    private float rawX, rawY;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewController = new ViewController(getSupportFragmentManager(), getFragmentContainerId());

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        RxBus.getInstance().subscribe(expiredToken);
    }

    public abstract int getLayoutId();

    public abstract int getFragmentContainerId();

    @Override
    public void onBackPressed() {
        if (mViewController != null && mViewController.getCurrentFragment() != null) {
            if (mViewController.getCurrentFragment().backPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Used for hide softKeyboard when touch outside
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = ev.getRawX();
                rawY = ev.getRawY();
                hasMove = false;
                focusedViewOnActionDown = getCurrentFocus();
                if (focusedViewOnActionDown != null) {
                    final Rect rect = new Rect();
                    final int[] coordinates = new int[2];

                    focusedViewOnActionDown.getLocationOnScreen(coordinates);

                    rect.set(coordinates[0], coordinates[1],
                            coordinates[0] + focusedViewOnActionDown.getWidth(),
                            coordinates[1] + focusedViewOnActionDown.getHeight());

                    final int x = (int) ev.getX();
                    final int y = (int) ev.getY();

                    touchWasInsideFocusedView = rect.contains(x, y);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (!hasMove) {
                    float delta = (float) Math.hypot(rawX - ev.getRawX(), rawY - ev.getRawY());
                    hasMove = delta > 6f;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (focusedViewOnActionDown != null) {
                    final boolean consumed = super.dispatchTouchEvent(ev);
                    final View currentFocus = getCurrentFocus();
                    if (hasMove) {
                        return consumed;
                    }
                    if (currentFocus.equals(focusedViewOnActionDown)) {
                        if (touchWasInsideFocusedView) {
                            return consumed;
                        }
                    } else if (currentFocus instanceof EditText) {
                        return consumed;
                    }

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(focusedViewOnActionDown.getWindowToken(), 0);
                    return consumed;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private Consumer<String> expiredToken = value -> {
        if (value.equals(Define.Bus.ACCESS_TOKEN_EXPIRED)) {
            Log.d("TOKEN_EXPIRED", BaseActivity.this.getClass().getName());
            runOnUiThread(() -> {
                //showDialogAccessTokenExpired();
            });
        }
    };

    @Nullable
    public RequestError handleNetworkError(Throwable throwable, boolean isShowDialog) {
        RequestError requestError = new RequestError();

        if (throwable instanceof NetworkCheckerInterceptor.NoConnectivityException) {
            requestError.setErrorCode(Define.Api.Error.NO_NETWORK_CONNECTION);
            requestError.setErrorMessage(throwable.getMessage());
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            String errorBody;
            try {
                errorBody = Objects.requireNonNull(httpException.response().errorBody()).string();
                Gson gson = new GsonBuilder().create();
                ApiObjectResponse apiResponse = gson.fromJson(errorBody, ApiObjectResponse.class);
                if (apiResponse != null && apiResponse.getRequestError() != null) {
                    requestError = apiResponse.getRequestError();
                } else {
                    requestError.setErrorCode(String.valueOf(httpException.code()));
                    requestError.setErrorMessage(getString(R.string.message_login_failed));
                }
            } catch (IOException e) {
                requestError.setErrorCode(Define.Api.Error.TIME_OUT);
                requestError.setErrorMessage(getString(R.string.message_login_failed));
            } catch (IllegalStateException e) {
                requestError.setErrorCode(Define.Api.Error.TIME_OUT);
                requestError.setErrorMessage(getString(R.string.message_login_failed));
            } catch (JsonSyntaxException e) {
                requestError.setErrorCode(Define.Api.Error.TIME_OUT);
                requestError.setErrorMessage(getString(R.string.message_login_failed));
            }
        } else if (throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException
                || throwable instanceof IOException) {
            requestError.setErrorCode(Define.Api.Error.TIME_OUT);
            requestError.setErrorMessage(getString(R.string.message_login_failed));
        } else {
            requestError.setErrorCode(Define.Api.Error.UNKNOWN);
            requestError.setErrorMessage(getString(R.string.message_login_failed));
        }

        if (isShowDialog && requestError != null) {
            Toast.makeText(this, requestError.getErrorMessage(), Toast.LENGTH_LONG).show();
        }

        return requestError;
    }

    protected boolean avoidDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < Define.CLICK_TIME_INTERVAL) {
            return true;
        }
        lastClickTime = now;
        return false;
    }

    public abstract void initView();

    public abstract void initData();
}
