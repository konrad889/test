package pl.tumblr.restApi.core;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import pl.tumblr.restApi.utils.Logs;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Logs.d("OkHttp:" + String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        if (request.method().toLowerCase().contains("post")) {
            Logs.d("OkHttp:" + String.format("Sending request body: %s", requestBodyToString(request)));
        }

        Logs.d("OkHttp:" + String.format("Request headers: %s", request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Logs.d("OkHttp:" + String.format("Received response for %s in %.1fms",
                response.request().url(), (t2 - t1) / 1e6d));

        //show headers
        Logs.d("OkHttp:" + String.format("Received response headers %n%s", response.headers()));

        // ..the response body is a one-shot value that may be consumed only once
        if (response.body() != null) {
            MediaType contentType = response.body().contentType();
            String bodyString = response.body().string();
            Logs.d("OkHttp:" + "Response body " + bodyString);

            //recreate response body
            ResponseBody body = ResponseBody.create(contentType, bodyString);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }

    private static String requestBodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}