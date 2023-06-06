package com.topreddit.topredditapplication;

import com.topreddit.topredditapplication.adapter.PostViewAdapter;
import com.topreddit.topredditapplication.data.DataParser;
import com.topreddit.topredditapplication.model.Post;
import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.postRecycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DataParser dataParser = new DataParser();
        List<Post> posts = dataParser.getPosts();
        PostViewAdapter postViewAdapter = new PostViewAdapter(posts, MainActivity.this);
        recyclerView.setAdapter(postViewAdapter);
    }
}
