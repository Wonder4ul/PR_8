package com.example.pr_8.Repositoryes;

import androidx.lifecycle.LiveData;

import com.example.pr_8.Book;
import com.example.pr_8.DataSources.Room.BookDao;
import com.example.pr_8.DataSources.Room.DataBase;
import com.example.pr_8.DataSources.BookDataSource;
import com.example.pr_8.GlolibApp;

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
}
