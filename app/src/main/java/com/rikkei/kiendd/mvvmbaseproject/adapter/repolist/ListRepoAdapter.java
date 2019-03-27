package com.rikkei.kiendd.mvvmbaseproject.adapter.repolist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ItemRepoBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

public class ListRepoAdapter extends BaseAdapter<ItemRepoBinding, ListRepoViewHolder> {

    private List<Repo> repoList = new ArrayList<>();
    private ItemClickListener listener;

    public ListRepoAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ListRepoViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo, parent, false);
        return new ListRepoViewHolder(binding, repo -> listener.onCLick(repo));
    }

    @Override
    public void onActualBindViewHolder(ListRepoViewHolder holder, int position) {
        holder.bind(repoList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = new ArrayList<>(repoList);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onCLick(Repo repo);
    }
}
