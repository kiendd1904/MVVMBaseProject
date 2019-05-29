package com.rikkei.kiendd.mvvmbaseproject.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rikkei.kiendd.mvvmbaseproject.utils.UIUtil;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected T mData;

    protected BaseAdapter.OnItemClickListener mListener;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setListener(BaseAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public void bind(T data) {
        mData = data;
    }

    @Override
    public void onClick(View v) {
        if (!UIUtil.isClickable()) {
            return;
        }

        if (mListener != null) {
            mListener.onClickItem(getAdapterPosition(), mData);
        }
    }
}
