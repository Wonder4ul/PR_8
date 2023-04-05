package com.example.pr_8.Repositoryes;

import androidx.lifecycle.LiveData;

import com.example.pr_8.DataSources.BookDataSource;
import com.example.pr_8.Models.BookInfo;

import java.util.List;

public class BookRepos {
    public LiveData<List<BookInfo>> generateData(){
        return BookDataSource.setInitialData();
    }
}
