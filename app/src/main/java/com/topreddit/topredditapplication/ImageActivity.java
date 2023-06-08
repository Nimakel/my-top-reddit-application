package com.topreddit.topredditapplication;

import com.topreddit.topredditapplication.adapter.PostViewAdapter;
import com.topreddit.topredditapplication.data.ImageLoader;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;

public class ImageActivity extends Activity {
    private ImageView imageView;
    private Button saveButton;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView = findViewById(R.id.imageView);
        saveButton = findViewById(R.id.save_button);
        imageLoader = new ImageLoader();
        imageView.setImageBitmap(imageLoader.getBitmapFromUrl(PostViewAdapter.getImage()));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
