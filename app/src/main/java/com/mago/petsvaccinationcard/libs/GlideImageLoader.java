package com.mago.petsvaccinationcard.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.mago.petsvaccinationcard.libs.base.ImageLoader;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedLoadingListener != null){
            glideRequestManager
                    .load(URL)
                    .apply(RequestOptions.circleCropTransform())
                    .addListener(onFinishedLoadingListener)
                    .into(imageView);
        } else {
            glideRequestManager
                    .load(URL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if (listener instanceof RequestListener)
            this.onFinishedLoadingListener = (RequestListener) listener;
    }
}
