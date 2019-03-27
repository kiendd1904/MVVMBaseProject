package com.rikkei.kiendd.mvvmbaseproject.data.model;

import com.rikkei.kiendd.mvvmbaseproject.utils.Define;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Define.Database.User.TABLE_NAME)
public class User {

    @PrimaryKey
    @ColumnInfo(name = Define.Database.User.ID)
    private Long id;

    @ColumnInfo(name = Define.Database.User.LOG_IN)
    private String login;

    @ColumnInfo(name = Define.Database.User.AVATAR_URL)
    private String avatarUrl;

    @ColumnInfo(name = Define.Database.User.NAME)
    private String name;

    @ColumnInfo(name = Define.Database.User.COMPANY)
    private String company;

    @ColumnInfo(name = Define.Database.User.EMAIL)
    private String email;

    @ColumnInfo(name = Define.Database.User.LOCATION)
    private String location;

    public User() {
    }

    @Ignore
    public User(Long id, String login, String avatarUrl, String name, String company, String email, String location) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.company = company;
        this.email = email;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
