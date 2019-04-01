package com.rikkei.kiendd.mvvmbaseproject.utils;

import androidx.annotation.StringRes;

public interface IResourceProvider {

    String getString(@StringRes final int id);
}
