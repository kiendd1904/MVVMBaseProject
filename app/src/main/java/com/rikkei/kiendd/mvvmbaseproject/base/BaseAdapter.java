package com.rikkei.kiendd.mvvmbaseproject.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected Context mContext;

    protected List<T> mListItems;

    protected OnItemClickListener mListener;

    public BaseAdapter(Context context, List<T> mListItems) {
        this.mContext = context;
        this.mListItems = mListItems;
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onActualCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        T data = mListItems.get(position);
        holder.bind(data);
        holder.setListener(mListener);
        onActualBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public void setData(List<T> data) {
        this.mListItems = new ArrayList<>(data);
    }

    public void addItems(List<T> items) {
        this.mListItems.addAll(new ArrayList<>(items));
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mListItems.get(position);
    }

    public List<T> getmListItems() {
        return mListItems;
    }

    public abstract BaseViewHolder<T> onActualCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onActualBindViewHolder(@NonNull BaseViewHolder<T> holder, int position);

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onClickItem(int position, T item);
    }
}
