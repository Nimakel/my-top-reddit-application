package com.topreddit.topredditapplication.data;

import java.io.IOException;
import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap icon = null;
        try {
            InputStream inputStream = new java.net.URL(strings[0]).openStream();
            icon = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Can't download image from URL!", e);
        }
        return icon;
    }
}
