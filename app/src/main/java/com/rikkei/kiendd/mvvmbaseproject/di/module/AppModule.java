package com.rikkei.kiendd.mvvmbaseproject.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    @Singleton
    abstract Context provideContext(Application application);
}
