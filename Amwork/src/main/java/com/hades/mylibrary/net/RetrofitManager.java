package com.hades.mylibrary.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hades on 2016/11/14.
 * 考虑使用静态类创建Client，目前有空指针错误【mBaseUrl】
 */

public class RetrofitManager {

    OkHttpClient mOkHttpClient;
    Retrofit mRetrofit;
    Retrofit mRxRetrofit;
    Context mContext;

    public String getmBaseUrl() {
        return mBaseUrl;
    }

    String mBaseUrl;

    private RetrofitManager() {
    }

    @Deprecated
    private static class Singleton {
        private static RetrofitManager instance = new RetrofitManager();
    }

    @Deprecated
    public static RetrofitManager getInstance() {
        return Singleton.instance;
    }

    @Deprecated
    //1 关联OKhttp
    public void bindHttpClient(OkHttpClient httpClient, Context context) {
        mOkHttpClient = httpClient;
        mContext = context;
    }

    @Deprecated
    //2
    public void setBaseUrl(String url) {
        mBaseUrl = url;
    }

    @Deprecated
    //3 初始化retrofit [需要设置url后方可使用]
    public Retrofit getDefaultRetrofit() {
        if (null != mBaseUrl) {
            if (mOkHttpClient != null) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .build();
            } else {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            return mRetrofit;
        } else throw new IllegalStateException("Base URL required.");

    }

    @Deprecated
    public Retrofit getRxRetrofit() {

        if (mOkHttpClient != null) {
            mRxRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        } else {
            mRxRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRxRetrofit;
    }

    @Deprecated
    //获取基准域名、应对不标准的情况
    public Retrofit getDefaultRetrofit(String url) {
        if (mOkHttpClient != null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        } else {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

    @Deprecated
    public Retrofit getFit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .build();
        return mRetrofit;
    }


    //    public static String API_URL = "http://demoa.ab-auto-mooc.com/api/";
        public static String API_URL = "http://plat.ab-auto-mooc.com/api/";
//    public static String API_URL = "http://plat.auto-mooc.com/api/";
    private static OkHttpClient.Builder httpClient = initClient();

    private static Retrofit.Builder mBuilder =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());


    private static OkHttpClient.Builder initClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(interceptor);
        return builder;
    }

    /**
     * 创建时间 2017/4/17
     * auther Hades
     * 描述
     *
     * @param builder 自定义的OKHttpClient
     **/
    public static void initHttpClientBuilder(OkHttpClient.Builder builder) {
        httpClient = builder;
    }


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = mBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


}
