package com.rikkei.kiendd.mvvmbaseproject.data.dao;

import com.rikkei.kiendd.mvvmbaseproject.data.model.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import io.reactivex.Completable;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);
}
