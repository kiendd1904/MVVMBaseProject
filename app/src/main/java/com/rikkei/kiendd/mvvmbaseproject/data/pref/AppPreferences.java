package com.rikkei.kiendd.mvvmbaseproject.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import javax.inject.Inject;

public class AppPreferences {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_FCM_TOKEN = "PREF_KEY_FCM_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferences(Context context) {
        mPrefs = context.getSharedPreferences(Define.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setAccessToken(String accessToken) {
        String BASE_TOKEN = "Bearer ";
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, BASE_TOKEN + accessToken).apply();
    }

    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    public void setFirstLaunch(boolean isFirstLaunch) {
        mPrefs.edit().putBoolean(Define.PreferenceKey.IS_FIRST_LAUNCH, isFirstLaunch).apply();
    }

    public boolean isFirstLaunch() {
        return mPrefs.getBoolean(Define.PreferenceKey.IS_FIRST_LAUNCH, true);
    }

    public void setFcmToken(String fcmToken) {
        mPrefs.edit().putString(PREF_KEY_FCM_TOKEN, fcmToken).apply();
    }

    public String getFcmToken() {
        return mPrefs.getString(PREF_KEY_FCM_TOKEN, null);
    }
}
