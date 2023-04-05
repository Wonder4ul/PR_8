package com.example.pr_8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_8.Models.BookInfo;

import java.util.ArrayList;
import java.util.List;

public class BookRecycleAdapter extends RecyclerView.Adapter<BookRecycleAdapter.ViewHolder>{
    public interface OnBookClickListener{
        void onBookClick(BookInfo book, int position);
    }
    private final OnBookClickListener onClickListener;
    private final LayoutInflater inflater;
    public List<BookInfo> books;
     public BookRecycleAdapter(Context context, OnBookClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.books = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookInfo book = books.get(position);
        holder.picView.setImageResource(book.getPictureResource());
        holder.nameView.setText(book.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onBookClick(book, position);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(final List<BookInfo> books) {
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