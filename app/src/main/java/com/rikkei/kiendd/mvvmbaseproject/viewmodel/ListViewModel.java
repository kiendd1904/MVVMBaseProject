package com.rikkei.kiendd.mvvmbaseproject.viewmodel;

import com.rikkei.kiendd.mvvmbaseproject.data.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.data.repository.RepoRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class ListViewModel extends ViewModel {

    private final RepoRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<ListResponse<Repo>> loadRepos = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @Inject
    public ListViewModel(RepoRepository repository) {
        this.repoRepository = repository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    public MutableLiveData<ListResponse<Repo>> getLoadRepos() {
        return loadRepos;
    }

    private void fetchRepos() {

    }
}
