package com.rikkei.kiendd.mvvmbaseproject.ui.login;

import android.os.Handler;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentLoginBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.home.HomeFragment;
import com.rikkei.kiendd.mvvmbaseproject.utils.ActivityUtils;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> {
            if (getFragmentManager() != null) {
                ActivityUtils.popAllFragment(getFragmentManager());
                ActivityUtils.pushFragment(getFragmentManager(), new HomeFragment());
            }
        }, 0);
    }
}
