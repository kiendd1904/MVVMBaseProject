package com.rikkei.kiendd.mvvmbaseproject.ui.splash;

import android.os.Handler;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentSplashBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.login.LoginFragment;

public class SplashFragment extends BaseFragment<FragmentSplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> mViewController.replaceFragment(LoginFragment.class, null), 1000);
    }
}
