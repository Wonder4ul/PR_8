package com.example.pr_8.DataSources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr_8.Book;
import com.example.pr_8.Models.BookInfo;
import com.example.pr_8.R;

import java.util.ArrayList;
import java.util.List;

public class BookDataSource {



    public static LiveData<List<Book>> setInitialData(){
        MutableLiveData<List<Book>> result = new MutableLiveData<>();
        ArrayList<Book> books = new ArrayList<>();
        for(int i=1; i<=200;i++){
            books.add(new Book(String.valueOf(i), R.drawable.book_svgrepo_com));
        }
        result.setValue(books);
        return result;
    }
}
