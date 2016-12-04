package pl.tumblr.restApi.interfaces;

import pl.tumblr.restApi.response.GetTumblrResponse;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public interface ApiRestTumblrInterface {

    @GET("api/read/json?debug=1")
    Call<GetTumblrResponse> getTumbrl();
}
