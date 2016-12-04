package pl.tumblr.restApi.response;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import pl.tumblr.restApi.models.Posts;
import pl.tumblr.restApi.models.Tumblelog;

/**
 * Created by Konrad_Tomczyk on 01.12.2016.
 */

public class GetTumblrResponse implements Serializable {

    @SerializedName("tumblelog")
    ArrayList<Tumblelog> tumblelogList;

    @SerializedName("posts-strat")
    Integer postsStart;

    @SerializedName("posts-total")
    Integer postsTotal;

    @SerializedName("posts-type")
    boolean postsType;

    @SerializedName("posts")
    ArrayList<Posts> postsList;

    public ArrayList<Posts> getPostsList() {
        return postsList;
    }

    public ArrayList<Tumblelog> getTumblelogList() {
        return tumblelogList;
    }

    public Integer getPostsStart() {
        return postsStart;
    }

    public Integer getPostsTotal() {
        return postsTotal;
    }

    public boolean isPostsType() {
        return postsType;
    }


}
