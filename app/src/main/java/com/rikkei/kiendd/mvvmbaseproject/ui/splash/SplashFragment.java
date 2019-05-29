package com.rikkei.kiendd.mvvmbaseproject.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.databinding.FragmentSplashBinding;
import com.rikkei.kiendd.mvvmbaseproject.ui.login.LoginFragment;
import com.rikkei.kiendd.mvvmbaseproject.utils.ActivityUtils;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToLogin();
    }

    private void goToLogin() {
        new Handler().postDelayed(() -> {
            if (getFragmentManager() != null) {
                ActivityUtils.popAllFragment(getFragmentManager());
                ActivityUtils.pushFragment(getFragmentManager(), new LoginFragment());
            }
        }, 1000);
    }
}
