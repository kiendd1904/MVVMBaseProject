package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.content.Context;

import javax.inject.Inject;

import androidx.annotation.StringRes;

public class ResourceProvider implements IResourceProvider {

    private Context context;

    @Inject
    ResourceProvider(Context context) {
        this.context = context;
    }

    @Override
    public String getString(@StringRes final int id) {
        return context.getString(id);
    }
}
