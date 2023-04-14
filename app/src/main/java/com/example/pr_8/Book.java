package com.example.pr_8;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int image;

    public Book(String name, int image) {this.name = name; this.image = image;}
    public Book() {}
    public int getImage() {
        return this.image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
