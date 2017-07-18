package com.hades.mylibrary.utils.image;

import android.widget.ImageView;

/**
 * Created by Hades on 2017/2/16.
 */

public class ImageParam {
    String imageUrl;
    ImageView imageView;
    int placeHolder;
    int errorHolder;
    int level;//根据不同内存状态响应不同内存释放策略
    int width;
    int heigh;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigh() {
        return heigh;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
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

    public static class Builder{
        String imageUrl;
        ImageView imageView;
        int placeHolder;
        int errorHolder;
        int level;//根据不同内存状态响应不同内存释放策略
        int width;
        int heigh;

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder setPlaceHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder setErrorHolder(int errorHolder) {
            this.errorHolder = errorHolder;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeigh(int heigh) {
            this.heigh = heigh;
            return this;
        }

        public ImageParam create(){
            ImageParam parameter = new ImageParam();

            if (null != imageUrl){
                parameter.setImageUrl(imageUrl);
            }
            if (null != imageView){
                parameter.setImageView(imageView);
            }
            if (0 != placeHolder){
                parameter.setPlaceHolder(placeHolder);
            }else {
                parameter.setPlaceHolder(0);
            }
            if (0 != errorHolder){
                parameter.setErrorHolder(errorHolder);
            }else {
                parameter.setErrorHolder(0);
            }
            if (0 != level){
                parameter.setLevel(level);
            }else {
                parameter.setLevel(0);
            }
            if (0 != width){
                parameter.setWidth(width);
            }else {
                parameter.setWidth(0);
            }
            if (0 != heigh){
                parameter.setHeigh(heigh);
            }else {
                parameter.setHeigh(0);
            }

            return parameter;
        }
    }
}
