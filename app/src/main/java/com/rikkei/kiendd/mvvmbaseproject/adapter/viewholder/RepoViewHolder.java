package com.rikkei.kiendd.mvvmbaseproject.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewHolder;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;

import butterknife.BindView;

public class RepoViewHolder extends BaseViewHolder<Repo> {

    private Repo repo;

    @BindView(R.id.tvRepoName)
    TextView tvRepoName;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Repo data) {
        super.bind(data);
        this.repo = data;
        tvRepoName.setText(data.getName());
    }
}
