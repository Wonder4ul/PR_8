package com.example.pr_8.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr_8.Models.BookInfo;
import com.example.pr_8.Repositoryes.BookRepos;

import java.util.List;

public class BookViewPattern extends ViewModel {
    public LiveData<List<BookInfo>> books;

    public void init(){
        BookRepos repositBooks = new BookRepos();
        books = repositBooks.generateData();
    }

    public BookViewPattern(){
        init();
    }
}
