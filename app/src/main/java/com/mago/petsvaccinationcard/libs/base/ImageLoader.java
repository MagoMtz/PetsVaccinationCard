package com.mago.petsvaccinationcard.libs.base;

import android.widget.ImageView;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
