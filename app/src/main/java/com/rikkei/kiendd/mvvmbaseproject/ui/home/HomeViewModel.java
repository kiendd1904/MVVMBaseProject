package com.rikkei.kiendd.mvvmbaseproject.ui.home;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewModel;
import com.rikkei.kiendd.mvvmbaseproject.data.repository.RepoRepository;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    private RepoRepository repoRepository;

    @Inject
    HomeViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }
}
