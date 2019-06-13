package com.rikkei.kiendd.mvvmbaseproject.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rikkei.kiendd.mvvmbaseproject.BuildConfig;
import com.rikkei.kiendd.mvvmbaseproject.data.network.ApiInterface;
import com.rikkei.kiendd.mvvmbaseproject.data.network.Authentication;
import com.rikkei.kiendd.mvvmbaseproject.data.network.NetworkCheckerInterceptor;
import com.rikkei.kiendd.mvvmbaseproject.data.network.TokenInterceptor;
import com.rikkei.kiendd.mvvmbaseproject.data.pref.AppPreferences;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Named("RefreshTokenApi")
    @Singleton
    ApiInterface provideRefreshTokenApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context, @Named("RefreshTokenApi") ApiInterface refreshTokenApi, AppPreferences preferences) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        NetworkCheckerInterceptor networkCheckerInterceptor = new NetworkCheckerInterceptor(context);

        // TODO: Call api get new access token when token expired with refresh token.
        Authentication authentication = new Authentication(refreshTokenApi, preferences);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .authenticator(authentication)
                .addInterceptor(networkCheckerInterceptor)
                .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
