package com.rikkei.kiendd.mvvmbaseproject.ui.detail;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentDetailBinding;

public class DetailFragment extends BaseFragment<FragmentDetailBinding, DetailViewModel> {

    private Repo repo;

    public static DetailFragment newInstance(Repo item) {
        DetailFragment fragment = new DetailFragment();
        fragment.repo = item;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected Class<DetailViewModel> getViewModelClass() {
        return DetailViewModel.class;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public void initData() {
        viewModel.loadRepoDetail(repo);
    }
}
