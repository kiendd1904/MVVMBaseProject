package com.rikkei.kiendd.mvvmbaseproject.viewmodel;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewModel;
import com.rikkei.kiendd.mvvmbaseproject.base.ObjectResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.data.repository.RepoRepository;
import com.rikkei.kiendd.mvvmbaseproject.utils.StringUtil;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

public class DetailViewModel extends BaseViewModel {

    private RepoRepository repoRepository;

    private final MutableLiveData<ObjectResponse<Repo>> loadRepoDetail = new MutableLiveData<>();

    @Inject
    DetailViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public void loadRepoDetail(String owner, String name) {
        if (StringUtil.isEmpty(owner, name)) {
            return;
        }

        mDisposable.add(
                repoRepository.getRepo(owner, name)
                .doOnSubscribe(it -> loadRepoDetail.setValue(new ObjectResponse<Repo>().loading()))
                .subscribe(repo -> loadRepoDetail.setValue(new ObjectResponse<Repo>().success(repo)),
                        throwable -> loadRepoDetail.setValue(new ObjectResponse<Repo>().error(throwable)))
        );
    }
}
