package pl.tumblr.app.ui.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Konrad-PC on 04.12.2016.
 */

public class PicassoUploadUtils {

    public static boolean IS_UPLOAD_HARMED = false;
    public static boolean showIt = false;

    public static void getImageByPicasso(final boolean isFromList, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final PicassoListener picassoListener) {

        if (picassoListener != null) {
            picassoListener.onStart();
        }

        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        if (picassoListener != null) {
                            picassoListener.onEnd();
                            picassoListener.onSuccess();
                        }
                        IS_UPLOAD_HARMED = false;
                        showIt = true;

                        imageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap, 50));
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                        if (picassoListener != null) {

                            picassoListener.onEnd();
                            picassoListener.onUnexpectedError();
                        }
                        IS_UPLOAD_HARMED = true;
                        showIt = false;
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);

                        if (isFromList) {
                            imageView.setVisibility(View.GONE);

                        } else {
                            imageView.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    public interface PicassoListener {
        void onStart();

        void onEnd();

        void onUnexpectedError();

        void onSuccess();
    }
}
