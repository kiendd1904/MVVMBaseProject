package com.rikkei.kiendd.mvvmbaseproject.ui.home;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.adapter.CommonListAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseAdapter;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.detail.DetailFragment;
import com.rikkei.kiendd.mvvmbaseproject.utils.ActivityUtils;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements BaseAdapter.OnItemClickListener<Repo> {

    private CommonListAdapter<Repo> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public void initData() {
        fetchRepos();
    }

    private void fetchRepos() {
        viewModel.fetchRepos();
        viewModel.getLoadRepos().observe(getViewLifecycleOwner(), this::fetchReposHandle);
    }

    private void fetchReposHandle(ListResponse<Repo> repoListResponse) {
        switch (repoListResponse.getStatus()) {
            case Define.ResponseStatus.LOADING:
                showLoading();
                break;
            case Define.ResponseStatus.SUCCESS:
                adapter = new CommonListAdapter<>(getContext(), repoListResponse.getData(), Define.ItemListType.REPO);
                adapter.setOnItemClickListener(this);
                binding.rvRepos.setAdapter(adapter);
                hideLoading();
                break;
            case Define.ResponseStatus.ERROR:
                hideLoading();
        }
    }

    @Override
    public void onClickItem(int position, Repo item) {
        if (getFragmentManager() != null) {
            ActivityUtils.pushFragment(getFragmentManager(), DetailFragment.newInstance(item));
        }
    }
}
