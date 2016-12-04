package pl.tumblr.restApi.interfaces;

import pl.tumblr.restApi.response.GetTumblrResponse;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public interface GetPostTumblrListener extends SimpleListener {

    void onSuccess(GetTumblrResponse response);

}
