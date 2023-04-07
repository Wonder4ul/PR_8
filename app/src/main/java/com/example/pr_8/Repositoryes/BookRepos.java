package com.example.pr_8.Repositoryes;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.pr_8.Book;
import com.example.pr_8.DataSources.Room.BookDao;
import com.example.pr_8.DataSources.Room.DataBase;
import com.example.pr_8.DataSources.BookDataSource;
import com.example.pr_8.GlolibApp;
import com.example.pr_8.Models.BookInfo;

import java.util.List;

public class BookRepos {

    private final BookDao mBookDao;
    private final LiveData<List<Book>> mBooks;
    DataBase dataBase;

    public BookRepos() {
        dataBase = GlolibApp.instance.getDatabase();
        mBookDao = GlolibApp.instance.getDatabase().bookDao();
        mBooks = mBookDao.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return mBooks;
    }

//    public void insert(Book book) {
//        DataBase.databaseWriteExecutor.execute(() -> {
//            mBookDao.insert(book);
//        });
//    }


    public LiveData<List<Book>> generateData(){
        return BookDataSource.setInitialData();
    }
}
