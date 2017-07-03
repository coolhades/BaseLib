package com.hades.mylibrary.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hades on 2016/11/14.
 * 考虑使用静态类创建Client
 */

public class RetrofitManager {

    public static String API_URL = "";
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
