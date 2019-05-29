package com.rikkei.kiendd.mvvmbaseproject.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.ui.ViewController;
import com.rikkei.kiendd.mvvmbaseproject.utils.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends ViewModel> extends DaggerFragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    protected T binding;

    protected V viewModel;

    /**
     * The ViewController for control fragments in an activity
     */
    @Nullable
    protected ViewController mViewController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initObserver();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract Class<V> getViewModelClass();

    public void setViewController(ViewController viewController) {
        this.mViewController = viewController;
    }

    public void setData(HashMap<String, Object> data) {
        if (data == null || data.isEmpty()) {
            setArguments(new Bundle());
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String) {
                bundle.putString(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                bundle.putDouble(entry.getKey(), ((Double) entry.getValue()));
            } else if (entry.getValue() instanceof Integer) {
                bundle.putInt(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Float) {
                bundle.putFloat(entry.getKey(), ((Float) entry.getValue()));
            } else if (entry.getValue() instanceof Boolean) {
                bundle.putBoolean(entry.getKey(), ((Boolean) entry.getValue()));
            } else if (entry.getValue() instanceof Parcelable) {
                bundle.putParcelable(entry.getKey(), ((Parcelable) entry.getValue()));
            } else if (entry.getValue() instanceof String[]) {
                bundle.putStringArray(entry.getKey(), (String[]) entry.getValue());
            } else if (entry.getValue() instanceof ArrayList) {
                if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof String) {
                    bundle.putStringArrayList(entry.getKey(), (ArrayList<String>) entry.getValue());
                } else if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof Parcelable) {
                    bundle.putParcelableArrayList(entry.getKey(), (ArrayList<? extends Parcelable>) entry.getValue());
                }
            }
        }
        setArguments(bundle);
    }

    protected void handleNetworkError(Throwable throwable, boolean isShowDialog) {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).handleNetworkError(throwable, isShowDialog);
        }
    }

    protected boolean avoidDuplicateClick() {

        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).avoidDuplicateClick();
        }

        return false;
    }

    /**
     * This func has call when pressed back button on device.
     *
     * @return True if need destroy Activity
     */
    protected void backPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    public void backFromAddFragment() {
    }

    protected void showLoading() {
        DialogUtil.getInstance(getContext()).showLoading();
    }

    protected void hideLoading() {
        DialogUtil.getInstance(getContext()).hideLoading();
    }

    public void showErrorDialog(String message) {
        if (getUserVisibleHint()) {
            DialogUtil.getInstance(getContext())
                    .setMessage(message)
                    .setPositiveButton(R.string.btn_accept, null)
                    .showError();
        }
    }

    public void showErrorDialog(int messageRes) {
        showErrorDialog(getString(messageRes));
    }

    public void showErrorDialog(String title, String message) {
        if (getUserVisibleHint())
            DialogUtil.getInstance(getContext())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(R.string.btn_accept, null)
                    .showError();
    }

    public void showDialog(String message, boolean isCancel, DialogUtil.OnDialogListener listener) {
        if (getUserVisibleHint()) {
            DialogUtil.getInstance(getContext())
                    .setMessage(message)
                    .setPositiveButton(R.string.btn_accept, listener)
                    .setCancelable(isCancel)
                    .showError();
        }
    }

    public void showDialogConfirm(String title, String message, boolean isCancel, DialogUtil.OnDialogListener listener, DialogUtil.OnDialogListener cancelListener) {
        if (getUserVisibleHint()) {
            DialogUtil.getInstance(getContext())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(R.string.btn_accept, listener)
                    .setNegativeButton(R.string.btn_cancel, cancelListener)
                    .setCancelable(isCancel)
                    .showError();
        }
    }

    public abstract void initView();

    public abstract void initObserver();

    public abstract void initData();
}
