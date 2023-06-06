package com.topreddit.topredditapplication;

import com.topreddit.topredditapplication.data.DataParser;
import com.topreddit.topredditapplication.data.DataDownloader;
import com.topreddit.topredditapplication.model.Post;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataParser dataParser;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.author);
        dataParser = new DataParser();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String, Void, String> execute = new DataDownloader().execute("https://www.reddit.com/r/all/top.json");
                try {
                    String s = execute.get();
                    List<Post> posts = dataParser.getPostsFromJSON(s);
                    textView.setText(posts.get(0).getAuthor());
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
