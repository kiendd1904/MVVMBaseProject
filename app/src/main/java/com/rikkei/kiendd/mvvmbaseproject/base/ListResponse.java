package com.rikkei.kiendd.mvvmbaseproject.base;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.annotations.Nullable;

public class ListResponse<T> {
    private int status;

    @Nullable
    private List<T> data;

    @Nullable
    private Throwable error;

    public ListResponse() {
    }

    private ListResponse(int status, List<T> data, Throwable error) {
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

    public ListResponse<T> loading() {
        return new ListResponse<>(Define.ResponseStatus.LOADING, null, null);
    }

    public ListResponse<T> success(@NonNull List<T> data) {
        return new ListResponse<>(Define.ResponseStatus.SUCCESS, data, null);
    }

    public ListResponse<T> error(@NonNull Throwable throwable) {
        return new ListResponse<>(Define.ResponseStatus.ERROR, null, throwable);
    }
}
