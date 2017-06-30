package com.hades.mylibrary.utils;

import android.widget.ImageView;

/**
 * Created by Hades on 2017/2/16.
 */

public class ImageParameter {
    String imageUrl;
    ImageView imageView;
    int placeHolder;
    int errorHolder;
    int level;//根据不同内存状态响应不同内存释放策略

    public ImageParameter(String imageUrl, ImageView imageView, int placeHolder, int errorHolder, int level) {
        this.imageUrl = imageUrl;
        this.imageView = imageView;
        this.placeHolder = placeHolder;
        this.errorHolder = errorHolder;
        this.level = level;
    }

    public int getErrorHolder() {
        return errorHolder;
    }

    public void setErrorHolder(int errorHolder) {
        this.errorHolder = errorHolder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
