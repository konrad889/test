package pl.tumblr.app.ui.main;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.tumblr.R;
import pl.tumblr.app.ui.base.BaseActivity;
import pl.tumblr.app.ui.base.BasePresenter;
import pl.tumblr.app.ui.utils.NetworkAvailable;

public class MainActivity extends BaseActivity implements MainActivityPresenter.MainActivityInterface {

    public static final String TAG = MainActivity.class.getSimpleName();

    public MainActivityPresenter presenter;
    public ViewHolder layout;

    private static class ViewHolder {
        Toolbar toolbar;

        public ViewHolder(Activity a) {
            toolbar = (Toolbar) a.findViewById(R.id.toolbar);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = new ViewHolder(this);
        setPresenter();

    }

    public void setPresenter() {
        presenter = new MainActivityPresenter();
        presenter.onLoad(this);
    }

    public Toolbar getToolbar() {
        return layout.toolbar;
    }

}
