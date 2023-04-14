package com.example.pr_8.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr_8.Book;
import com.example.pr_8.GlolibApp;
import com.example.pr_8.Models.BookInfo;
import com.example.pr_8.Repositoryes.BookRepos;
import com.example.pr_8.UserInterfaces.LibraryFragment;

import java.util.List;

public class BookViewPattern extends ViewModel {

    public BookRepos mBookRepos;
    public LiveData<List<Book>> mBooks;
    public BookViewPattern(){
        mBookRepos = GlolibApp.instance.getBookRepos();
        mBooks = mBookRepos.getBooks();
    }
}
