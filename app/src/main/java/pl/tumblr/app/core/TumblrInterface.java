package pl.tumblr.app.core;

import java.util.ArrayList;

import pl.tumblr.restApi.models.Posts;

/**
 * Created by Konrad-PC on 01.12.2016.
 */

public interface TumblrInterface {

    void onPosts(ArrayList<Posts> postsList);
}
