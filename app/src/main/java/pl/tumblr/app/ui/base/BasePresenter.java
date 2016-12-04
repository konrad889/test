package pl.tumblr.app.ui.base;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public abstract class BasePresenter<T> {

    private T view = null;

    public void onLoad(T view) {
        this.view = view;
    }

    protected T getView() {

        if (view == null) {
            throw new IllegalStateException("Initialize presenter first");
        }

        return view;
    }
}
