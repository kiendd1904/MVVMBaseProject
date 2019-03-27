package com.rikkei.kiendd.mvvmbaseproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.viewholder.ListRepoViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ItemRepoBinding;

import androidx.databinding.DataBindingUtil;

public class ListRepoAdapter extends BaseAdapter<ItemRepoBinding, ListRepoViewHolder> {

    @Override
    public ListRepoViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo, parent, false);
        return new ListRepoViewHolder<>(binding);
    }

    @Override
    public void onActualBindViewHolder(ListRepoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
