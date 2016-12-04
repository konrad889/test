package pl.tumblr.restApi.utils;

import android.util.Log;

import pl.tumblr.restApi.core.RestEngine;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class Logs {

    public static void i(String message) {
        if (RestEngine.LOGS_ENABLED) Log.i("tumblr", message);
    }

    public static void d(String message) {
        if (RestEngine.LOGS_ENABLED) Log.d("tumblr", message);
    }

    public static void e(String message) {
        if (RestEngine.LOGS_ENABLED) Log.e("tumblr", message);
    }

    public static void i(String tag, String message) {
        if (RestEngine.LOGS_ENABLED) Log.i(tag, message);
    }

    public static void d(String tag, String message) {
        if (RestEngine.LOGS_ENABLED) Log.d(tag, message);
    }

    public static void e(String tag, String message) {
        if (RestEngine.LOGS_ENABLED) Log.e(tag, message);
    }
}