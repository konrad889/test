package pl.tumblr.restApi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Konrad-PC on 01.12.2016.
 */

public class Tumblelog implements Serializable {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("name")
    String name;

    @SerializedName("timezone")
    String timezone;

    @SerializedName("cname")
    String cname;

    @SerializedName("feeds")
    ArrayList<Feeds> feedsList;

    public ArrayList<Feeds> getFeedsList() {
        return feedsList;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCname() {
        return cname;
    }

}
