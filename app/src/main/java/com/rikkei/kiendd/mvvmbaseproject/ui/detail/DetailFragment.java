package com.rikkei.kiendd.mvvmbaseproject.ui.detail;

import android.os.Bundle;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentDetailBinding;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {

    private DetailViewModel detailViewModel;

    private String repoName;
    private String repoOwner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle.containsKey(Define.Intent.REPO_OWNER)) {
                repoOwner = bundle.getString(Define.Intent.REPO_OWNER);
            }

            if (bundle.containsKey(Define.Intent.REPO_NAME)) {
                repoName = bundle.getString(Define.Intent.REPO_NAME);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        detailViewModel.loadRepoDetail(repoOwner, repoName);
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        mViewController.backFromAddFragment(null);
        return false;
    }
}
