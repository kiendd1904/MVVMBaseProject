package com.rikkei.kiendd.mvvmbaseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.viewholder.RepoViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import java.util.List;

public class CommonListAdapter<T> extends BaseAdapter<T> {

    private int listType;

    public CommonListAdapter(Context context, List<T> mListItems, int listType) {
        super(context, mListItems);
        this.listType = listType;
    }

    @Override
    public BaseViewHolder<T> onActualCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (listType) {
            case Define.ItemListType.REPO:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_repo, parent, false);
                return (BaseViewHolder<T>) new RepoViewHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_repo, parent, false);
                return (BaseViewHolder<T>) new RepoViewHolder(view);
        }
    }

    @Override
    public void onActualBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        holder.bind(mListItems.get(position));
    }
}
