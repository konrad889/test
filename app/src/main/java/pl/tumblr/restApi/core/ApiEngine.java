package pl.tumblr.restApi.core;


import java.net.HttpURLConnection;

import pl.tumblr.restApi.interfaces.GetPostTumblrListener;
import pl.tumblr.restApi.response.GetTumblrResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class ApiEngine {


    public static void getTumbrl(final GetPostTumblrListener listener) {
        listener.onStart();
        Call<GetTumblrResponse> call = RestEngine.getApiInterface().getTumbrl();
        call.enqueue(new Callback<GetTumblrResponse>() {
            @Override

            public void onResponse(Call<GetTumblrResponse> call, Response<GetTumblrResponse> response) {
                GetTumblrResponse rsp = response.body();
                listener.onEnd();
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        listener.onSuccess(rsp);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<GetTumblrResponse> call, Throwable t) {
                t.printStackTrace();
                listener.onEnd();
                listener.onResponseFailure();
            }
        });
    }


}
