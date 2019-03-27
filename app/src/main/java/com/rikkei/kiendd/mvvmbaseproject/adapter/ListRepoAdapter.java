package com.rikkei.kiendd.mvvmbaseproject.adapter;

import android.view.View;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ItemRepoBinding;

import androidx.annotation.NonNull;

public class ListRepoAdapter extends BaseAdapter<ItemRepoBinding, ListRepoAdapter.ListRepoVH> {
    @Override
    public int getLayoutId() {
        return R.layout.item_repo;
    }

    @Override
    public BaseViewHolder onActualCreateViewHolder() {
        return null;
    }

    @Override
    public void onActualBindViewHolder() {

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListRepoVH extends BaseViewHolder {

        public ListRepoVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
