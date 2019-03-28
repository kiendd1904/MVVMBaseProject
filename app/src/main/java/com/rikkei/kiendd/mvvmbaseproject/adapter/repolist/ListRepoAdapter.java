package com.rikkei.kiendd.mvvmbaseproject.adapter.repolist;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ItemRepoBinding;

public class ListRepoAdapter extends BaseAdapter<ItemRepoBinding, ListRepoViewHolder, Repo> {

    private ItemClickListener listener;

    public ListRepoAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_repo;
    }

    @Override
    public ListRepoViewHolder onActualCreateViewHolder(ItemRepoBinding binding) {
        return new ListRepoViewHolder(binding, repo -> listener.onCLick(repo));
    }

    @Override
    public void onActualBindViewHolder(ListRepoViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    public interface ItemClickListener {
        void onCLick(Repo repo);
    }
}
