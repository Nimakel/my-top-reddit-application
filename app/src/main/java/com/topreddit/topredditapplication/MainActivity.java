package com.topreddit.topredditapplication;

import com.topreddit.topredditapplication.adapter.PostViewAdapter;
import com.topreddit.topredditapplication.service.DataParser;
import com.topreddit.topredditapplication.model.Post;
import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DataParser dataParser;
    private List<Post> posts;
    private PostViewAdapter postViewAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.postRecycleView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        dataParser = new DataParser();
        posts = dataParser.getPosts();
        postViewAdapter = new PostViewAdapter(posts, MainActivity.this);
        recyclerView.setAdapter(postViewAdapter);
    }
}
