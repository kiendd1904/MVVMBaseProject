package com.rikkei.kiendd.mvvmbaseproject.data.network;

import android.util.Log;

import androidx.annotation.Nullable;

import com.rikkei.kiendd.mvvmbaseproject.data.pref.AppPreferences;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;
import com.rikkei.kiendd.mvvmbaseproject.utils.RxBus;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

/**
 * This class used to handle call api with access token
 */
public class Authentication implements Authenticator {

    private ApiInterface mApiInterface;
    private AppPreferences mPreference;

    public Authentication(ApiInterface apiInterface, AppPreferences preferences) {
        this.mApiInterface = apiInterface;
        this.mPreference = preferences;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        if (!response.request().header(Define.Api.Key.AUTHORIZATION).equals(mPreference.getAccessToken())) {
            return null;
        }

        String newAccessToken = null;

//        Call<ApiObjectResponse<TokenResponse>> call =
//                mApiInterface.getNewAccessToken(new RefreshTokenRequest(mPreference.getRefreshToken()));
//        try {
//            retrofit2.Response responseCall = call.execute();
//            ApiObjectResponse<TokenResponse> responseRequest = (ApiObjectResponse<TokenResponse>) responseCall.body();
//            if (responseRequest != null) {
//                mPreference.setAccessToken(responseRequest.getData().getAccessToken());
//                newAccessToken = mPreference.getAccessToken();
//            }
//
//            if (responseCall.code() == Define.Api.Http.RESPONSE_CODE_REFRESH_TOKEN_EXPIRED) {
//                RxBus.getInstance().publish(Define.Bus.REFRESH_TOKEN_EXPIRED);
//            }
//        } catch (Exception ex) {
//            Log.d("ERROR", "onResponse: " + ex.toString());
//        }
//
//        if (newAccessToken != null) {
//            // retry the failed 401 request with new access token
//            return response.request().newBuilder()
//                    .header(Define.Api.Key.AUTHORIZATION, newAccessToken)
//                    .build();
//        } else {
//            return null;
//        }

        return null;
    }
}
