package com.rikkei.kiendd.mvvmbaseproject.data.network;

import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("orgs/Google/repos")
    Single<List<Repo>> getRepositories();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String owner, @Path("name") String name);
}
