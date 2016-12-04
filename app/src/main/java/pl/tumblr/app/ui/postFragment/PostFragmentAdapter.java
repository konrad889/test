package pl.tumblr.app.ui.postFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pl.tumblr.R;
import pl.tumblr.app.ui.utils.PicassoUploadUtils;
import pl.tumblr.restApi.models.Posts;

/**
 * Created by Konrad-PC on 30.11.2016.
 */

public class PostFragmentAdapter extends RecyclerView.Adapter<PostFragmentAdapter.ViewHolder> {

    ArrayList<Posts> postsList;
    ViewHolder.AdapterOnClick listener;

    public PostFragmentAdapter(ArrayList<Posts> posts, ViewHolder.AdapterOnClick listener) {
        this.postsList = posts;
        this.listener = listener;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTvDate, mTvType;
        public ImageView mIvPhoto;
        private AdapterOnClick listener;

        public ViewHolder(View itemView, AdapterOnClick listener) {
            super(itemView);
            this.listener = listener;
            mTvType = (TextView) itemView.findViewById(R.id.post_adapter_type);
            mTvDate = (TextView) itemView.findViewById(R.id.post_adapter_date);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.post_adapter_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClicked(getAdapterPosition());
        }

        public static interface AdapterOnClick {
            public void onItemClicked(int position);
        }
    }

    @Override
    public PostFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, null);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(PostFragmentAdapter.ViewHolder holder, int position) {
        Posts posts = postsList.get(position);
        StringBuilder sbType = new StringBuilder();
        StringBuilder sbDate = new StringBuilder();
        sbType.append(posts.getDate());
        sbDate.append(posts.getDate());
        holder.mTvDate.setText(sbDate);
        holder.mTvType.setText(sbType);
        Picasso.with(holder.mIvPhoto.getContext())
                .load(posts.getUrl())
                .into(holder.mIvPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        PicassoUploadUtils.IS_UPLOAD_HARMED = false;
                    }

                    @Override
                    public void onError() {
                        PicassoUploadUtils.IS_UPLOAD_HARMED = true;
                    }
                });


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}
