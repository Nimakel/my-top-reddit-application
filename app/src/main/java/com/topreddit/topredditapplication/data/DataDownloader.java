package com.topreddit.topredditapplication.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;

public class DataDownloader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... string) {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            URL redditEndpoint = new URL(string[0]);
            connection = (HttpURLConnection) redditEndpoint.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str).append("/n");
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't download data from URL!", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    new RuntimeException("Can't close buffered reader! Something went wrong." , e);
                }
            }
        }
    }
}
