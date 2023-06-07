package com.topreddit.topredditapplication.data;

import com.topreddit.topredditapplication.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser {
    private static final String BASE_URL = "https://www.reddit.com/r/all/top.json";
    private static final String DATA = "data";
    private static final String CHILDREN = "children";
    private static final String SUBREDDIT_NAME = "subreddit_name_prefixed";
    private static final String TITLE = "title";
    private static final String CREATED_UTC = "created_utc";
    private static final String NUM_COMMENTS = "num_comments";
    private static final String THUMBNAIL = "thumbnail";
    private static final String AFTER = "after";
    private static final String IMAGE_URL = "url";
    private static final String MEDIA = "media";
    private static final String REDDIT_VIDEO = "reddit_video";
    private static final String FALLBACK_URL = "fallback_url";
    private static final String IS_VIDEO = "is_video";

    public List<Post> getPosts() {
        List<Post> posts;
        try {
            String json = new DataDownloader().execute(BASE_URL).get();
            posts = parseDataFromJSON(json);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Can't get posts from parser!" , e);
        }
        return posts;
    }

    private List<Post> parseDataFromJSON(String json) {
        List<Post> posts = new ArrayList<>();
        try {
            JSONObject body = new JSONObject(json);
            JSONObject dataScope = body.getJSONObject(DATA);
            JSONArray children = dataScope.getJSONArray(CHILDREN);
            for (int i = 0; i < children.length(); i++) {
                JSONObject data = children.getJSONObject(i).getJSONObject(DATA);
                Post post = new Post();
                post.setAuthor(data.getString(SUBREDDIT_NAME));
                post.setTitle(data.getString(TITLE));
                post.setPostTime(data.getLong(CREATED_UTC));
                post.setCommentsCount(data.getInt(NUM_COMMENTS));
                post.setThumbnailUrl(data.getString(THUMBNAIL));
                post.setAfter(dataScope.getString(AFTER));
                post.setUrl(data.getString(IMAGE_URL));
                if (!data.isNull(MEDIA)) {
                    JSONObject media = data.getJSONObject(MEDIA);
                    if (data.has(REDDIT_VIDEO)) {
                        JSONObject redditVideo = media.getJSONObject(REDDIT_VIDEO);
                        post.setVideoUrl(redditVideo.getString(FALLBACK_URL));
                        post.setVideo(data.getBoolean(IS_VIDEO));
                    }
                } else {
                    post.setVideo(false);
                }
                posts.add(post);
            }
            return posts;
        } catch (JSONException e) {
            throw new RuntimeException("Can't parse data from JSON!", e);
        }
    }
}
