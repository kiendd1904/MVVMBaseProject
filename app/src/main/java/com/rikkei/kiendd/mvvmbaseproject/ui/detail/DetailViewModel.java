package com.rikkei.kiendd.mvvmbaseproject.ui.detail;

import androidx.lifecycle.MutableLiveData;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseViewModel;
import com.rikkei.kiendd.mvvmbaseproject.base.ObjectResponse;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.data.repository.RepoRepository;
import com.rikkei.kiendd.mvvmbaseproject.utils.StringUtil;

import javax.inject.Inject;

public class DetailViewModel extends BaseViewModel {

    private RepoRepository repoRepository;

    private final MutableLiveData<ObjectResponse<Repo>> loadRepoDetail = new MutableLiveData<>();

    @Inject
    DetailViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public MutableLiveData<ObjectResponse<Repo>> getLoadRepoDetail() {
        return loadRepoDetail;
    }

    public void loadRepoDetail(Repo repo) {
        if (StringUtil.isEmpty(repo.getFullName(), repo.getName())) {
            return;
        }

        mDisposable.add(
                repoRepository.getRepo(repo.getFullName(), repo.getName())
                .doOnSubscribe(it -> loadRepoDetail.setValue(new ObjectResponse<Repo>().loading()))
                .subscribe(it -> loadRepoDetail.setValue(new ObjectResponse<Repo>().success(it)),
                        throwable -> loadRepoDetail.setValue(new ObjectResponse<Repo>().error(throwable)))
        );
    }
}
