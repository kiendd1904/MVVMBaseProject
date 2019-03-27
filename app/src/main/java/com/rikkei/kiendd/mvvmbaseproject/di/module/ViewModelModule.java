package com.rikkei.kiendd.mvvmbaseproject.di.module;

import com.rikkei.kiendd.mvvmbaseproject.di.ViewModelFactory;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.DetailViewModel;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.HomeViewModel;
import com.rikkei.kiendd.mvvmbaseproject.viewmodel.ListViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
