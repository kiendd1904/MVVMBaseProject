package com.rikkei.kiendd.mvvmbaseproject.ui.home;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewModel;
import com.rikkei.kiendd.mvvmbaseproject.base.ListResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.data.pref.AppPreferences;
import com.rikkei.kiendd.mvvmbaseproject.data.repository.RepoRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class ListViewModel extends BaseViewModel {

    private final RepoRepository repoRepository;

    private final AppPreferences preferences;

    private final MutableLiveData<ListResponse<Repo>> loadRepos = new MutableLiveData<>();

    @Inject
    ListViewModel(RepoRepository repository, AppPreferences preferences) {
        this.repoRepository = repository;
        this.preferences = preferences;
        mDisposable = new CompositeDisposable();
    }

    public MutableLiveData<ListResponse<Repo>> getLoadRepos() {
        return loadRepos;
    }

    public void fetchRepos() {
        mDisposable.add(
                repoRepository.getRepositories()
                        .doOnSubscribe(it -> {
                            loadRepos.setValue(new ListResponse<Repo>().loading());
                        })
                        .subscribe(repoList -> {
                            loadRepos.setValue(new ListResponse<Repo>().success(repoList));
                        }, throwable -> {
                            loadRepos.setValue(new ListResponse<Repo>().error(throwable));
                        })
        );
    }
}
