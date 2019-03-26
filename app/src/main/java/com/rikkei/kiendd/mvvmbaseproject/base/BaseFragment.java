package com.rikkei.kiendd.mvvmbaseproject.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rikkei.kiendd.mvvmbaseproject.view.ViewController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    protected T binding;

    /**The ViewController for control fragments in an activity
     */
    @Nullable
    protected ViewController mViewController;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    protected abstract int getLayoutId();

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

    public abstract void backFromAddFragment();
}
