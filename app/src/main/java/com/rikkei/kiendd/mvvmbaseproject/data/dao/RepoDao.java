package com.rikkei.kiendd.mvvmbaseproject.data.dao;

import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import io.reactivex.Completable;

@Dao
public interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRepo(Repo repo);
}
