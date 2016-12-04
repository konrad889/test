package pl.tumblr.app.ui.postViewFragment;

import pl.tumblr.app.ui.base.BasePresenter;
import pl.tumblr.app.ui.postFragment.PostFragment;
import pl.tumblr.app.ui.utils.PicassoUploadUtils;
import pl.tumblr.restApi.models.Posts;

/**
 * Created by Konrad-PC on 04.12.2016.
 */

public class PostViewFragmentPresenter extends BasePresenter<PostViewFragment> {

    public Posts mPosts;

    public interface postViewInterface {

        void showProgress();

        void hideProgress();
    }

    @Override
    public void onLoad(PostViewFragment view) {
        super.onLoad(view);
    }

    public void setImage(Posts posts) {
        getView().setImageToView(posts.getUrl());
    }

    PicassoUploadUtils.PicassoListener picassoListener = new PicassoUploadUtils.PicassoListener() {
        @Override
        public void onStart() {
            getView().showProgress();
        }

        @Override
        public void onEnd() {
            getView().hideProgress();
        }

        @Override
        public void onUnexpectedError() {

        }

        @Override
        public void onSuccess() {

        }
    };
}
