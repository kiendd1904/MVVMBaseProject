package com.rikkei.kiendd.mvvmbaseproject.view.home;

import android.os.Bundle;
import android.view.View;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.repolist.ListRepoAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.view.detail.DetailFragment;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.HomeViewModel;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.ListViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;
    private ListViewModel listViewModel;
    private ListRepoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initData();

        fetchRepos();
    }

    private void initData() {
        adapter = new ListRepoAdapter(repo -> {
            if (mViewController != null) {
                mViewController.addFragment(DetailFragment.class, null);
            }
        });
    }

    private void initView() {

    }

    private void fetchRepos() {
        listViewModel.fetchRepos();
        listViewModel.getLoadRepos().observe(this, this::fetchReposHandle);
    }

    private void fetchReposHandle(ListResponse<Repo> repoListResponse) {
        switch (repoListResponse.getStatus()) {
            case Define.ResponseStatus.LOADING:
                //TODO: Show Loading
                break;
            case Define.ResponseStatus.SUCCESS:
                adapter.setRepoList(repoListResponse.getData());
                binding.rvRepos.setAdapter(adapter);
                break;
            case Define.ResponseStatus.ERROR:
                //TODO: Hide Loading, show Error
        }
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }
}
