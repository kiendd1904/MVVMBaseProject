package com.rikkei.kiendd.mvvmbaseproject.view.home;

import android.os.Bundle;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentHomeBinding;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.HomeViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }
}
