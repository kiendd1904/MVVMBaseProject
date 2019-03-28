package com.rikkei.kiendd.mvvmbaseproject.ui.home;

import android.os.Bundle;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.repolist.ListRepoAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.ui.detail.DetailFragment;

import java.util.HashMap;

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
    public void onStart() {
        super.onStart();

        fetchRepos();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        adapter = new ListRepoAdapter(repo -> {
            if (mViewController != null) {
                HashMap<String, Object> data = new HashMap<>();
                data.put(Define.Intent.REPO_OWNER, repo.getFullName());
                data.put(Define.Intent.REPO_NAME, repo.getName());

                mViewController.addFragment(DetailFragment.class, data);
            }
        });
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
                adapter.setData(repoListResponse.getData());
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
