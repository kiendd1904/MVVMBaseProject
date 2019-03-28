package com.rikkei.kiendd.mvvmbaseproject.adapter.repolist;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ItemRepoBinding;

public class ListRepoViewHolder extends BaseViewHolder<Repo> {

    private ItemRepoBinding binding;
    private Repo repo;

    ListRepoViewHolder(ItemRepoBinding binding, ListRepoAdapter.ItemClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(v -> listener.onCLick(repo));
    }

    @Override
    public void bind(Repo data) {
        this.repo = data;
        binding.tvRepoName.setText(data.getName());
    }
}
