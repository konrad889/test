package pl.tumblr.app.ui.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import pl.tumblr.app.core.TumblrApplication;

/**
 * Created by Konrad-PC on 04.12.2016.
 */

public class NetworkAvailable extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = activeNetInfo.isConnectedOrConnecting();
        } catch (Exception e) {
        }
        if (isConnected) {
            TumblrApplication.IS_CONNECTED_TO_THE_INTERNET = true;
            Toast.makeText(context, "Device is connected to the internet", Toast.LENGTH_SHORT).show();
        } else {
            TumblrApplication.IS_CONNECTED_TO_THE_INTERNET = false;
            Toast.makeText(context, "Please connect the device to the internet.", Toast.LENGTH_SHORT).show();
        }
    }
}
