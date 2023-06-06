package com.topreddit.topredditapplication.adapter;

import com.topreddit.topredditapplication.R;
import com.topreddit.topredditapplication.data.DataDownloader;
import com.topreddit.topredditapplication.data.DataParser;
import com.topreddit.topredditapplication.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private static final String BASE_URL = "https://www.reddit.com/r/all/top.json";
    private DataParser dataParser;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Post> posts = getPosts(BASE_URL);
        holder.author.setText(posts.get(position).getAuthor());
        holder.title.setText(posts.get(position).getTitle());
        holder.time.setText((int) posts.get(position).getPostTime());
        holder.comments.setText(posts.get(position).getCommentsCount());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private List<Post> getPosts(String url) {
        List<Post> posts;
        try {
            String json = new DataDownloader().execute(BASE_URL).get();
            dataParser = new DataParser();
            posts = dataParser.getPostsFromJSON(json);
            return posts;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Can't get posts in RecycleView adapter" , e);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author, title, time, comments;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            comments = (TextView) itemView.findViewById(R.id.comments);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}

