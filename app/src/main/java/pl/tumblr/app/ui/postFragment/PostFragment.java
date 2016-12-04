package pl.tumblr.app.ui.postFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import pl.tumblr.R;
import pl.tumblr.app.core.TumblrApplication;
import pl.tumblr.app.ui.base.BaseFragment;
import pl.tumblr.restApi.models.Posts;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class PostFragment extends BaseFragment implements PostFragmentPresenter.PostsInterface {

    public static final String TAG = PostFragment.class.getSimpleName();

    public ViewHolder layout;
    private PostFragmentPresenter presenter;
    private PostFragmentAdapter mRvAdapter;
    public static boolean shouldReloadPosts = false;
    public static boolean isPostsListEmpty = false;


    public static class ViewHolder {

        TextView mTvEmptyPostList;
        ProgressBar mPbWait;
        RecyclerView mRvPostList;
        MaterialRefreshLayout mRlPullToRefresh;

        public ViewHolder(View root) {
            mTvEmptyPostList = (TextView) root.findViewById(R.id.posts_tv_empty_list);
            mPbWait = (ProgressBar) root.findViewById(R.id.posts_pb);
            mRvPostList = (RecyclerView) root.findViewById(R.id.posts_list_fragm_rv);
            mRlPullToRefresh = (MaterialRefreshLayout) root.findViewById(R.id.posts_list_fragm_mrl);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_post_list, container, false);
        layout = new ViewHolder(root);
        setPresenter();
        initPullToRefresh();
        requestUserNameDialog();
        return root;
    }

    private void setPresenter() {
        presenter = new PostFragmentPresenter();
        presenter.onLoad(this);
        shouldReloadPosts = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (shouldReloadPosts) {
            presenter.getPostTumblr();
            shouldReloadPosts = false;
        }
    }

    private void initPullToRefresh() {
        layout.mRlPullToRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                presenter.getPostTumblr();
            }
        });
    }

    private void requestUserNameDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.dialog_post_set_name);
        final EditText mEtDialogName = new EditText(getActivity());
        builder.setView(mEtDialogName);
        builder.setPositiveButton(R.string.general_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TumblrApplication.userName = mEtDialogName.getText().toString();
                presenter.getPostTumblr();
            }
        });
        builder.setNegativeButton(R.string.general_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                requestUserNameDialog();
            }
        });
        builder.show();

    }

    @Override
    public void showProgress() {
        layout.mPbWait.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        layout.mPbWait.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyDataInfo() {
        layout.mTvEmptyPostList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyDataInfo() {
        layout.mTvEmptyPostList.setVisibility(View.GONE);
    }

    @Override
    public void showPostsList(final ArrayList<Posts> postsList) {
        layout.mRvPostList.setVisibility(View.VISIBLE);
        mRvAdapter = new PostFragmentAdapter(postsList, new PostFragmentAdapter.ViewHolder.AdapterOnClick() {
            @Override
            public void onItemClicked(int position) {
                presenter.onItemClicked(postsList.get(position));
            }
        });
        layout.mRvPostList.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout.mRvPostList.setAdapter(mRvAdapter);
    }


    @Override
    public void hidePostsList() {
        layout.mRvPostList.setVisibility(View.GONE);
    }

    @Override
    public void onFinishRefresh() {
        layout.mRlPullToRefresh.finishRefresh();
    }
}
