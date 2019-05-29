package com.rikkei.kiendd.mvvmbaseproject.ui.main;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseActivity;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ActivityMainBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.splash.SplashFragment;
import com.rikkei.kiendd.mvvmbaseproject.utils.ActivityUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.flMainContainer;
    }

    @Override
    protected void onStart() {
        super.onStart();

        initView();
    }

    public void initView() {
        ActivityUtils.pushFragment(getSupportFragmentManager(), new SplashFragment());
    }
}
