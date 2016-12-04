package pl.tumblr.app.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import pl.tumblr.R;
import pl.tumblr.app.ui.main.MainActivity;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class BaseFragment extends android.support.v4.app.Fragment {

    public static Toolbar toolbar;

    public static Integer containerId = null;

    public BaseFragment() {

    }

    public void startNewFragment(android.support.v4.app.Fragment fragment, String backStackTag, Bundle bundle) {
        FragmentManager fm;
        int container = 0;
        try {
            fm = getActivity().getSupportFragmentManager();
            if (containerId != null) container = containerId;
            if (getActivity() instanceof MainActivity) {
                container = R.id.posts_list_frame_container;
            }
        } catch (Exception e) {
            System.err.println("Container got here from other module/activity, and it was unable to set card module container" + Log.getStackTraceString(e));
            return;
        }
        if (bundle != null) fragment.setArguments(bundle);
        if (!TextUtils.isEmpty(backStackTag)) {
            fm.beginTransaction()
                    .replace(container, fragment)
                    .addToBackStack(backStackTag)
                    .commit();
        } else {
            fm.beginTransaction()
                    .replace(container, fragment)
                    .commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseFragment.toolbar = null;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
