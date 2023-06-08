package com.topreddit.topredditapplication;

import static android.content.ContentValues.TAG;

import com.topreddit.topredditapplication.adapter.PostViewAdapter;
import com.topreddit.topredditapplication.service.ImageLoader;
import java.io.OutputStream;
import java.util.Objects;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class ImageActivity extends Activity {
    private ImageView imageView;
    private Button saveButton;
    private ImageLoader imageLoader;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView = findViewById(R.id.imageView);
        saveButton = findViewById(R.id.save_button);
        imageLoader = new ImageLoader();
        bitmap = imageLoader.getBitmapFromUrl(PostViewAdapter.getImage());
        imageView.setImageBitmap(bitmap);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission is granted");
                saveImageToGallery();
            } else {
                Log.d(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(ImageActivity.this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
            }
        });
    }

    private void saveImageToGallery() {
        Uri images;
        ContentResolver contentResolver = getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            images = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis()
                + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "images/*");
        Uri uri = contentResolver.insert(images, contentValues);
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmapDraw = bitmapDrawable.getBitmap();
            OutputStream outputStream = contentResolver.openOutputStream(
                    Objects.requireNonNull(uri));
            bitmapDraw.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Objects.requireNonNull(outputStream);
            Toast.makeText(ImageActivity.this, "Image saved successfully",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Image saved!");
        } catch (Exception e) {
            Toast.makeText(ImageActivity.this, "Can't save image!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
