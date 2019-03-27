package com.rikkei.kiendd.mvvmbaseproject.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mDisposable;

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
