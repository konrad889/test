package pl.tumblr.app.ui.postViewFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pl.tumblr.R;
import pl.tumblr.app.ui.base.BaseFragment;
import pl.tumblr.app.ui.postFragment.PostFragment;
import pl.tumblr.app.ui.postFragment.PostFragmentPresenter;
import pl.tumblr.app.ui.utils.PicassoUploadUtils;
import pl.tumblr.restApi.models.Posts;

/**
 * Created by Konrad-PC on 04.12.2016.
 */

public class PostViewFragment extends BaseFragment implements PostViewFragmentPresenter.postViewInterface {

    public static final String TAG = PostViewFragment.class.getSimpleName();

    private PostViewFragmentPresenter presenter;
    public ViewHolder layout;
    public int postListSize;

    public PostViewFragment() {

    }

    public class ViewHolder {

        TextView mTvType, mTvDate;
        ImageView mIvPhoto;
        ProgressBar mPbWait;

        public ViewHolder(View root) {
            mTvDate = (TextView) root.findViewById(R.id.post_fragment_date);
            mTvType = (TextView) root.findViewById(R.id.post_fragment_type);
            mIvPhoto = (ImageView) root.findViewById(R.id.post_fragment_photo);
            mPbWait = (ProgressBar) root.findViewById(R.id.post_fragment_pb);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_item, container, false);
        layout = new ViewHolder(root);
        setPresenter();
        prepareData();
        return root;
    }

    private void setPresenter() {
        presenter = new PostViewFragmentPresenter();
        presenter.onLoad(this);
    }

    private void prepareData() {
        postListSize = getArguments().getInt(PostFragmentPresenter.POST_INT_POST);
        presenter.mPosts = (Posts) getArguments().getSerializable(PostFragmentPresenter.POST_LIST);
        if (presenter.mPosts != null) {
            layout.mTvDate.setText(presenter.mPosts.getDate());
            layout.mTvType.setText(presenter.mPosts.getType());
            presenter.setImage(presenter.mPosts);
        } else {
            Toast.makeText(getActivity(), R.string.fragment_post_empty_list, Toast.LENGTH_LONG).show();
        }
    }

    public void setImageToView(String url) {
        if (url != null) {
            PicassoUploadUtils.getImageByPicasso(false, url, layout.mIvPhoto, layout.mPbWait, presenter.picassoListener);
        } else {
            layout.mIvPhoto.setImageResource(R.drawable.ic_simple_photo);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showProgress() {
        layout.mPbWait.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        layout.mPbWait.setVisibility(View.GONE);
    }
}
