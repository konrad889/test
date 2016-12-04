package pl.tumblr.app.ui.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.tumblr.app.ui.utils.NetworkAvailable;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class BaseActivity extends AppCompatActivity {

    NetworkAvailable networkAvailable = null;
    Boolean connIntentFilterIsRegistered = false;
    IntentFilter connIntentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connIntentFilter = new IntentFilter();
        connIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkAvailable = new NetworkAvailable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!connIntentFilterIsRegistered) {
            registerReceiver(networkAvailable, connIntentFilter);
            connIntentFilterIsRegistered = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (connIntentFilterIsRegistered) {
            unregisterReceiver(networkAvailable);
            connIntentFilterIsRegistered = false;
        }
    }
}
