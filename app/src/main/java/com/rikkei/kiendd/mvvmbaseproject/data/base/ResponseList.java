package com.rikkei.kiendd.mvvmbaseproject.data.base;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.annotations.Nullable;

public class ResponseList<T> {
    private int status;

    @Nullable
    private List<T> data;

    @Nullable
    private Throwable error;

    public ResponseList() {
    }

    private ResponseList(int status, List<T> data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public List<T> getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public ResponseList<T> loading() {
        return new ResponseList<>(Define.ResponseStatus.LOADING, null, null);
    }

    public ResponseList<T> success(@NonNull List<T> data) {
        return new ResponseList<>(Define.ResponseStatus.SUCCESS, data, null);
    }

    public ResponseList<T> error(@NonNull Throwable throwable) {
        return new ResponseList<>(Define.ResponseStatus.ERROR, null, throwable);
    }
}
