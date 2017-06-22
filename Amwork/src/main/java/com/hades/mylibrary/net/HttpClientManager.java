package com.hades.mylibrary.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Hades on 16/10/10.
 * 用于初始化 httpClient
 * 单例 默认使用OKHttp
 * 可自定义 client属性
 * 首先创建client
 * 才能使用request
 *
 */

public class HttpClientManager {
    private   OkHttpClient mOkHttpClient;
    private final static int TYPE_HTTP = 0;
    private final static int TYPE_HTTPS = 1;



    public static HttpClientManager getInstance(){
        return NewInstance.instance;
    }

    private static class NewInstance{
        private static HttpClientManager instance = new HttpClientManager();
    }

    private HttpClientManager() {
    }

    /**
    * 创建时间 16/10/10
    * auther Hades
    * 描述  在Application中初始化client
    **/
    @Deprecated
    public void initClient(Context context){
        //初始化
        if (mOkHttpClient == null) {
            initDefaultClient(context);
        }
    }

    /**
     * 创建时间 16/10/10
     * auther Has
     * 描述  覆盖配置接口 需要在网络框架初始化前适配
     * 在  Application中初始化client
     **/
    public void initClient(OkHttpClient client) {
        //覆盖配置
            if (client != null){
                mOkHttpClient = client;
            }
    }

    /**
    * 创建时间 16/10/11
    * auther Hades
    * 描述 默认实现 增加了拦截器 设置了log
     *    需要缓存设置 需要实现自定义client 并注入
    **/
    private void initDefaultClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(interceptor);
        int cacheSize = 100 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        builder.cache(cache);
        mOkHttpClient = builder.build();
    }

    public static OkHttpClient.Builder initClientBuilder(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(interceptor);
        int cacheSize = 100 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        builder.cache(cache);
        return builder;
    }

}
