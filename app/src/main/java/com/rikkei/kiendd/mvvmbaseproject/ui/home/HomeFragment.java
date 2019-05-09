package com.rikkei.kiendd.mvvmbaseproject.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.repolist.ListRepoAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.detail.DetailFragment;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.utils.DialogUtil;

import java.util.HashMap;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    @Inject
    HomeViewModel homeViewModel;

    private ListRepoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
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
        homeViewModel.fetchRepos();
        homeViewModel.getLoadRepos().observe(getViewLifecycleOwner(), this::fetchReposHandle);
    }

    private void fetchReposHandle(ListResponse<Repo> repoListResponse) {
        switch (repoListResponse.getStatus()) {
            case Define.ResponseStatus.LOADING:
                DialogUtil.getInstance(getContext()).show();
                break;
            case Define.ResponseStatus.SUCCESS:
                adapter.setData(repoListResponse.getData());
                binding.rvRepos.setAdapter(adapter);
                DialogUtil.getInstance(getContext()).hidden();
                break;
            case Define.ResponseStatus.ERROR:
                DialogUtil.getInstance(getContext()).hidden();
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
