package pl.tumblr.restApi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Konrad-PC on 01.12.2016.
 */

public class Posts implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("url")
    String url;

    @SerializedName("url-with-slug")
    String urlWithSlug;

    @SerializedName("type")
    String type;

    @SerializedName("date")
    String date;

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlWithSlug() {
        return urlWithSlug;
    }

    public String getType() {
        return type;
    }

}
