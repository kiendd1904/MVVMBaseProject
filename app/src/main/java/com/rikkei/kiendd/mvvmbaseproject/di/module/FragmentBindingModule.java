package com.rikkei.kiendd.mvvmbaseproject.di.module;

import com.rikkei.kiendd.mvvmbaseproject.ui.detail.DetailFragment;
import com.rikkei.kiendd.mvvmbaseproject.ui.home.HomeFragment;
import com.rikkei.kiendd.mvvmbaseproject.ui.login.LoginFragment;
import com.rikkei.kiendd.mvvmbaseproject.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract DetailFragment bindDetailFragment();
}
