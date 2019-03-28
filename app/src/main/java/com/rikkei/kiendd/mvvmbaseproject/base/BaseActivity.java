package com.rikkei.kiendd.mvvmbaseproject.base;

import android.os.Bundle;
import android.util.Log;

import com.rikkei.kiendd.mvvmbaseproject.ui.ViewController;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.utils.rx.RxBus;

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

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {

    protected T binding;

    protected ViewController mViewController;

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

    private Consumer<String> expiredToken = value -> {
        if (value.equals(Define.Bus.ACCESS_TOKEN_EXPIRED)) {
            Log.d("TOKEN_EXPIRED", BaseActivity.this.getClass().getName());
            runOnUiThread(() -> {
                //showDialogAccessTokenExpired();
            });
        }
    };
}
