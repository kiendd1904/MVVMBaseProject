package com.rikkei.kiendd.mvvmbaseproject.adapter.viewholder;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;

import androidx.databinding.ViewDataBinding;

public class ListRepoViewHolder<T extends ViewDataBinding> extends BaseViewHolder<Repo> {

    private T binding;

    public ListRepoViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    public void bind(Repo data) {

    }
}
