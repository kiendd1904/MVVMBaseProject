package com.rikkei.kiendd.mvvmbaseproject.di.module;

import com.rikkei.kiendd.mvvmbaseproject.view.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();
}
