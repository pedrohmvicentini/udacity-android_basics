package com.example.android.projeto8;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 15/01/2018.
 */

public final class Utils {
    public static final String LOG_TAG = Utils.class.getSimpleName();

    private Utils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {

        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Erro na URL", e);
        }

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Não há conexão", e);
        }

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<News> newsList = new ArrayList<>();

        try {
            JSONObject baseJsonObject = new JSONObject(jsonResponse);

            JSONObject response = baseJsonObject.getJSONObject("response");

            JSONArray result = response.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                JSONObject newsObject = result.getJSONObject(i);
                String title = newsObject.getString("webTitle");
                String section = newsObject.getString("sectionName");
                String date = newsObject.getString("webPublicationDate");
                String webUrl = newsObject.getString("webUrl");

                if (date.length() > 10) {
                    date = date.substring(0, 10);
                }

                String author = "N/A";
                List<String> authorsList = new ArrayList<>();

                JSONArray tagsArray = newsObject.getJSONArray("tags");

                for (int j = 0; j < tagsArray.length(); j++) {
                    JSONObject tagsObject = tagsArray.getJSONObject(j);
                    String firstName = tagsObject.optString("firstName");
                    String lastName = tagsObject.optString("lastName");
                    String authorName;
                    if (TextUtils.isEmpty(firstName)) {
                        authorName = lastName;
                    } else {
                        authorName = firstName + " " + lastName;
                    }
                    authorsList.add(authorName);
                }

                if (authorsList.size() == 0) {
                    author = "N/A";
                } else {
                    author = TextUtils.join(", ", authorsList);
                }

                News news = new News(title, section, date, webUrl, author);
                newsList.add(news);
            }
        } catch (JSONException e) {
            Log.e("Utils", "Erro ao converter JSON", e);
        }

        return newsList;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(20000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();

                StringBuilder output = new StringBuilder();
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        output.append(line);
                        line = reader.readLine();
                    }
                }
                jsonResponse = output.toString();

            } else {
                Log.e(LOG_TAG, "Erro: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro ao recuperar dados", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
}
