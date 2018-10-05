package com.example.android.projeto8;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.android.projeto8.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String DATE = "2018-01-01";
    private static final String KEY = "test";
    private static final String PAGESIZE = "10";
    private static final String SHOWTAGS = "contributor";
    private static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=" + DATE + "&api-key=" + KEY + "&page-size=" + PAGESIZE + "&show-tags=" + SHOWTAGS;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int NEWS_LOADER_ID = 1;
    private NewsAdapter mNewsAdapters;
    ActivityMainBinding binding;

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        binding.loadingIndicator.setVisibility(View.GONE);

        binding.emptyView.setText(R.string.no_news);
        mNewsAdapters.clear();

        if (data != null && !data.isEmpty()) {
            mNewsAdapters.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapters.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.emptyView.setText(getResources().getText(R.string.no_news));
        binding.newsListView.setEmptyView(binding.emptyView);

        mNewsAdapters = new NewsAdapter(this, new ArrayList<News>());

        binding.newsListView.setAdapter(mNewsAdapters);

        binding.newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mNewsAdapters.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getWebUrl());
                Log.d(LOG_TAG, newsUri.toString());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loadingIndicator);
            loadingIndicator.setVisibility(View.GONE);
            binding.emptyView.setText(R.string.no_internet);
        }
    }
}
