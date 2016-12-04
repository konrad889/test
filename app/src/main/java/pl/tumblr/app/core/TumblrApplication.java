package pl.tumblr.app.core;

import android.app.Application;
import android.content.Context;

import pl.tumblr.restApi.core.ApiInterface;
import pl.tumblr.restApi.core.RestEngine;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class TumblrApplication extends Application {

    public static String URL = "http://Konrad889.tumblr.com/";
    public static String userName = "";
    private static long CONNECTION_TIMEOUT_IN_SEC = 20;
    private static Context mContext;
    public static ApiInterface mInterface;
    public static TumblrInterface tumblrInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplication(URL, CONNECTION_TIMEOUT_IN_SEC, tumblrInterface, apiInterface);
        mContext = this;

    }

    public static void initializeApplication(String url, long connectionTimeOut, TumblrInterface tumblrInterfaceApplication, ApiInterface apiInterface) {
        mInterface = apiInterface;
        tumblrInterface = tumblrInterfaceApplication;
        RestEngine.initializeRetrofitService(url, connectionTimeOut, apiInterface);
    }

    private ApiInterface apiInterface = new ApiInterface() {


    };
}
