package com.rikkei.kiendd.mvvmbaseproject.di.module;

import android.content.Context;

import com.rikkei.kiendd.mvvmbaseproject.data.AppDatabase;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class AppDatabaseModule {

    @Singleton
    @Provides
    AppDatabase provideRoomDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Define.Database.DATABASE_NAME)
                .build();
    }
}
