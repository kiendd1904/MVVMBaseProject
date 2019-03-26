package com.rikkei.kiendd.mvvmbaseproject.di.component;

import android.app.Application;

import com.rikkei.kiendd.mvvmbaseproject.BaseApplication;
import com.rikkei.kiendd.mvvmbaseproject.di.module.ActivityBindingModule;
import com.rikkei.kiendd.mvvmbaseproject.di.module.AppDatabaseModule;
import com.rikkei.kiendd.mvvmbaseproject.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        AppDatabaseModule.class,
        ActivityBindingModule.class
})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
