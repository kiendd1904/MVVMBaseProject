package com.rikkei.kiendd.mvvmbaseproject.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T extends ViewDataBinding, V extends BaseViewHolder, D> extends RecyclerView.Adapter<V> {

    protected T binding;

    protected List<D> dataList;

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), parent, false);
        return onActualCreateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        onActualBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<D> data) {
        this.dataList = new ArrayList<>(data);
    }

    public void addItems(List<D> items) {
        this.dataList.addAll(new ArrayList<>(items));
        this.notifyDataSetChanged();
    }

    public D getItem(int position) {
        return dataList.get(position);
    }

    public abstract int getLayoutId();

    public abstract V onActualCreateViewHolder(T binding);

    public abstract void onActualBindViewHolder(V holder, int position);
}
