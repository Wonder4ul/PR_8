package com.example.pr_8.ListAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_8.Book;
import com.example.pr_8.Models.BookInfo;
import com.example.pr_8.R;

import java.util.ArrayList;
import java.util.List;

public class BookRecycleAdapter extends RecyclerView.Adapter<BookRecycleAdapter.ViewHolder>{
    public interface OnBookClickListener{
        void onBookClick(Book book, int position);
    }
    private final OnBookClickListener onClickListener;
    private final LayoutInflater inflater;
    public List<Book> books;
    //public List<Book> books;
     public BookRecycleAdapter(Context context, OnBookClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.books = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = books.get(position);
        holder.picView.setImageResource(book.getImage());
        holder.nameView.setText(book.getName());
        holder.itemView.setOnClickListener(v -> onClickListener.onBookClick(book, position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(final List<Book> books) {
        this.books.clear();
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView picView;
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            picView = view.findViewById(R.id.pic);
            nameView = view.findViewById(R.id.name);
        }
    }
}