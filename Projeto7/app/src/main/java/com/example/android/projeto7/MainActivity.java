package com.example.android.projeto7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.projeto7.Util.makeHttpRequest;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textNoDataFound;
    BookAdapter adapter;
    ListView listView;
    private static final String SEARCH_RESULTS = "booksSearchResults";
    private static final String SEARCH_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.etSearch);
        button = (Button) findViewById(R.id.btnSearch);
        textNoDataFound = (TextView) findViewById(R.id.text_no_data_found);

        adapter = new BookAdapter(this, -1);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.hasConnection(MainActivity.this)) {
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute();
                } else {
                    Toast.makeText(MainActivity.this, R.string.error_no_internet,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            Book[] books = (Book[]) savedInstanceState.getParcelableArray(SEARCH_RESULTS);
            adapter.addAll(books);
        }
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, List<Book>> {
        @Override
        protected List<Book> doInBackground(URL... urls) {
            URL url = createURL(getUrlSearch());
            String jsonResponse = "";

            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Book> books = parseJson(jsonResponse);
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if (books == null) {
                return;
            }

            if (books.isEmpty()) {
                textNoDataFound.setVisibility(View.VISIBLE);
            } else {
                textNoDataFound.setVisibility(View.GONE);
            }

            adapter.clear();
            adapter.addAll(books);
        }

        private List<Book> parseJson(String json) {
            if (json == null) {
                return null;
            }

            return extractBooks(json);
        }
    }

    private String getUrlSearch() {
        String formatUserInput = editText.getText().toString().trim().replaceAll("\\s+", "+");
        String url = SEARCH_URL + formatUserInput;
        return url;
    }

    private URL createURL(String stringUrl) {
        try {
            return new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Book> extractBooks(String json) {

        List<Book> books = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(json);

            if (jsonResponse.getInt("totalItems") == 0) {
                return books;
            }
            JSONArray jsonArray = jsonResponse.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookObject = jsonArray.getJSONObject(i);

                JSONObject bookInfo = bookObject.getJSONObject("volumeInfo");

                String title = bookInfo.optString("title");
                JSONArray authorsArray = bookInfo.getJSONArray("authors");
                String authors = Util.formatAuthors(authorsArray);
                String year = Util.formatYear(bookInfo.optString("publishedDate"));

                Book book = new Book(authors, title, year);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Book[] books = new Book[adapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = adapter.getItem(i);
        }
        outState.putParcelableArray(SEARCH_RESULTS, (Parcelable[]) books);
    }
}