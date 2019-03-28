package com.rikkei.kiendd.mvvmbaseproject.ui.main;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseActivity;
import com.rikkei.kiendd.mvvmbaseproject.databinding.ActivityMainBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.home.HomeFragment;

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
    protected void onStart() {
        super.onStart();

        initView();
        initData();
    }

    private void initView() {
        if (mViewController != null) {
            mViewController.addFragment(HomeFragment.class, null);
        }
    }

    private void initData() {
    }
}
