package com.rikkei.kiendd.mvvmbaseproject.view.detail;

import android.os.Bundle;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentDetailBinding;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.FragmentDetailViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {

    private FragmentDetailViewModel detailViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(FragmentDetailViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();

        binding.btnBack.setOnClickListener(v -> {
            if (mViewController != null) {
                mViewController.backFromAddFragment(null);
            }
        });
    }

    @Override
    public void backFromAddFragment() {

    }
}
