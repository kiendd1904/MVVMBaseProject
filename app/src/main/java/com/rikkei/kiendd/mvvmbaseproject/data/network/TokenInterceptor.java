package com.rikkei.kiendd.mvvmbaseproject.data.network;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.utils.rx.RxBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private RxBus event;

    public TokenInterceptor() {
        event = RxBus.getInstance();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder ongoing = chain.request().newBuilder();
        Response response = chain.proceed(ongoing.build());
        int responseCode = response.code();
        if (responseCode == Define.Api.Http.RESPONSE_CODE_ACCESS_TOKEN_EXPIRED) {
            event.publish(Define.Bus.ACCESS_TOKEN_EXPIRED);
        }
        return response;
    }
}
