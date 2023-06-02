package com.topreddit.topredditapplication.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;

public class DataDownloader extends AsyncTask<String[], Void, String> {
    private static final String BASE_URL = "https://www.reddit.com/r/all/top.json";

    @Override
    protected String doInBackground(String[]... strings) {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            URL redditEndpoint = new URL(BASE_URL);
            connection = (HttpURLConnection) redditEndpoint.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
