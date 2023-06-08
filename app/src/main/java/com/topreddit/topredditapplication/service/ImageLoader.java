package com.topreddit.topredditapplication.service;

import com.topreddit.topredditapplication.data.ImageDownloader;
import java.util.concurrent.ExecutionException;
import android.graphics.Bitmap;

public class ImageLoader {
    public Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap;
        try {
            bitmap = new ImageDownloader().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Can't get bitmap from image URL!" , e);
        }
        return bitmap;
    }
}
