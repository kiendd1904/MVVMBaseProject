package com.rikkei.kiendd.mvvmbaseproject.di.module;

import com.rikkei.kiendd.mvvmbaseproject.di.ViewModelFactory;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.FragmentDetailViewModel;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.FragmentHomeViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FragmentHomeViewModel.class)
    abstract ViewModel bindFragmentHomeViewModel(FragmentHomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FragmentDetailViewModel.class)
    abstract ViewModel bindFragmentDetailViewModel(FragmentDetailViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
