package pl.tumblr.app.ui.postFragment;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pl.tumblr.app.core.TumblrApplication;
import pl.tumblr.app.ui.base.BasePresenter;
import pl.tumblr.app.ui.postViewFragment.PostViewFragment;
import pl.tumblr.restApi.core.ApiEngine;
import pl.tumblr.restApi.interfaces.GetPostTumblrListener;
import pl.tumblr.restApi.models.Posts;
import pl.tumblr.restApi.models.Tumblelog;
import pl.tumblr.restApi.response.GetTumblrResponse;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class PostFragmentPresenter extends BasePresenter<PostFragment> {

    private int postsListSize;
    public static final String POST_INT_POST = "postsListSize", POST_LIST = "postList";

    public interface PostsInterface {

        void showProgress();

        void hideProgress();

        void showEmptyDataInfo();

        void hideEmptyDataInfo();

        void showPostsList(final ArrayList<Posts> postsList);

        void hidePostsList();

        void onFinishRefresh();
    }

    @Override
    public void onLoad(PostFragment view) {
        super.onLoad(view);
    }

    public void onItemClicked(Posts posts) {
        Bundle bundle = new Bundle();
        bundle.putInt(POST_INT_POST, postsListSize);
        bundle.putSerializable(POST_LIST, posts);
        getView().startNewFragment(new PostViewFragment(), PostViewFragment.TAG, bundle);
    }

    public void getPostTumblr() {
        ApiEngine.getTumbrl(getPostTumblrListener);
    }

    private GetPostTumblrListener getPostTumblrListener = new GetPostTumblrListener() {
        @Override
        public void onSuccess(GetTumblrResponse response) {
            boolean success = true;
            postsListSize = response.getPostsList().size();
            if (response.getPostsList() != null) {
                if (postsListSize < 1) {
                    success = false;
                }
            } else {
                success = false;
            }
            if (success) {
                getView().hideEmptyDataInfo();
                getView().showPostsList(response.getPostsList());
                TumblrApplication.tumblrInterface.onPosts(response.getPostsList());
            } else {
                getView().hidePostsList();
                getView().isPostsListEmpty = true;
                getView().showEmptyDataInfo();
            }

        }


        @Override
        public void onStart() {
            getView().hidePostsList();
            getView().hideEmptyDataInfo();
            getView().showProgress();
        }

        @Override
        public void onUnknownError() {

        }

        @Override
        public void onResponseFailure() {
            getView().showEmptyDataInfo();
            getView().layout.mTvEmptyPostList.setText("response failure");
        }

        @Override
        public void onInternalError() {

        }

        @Override
        public void onEnd() {
            getView().onFinishRefresh();
            getView().hideProgress();
        }
    };

}
