package com.rikkei.kiendd.mvvmbaseproject.data;

import com.rikkei.kiendd.mvvmbaseproject.data.dao.RepoDao;
import com.rikkei.kiendd.mvvmbaseproject.data.dao.UserDao;
import com.rikkei.kiendd.mvvmbaseproject.data.model.Repo;
import com.rikkei.kiendd.mvvmbaseproject.data.model.User;
import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Repo.class, User.class} , version = Define.Database.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RepoDao repoDao();

    public abstract UserDao userDao();

}
