package com.example.pr_8;

import android.app.Application;
import android.util.Log;

import com.example.pr_8.DataSources.Room.DataBase;
import com.example.pr_8.Repositoryes.BookRepos;

public class GlolibApp extends Application {

    public static GlolibApp instance;

    private DataBase dataBase;
    private BookRepos mBookRepos;


    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        dataBase = DataBase.getDatabase(this);
        mBookRepos = new BookRepos();
    }

    public BookRepos getBookRepos(){
        return mBookRepos;
    }

    public DataBase getDatabase(){
        return dataBase;
    }

}
