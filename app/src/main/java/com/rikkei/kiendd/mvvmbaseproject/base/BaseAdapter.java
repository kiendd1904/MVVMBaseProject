package com.rikkei.kiendd.mvvmbaseproject.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T extends ViewDataBinding, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    protected T binding;

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onActualCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        onActualBindViewHolder(holder, position);
    }

    public abstract V onActualCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onActualBindViewHolder(V holder, int position);
}
