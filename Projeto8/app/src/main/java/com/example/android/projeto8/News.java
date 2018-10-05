package com.example.android.projeto8;

/**
 * Created by Pedro on 11/01/2018.
 */

public class News {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mWebUrl;
    private String mAuthor;

    public News(String title, String section, String date, String webUrl, String author) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mWebUrl = webUrl;
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
