package com.rikkei.kiendd.mvvmbaseproject.ui.main;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseActivity;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ActivityMainBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.home.HomeFragment;
import com.rikkei.kiendd.mvvmbaseproject.ui.splash.SplashFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.flMainContainer;
    }

    @Override
    public void initView() {
        mViewController.addFragment(SplashFragment.class, null);
    }

    @Override
    public void initData() {

    }
}
