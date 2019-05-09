package com.rikkei.kiendd.mvvmbaseproject.base;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mDisposable;

    protected MutableLiveData<Define.ViewState> viewState = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
