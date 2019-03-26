package com.rikkei.kiendd.mvvmbaseproject.data.base;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import androidx.annotation.NonNull;
import io.reactivex.annotations.Nullable;

public class ResponseObject<T> {
    private int status;

    @Nullable
    private T data;

    @Nullable
    private Throwable error;

    public ResponseObject() {
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    private ResponseObject(int status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public ResponseObject<T> loading() {
        return new ResponseObject<>(Define.ResponseStatus.LOADING, null, null);
    }

    public ResponseObject<T> success(@NonNull T data) {
        return new ResponseObject<>(Define.ResponseStatus.SUCCESS, data, null);
    }

    public ResponseObject<T> error(@NonNull Throwable throwable) {
        return new ResponseObject<>(Define.ResponseStatus.ERROR, null, throwable);
    }
}
