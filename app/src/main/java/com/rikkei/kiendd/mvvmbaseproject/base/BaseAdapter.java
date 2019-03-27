package com.rikkei.kiendd.mvvmbaseproject.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T extends ViewDataBinding, V extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    protected T binding;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), parent, false);
        return onActualCreateViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        onActualBindViewHolder();
    }

    public abstract int getLayoutId();

    public abstract BaseViewHolder onActualCreateViewHolder();

    public abstract void onActualBindViewHolder();
}
