package com.rikkei.kiendd.mvvmbaseproject.view.home;

import android.os.Bundle;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.view.detail.DetailFragment;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.FragmentHomeViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private FragmentHomeViewModel homeViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(FragmentHomeViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();



        binding.btnNext.setOnClickListener(v -> {
            if (mViewController != null) {
                mViewController.addFragment(DetailFragment.class, null);
            }
        });
    }

    @Override
    public void backFromAddFragment() {

    }
}
