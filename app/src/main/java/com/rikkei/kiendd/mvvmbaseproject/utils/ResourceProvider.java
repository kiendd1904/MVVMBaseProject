package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.content.Context;

import javax.inject.Inject;

import androidx.annotation.StringRes;

public class ResourceProvider {

    private Context context;

    @Inject
    ResourceProvider(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int id) {
        return context.getString(id);
    }
}
