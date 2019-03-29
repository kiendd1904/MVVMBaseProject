package com.rikkei.kiendd.mvvmbaseproject.data.network.model;

import com.google.gson.annotations.SerializedName;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import java.util.List;

public class ApiArrayResponse<T> {

    @SerializedName(Define.Api.BaseResponse.SUCCESS)
    private Integer success;

    @SerializedName(Define.Api.BaseResponse.DATA)
    private List<T> data;

    @SerializedName(Define.Api.BaseResponse.PAGE)
    private Integer page;

    @SerializedName(Define.Api.BaseResponse.ERROR)
    private RequestError error;

    public Integer getSuccess() {
        return success;
    }

    public boolean isSuccess() {
        return success == 1;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public RequestError getRequestError() {
        return error;
    }

    public void setRequestError(RequestError error) {
        this.error = error;
    }
}
