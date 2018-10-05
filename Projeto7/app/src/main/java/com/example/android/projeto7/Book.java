package com.example.android.projeto7;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pedro on 17/12/2017.
 */

public class Book implements Parcelable {

    private String author;
    private String title;
    private String year;

    public Book(String author, String title, String year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    protected Book(Parcel in) {
        author = in.readString();
        title = in.readString();
        year = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(year);
    }
}