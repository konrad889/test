package pl.tumblr.restApi.core;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.tumblr.restApi.interfaces.ApiRestTumblrInterface;
import pl.tumblr.restApi.utils.HttpLoggingInterceptorFromOkHttp;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class RestEngine {

    public static String URL = "";
    private static Retrofit retrofit;
    private static ApiRestTumblrInterface apiService;
    private static OkHttpClient.Builder httpClient;
    private static long CONNECTION_TIMEOUT_IN_SEC = 20;
    public static boolean LOGS_ENABLED = true;
    public static ApiInterface apiInitializeInterface;


    public static void initializeRetrofitService(String url, long connectionTimeOut, ApiInterface apiInterface) {
        URL = url;
        CONNECTION_TIMEOUT_IN_SEC = connectionTimeOut;
        apiInitializeInterface = apiInterface;

        initializeHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(url)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();

        apiService = retrofit.create(ApiRestTumblrInterface.class);


    }

    private static void initializeHttpClient() {
        httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(CONNECTION_TIMEOUT_IN_SEC, TimeUnit.SECONDS).build();
        httpClient.readTimeout(CONNECTION_TIMEOUT_IN_SEC, TimeUnit.SECONDS).build();
        httpClient.writeTimeout(CONNECTION_TIMEOUT_IN_SEC, TimeUnit.SECONDS).build();

        if (LOGS_ENABLED) {
            HttpLoggingInterceptorFromOkHttp interceptorFromOkHttp = new HttpLoggingInterceptorFromOkHttp();
            interceptorFromOkHttp.setLevel(HttpLoggingInterceptorFromOkHttp.Level.BODY);
            httpClient.interceptors().add(interceptorFromOkHttp);
        }



        httpClient.interceptors().add(new LogInterceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder().build();
                Response response = chain.proceed(request);

                return response;
            }
        });

    }

    public static ApiRestTumblrInterface getApiInterface() {
        return apiService;
    }
}
