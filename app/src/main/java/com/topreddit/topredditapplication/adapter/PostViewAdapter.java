package com.topreddit.topredditapplication.adapter;

import com.topreddit.topredditapplication.ImageActivity;
import com.topreddit.topredditapplication.MainActivity;
import com.topreddit.topredditapplication.R;
import com.topreddit.topredditapplication.data.ImageLoader;
import com.topreddit.topredditapplication.model.Post;
import java.net.URI;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<Post> posts;
    private ImageLoader imageLoader;
    private Context context;

    public PostViewAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    public PostViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        imageLoader = new ImageLoader();
        holder.author.setText(posts.get(position).getAuthor());
        holder.title.setText(posts.get(position).getTitle());
        holder.imageView.setImageBitmap(imageLoader.getBitmapFromUrl(posts.get(position).getUrl()));
        int time = getTime(posts.get(position).getPostTime());
        String tm = time + " hours ago";
        holder.time.setText(tm);
        String cm = posts.get(position).getCommentsCount() + " comments";
        holder.comments.setText(cm);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static int getTime(long postTime) {
        long timeInSec = (System.currentTimeMillis()/1000) - postTime;
        long hour = (timeInSec - (timeInSec % 3600)) / 3600;
        return (int)hour;
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
            context = itemView.getContext();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}

