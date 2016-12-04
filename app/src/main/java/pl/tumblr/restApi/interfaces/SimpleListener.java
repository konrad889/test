package pl.tumblr.restApi.interfaces;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public interface SimpleListener {

    void onStart();

    void onUnknownError();

    void onResponseFailure();

    void onInternalError();

    void onEnd();
}
