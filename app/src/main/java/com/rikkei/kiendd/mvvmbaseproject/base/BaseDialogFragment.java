package com.rikkei.kiendd.mvvmbaseproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.utils.UIUtil;

public abstract class BaseDialogFragment<V extends ViewDataBinding, T> extends DialogFragment {

    protected V binding;
    protected DialogListener<T> acceptListener;
    protected DialogListener<T> cancelListener;

    protected float dialogWidthRatio = 0.8f;
    protected float dialogHeightRatio = 0.48f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        setDialogDimension();

        // Set background & dimensions for dialog
        if (getContext() != null && getDialog() != null && getDialog().getWindow() != null) {

            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);

            getDialog().getWindow().setLayout(
                    (int) (UIUtil.widthScreenPixel(getContext()) * dialogWidthRatio),
                    (int) (UIUtil.heightScreenPixel(getContext()) * dialogHeightRatio));
        }

        initView();
        initListener();
    }

    public void show(FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            show(fragmentManager, null);
        }
    }

    protected void setDialogDimension() {
        dialogWidthRatio = 0.8f;
        dialogHeightRatio = 0.48f;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    public BaseDialogFragment setAcceptListener(DialogListener<T> listener) {
        acceptListener = listener;
        return this;
    }

    public BaseDialogFragment setCancelListener(DialogListener<T> listener) {
        cancelListener = listener;
        return this;
    }

    public interface DialogListener<T> {
        void onClick(T data);
    }
}
