package com.hades.mylibrary.utils;

import android.content.Context;


/**
 * 创建时间 2017/2/16
 * auther Hades
 * 重构使用策略模式，默认使用Glide库，可继承ImageLoaderStrategy接口实现新的图片加载库，并在使用前注入策略
 * 统一封装图片加载接口
 **/
public class ImageLoaderUtils {
    //图片默认加载类型
    public static final int IMG_DEFAULT_TYPE = 0;
    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private ImageLoaderStrategy mStrategy;

    public static ImageLoaderUtils getmIntance() {
        return SingleInstance.imageLoaderUtils;
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述
     *
     * @param strategy ImageLoaderStrategy 实现了图片加载库的统一封装
     *                 提供图片加载 缓存清理等接口
     **/
    public void setLoadStrategy(ImageLoaderStrategy strategy) {
        if (null != strategy) {
            mStrategy = strategy;
        }
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述
     *
     * @param mContext  Activity、Fragment、Application...
     * @param parameter 封装的图片信息参数类 此处为拷贝
     **/
    public void loadImage(Context mContext, ImageParameter parameter) {
        mStrategy.loadImage(mContext, parameter);
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述 内部使用了ImageView的Context
     **/
    public void loadImage(ImageParameter parameter) {
        mStrategy.loadImage(parameter);
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述 清除图片缓存
     **/
    public void clearDiskCache(Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    public void clearMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    public void clearAllCache(Context context) {
        mStrategy.clearImageDiskCache(context);
        mStrategy.clearImageMemoryCache(context);
    }


    private ImageLoaderUtils() {
        //默认策略 Glide
        mStrategy = new GlideImageLoaderStrategy();
    }


    private static class SingleInstance {
        private static ImageLoaderUtils imageLoaderUtils = new ImageLoaderUtils();
    }




}
