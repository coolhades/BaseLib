package com.hades.mylibrary.base.net;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.hades.mylibrary.base.ui.base.pojo.RootDataBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Hades on 16/10/9.
 * mBaseUrl 必须先配置 通过获取基准域名进行配置
 */
public  class RootRequest {

    public static String mBaseUrl; //直接类调用设置

    protected Retrofit mRetrofit;

    private final String TAG = "RootRequest";
    private OkHttpClient mOkHttpClient;

    /**
    * 创建时间 16/10/10
    * auther Hades
    * 描述  必须先设置基准域名地址
     *     创建
    **/
    public RootRequest() {
        if (mBaseUrl.isEmpty()){
            return;
        }

        //获取client
        setmOkHttpClient();

        if (mOkHttpClient != null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }else {

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    }


    /**
    * 创建时间 16/10/10
    * auther Hades
    * 描述  获取基准域名时使用
    **/
    public RootRequest(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void setmOkHttpClient() {
        this.mOkHttpClient = HttpClientManager.getInstance().getmOkHttpClient();
    }


    //处理retrofit回调CallBack 并调用ApiCallback相应返回
    protected class RetrofitCallback<T> implements Callback<T> {
        private ApiCallback<T> mCallback;
        public RetrofitCallback(ApiCallback<T> callback) {
            mCallback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            Gson gson = new Gson();
            if(response.isSuccessful()) {
                if(((RootDataBean)response.body()).status ==1) {
                    mCallback.onSuccess( (T) ((RootDataBean) response.body()).data );
                } else {
                    mCallback.onError(((RootDataBean)response.body()).message);
                }
            } else {
                mCallback.onFailure();
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.e(TAG, "api failure,throw=" + t.getMessage());
            t.printStackTrace();
            mCallback.onFailure();
        }
    }

    //api调用回调
    public interface ApiCallback<T> {
        void onSuccess(T ret);        //ret=1时返回
        void onError(String err_msg);   //ret=0时返回
        void onFailure();   //网络请求失败
    }

    //文件下载回调
    public interface FileDownloadCallback {
        void onSuccess();   //下载成功返回
        void onProcess(long fileSizeDownloaded, long fileSize);   //下载进度
        void onFailure();   //网络请求失败
    }

    /**
     * 下载文件
     * @param fileUrl 下载url
     * @param filePath 本地保存path
     * @param callback FileDownloadCallback回调
     */
    public void downloadFile(final String fileUrl, final String filePath, final FileDownloadCallback callback) {
        final ApiStore apiStore = mRetrofit.create(ApiStore.class);

        new AsyncTask<Void, Long, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Call<ResponseBody> call = apiStore.downloadFile(fileUrl);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            new AsyncTask<Void, Void, Void>() {

                                private boolean mWrittenToDisk;

                                @Override
                                protected Void doInBackground(Void... voids) {
                                    mWrittenToDisk = writeResponseBodyToDisk(response.body(), filePath, callback);
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    if(mWrittenToDisk) {
                                        callback.onSuccess();
                                    } else {
                                        callback.onFailure();
                                    }
                                }
                            }.execute();


                        } else {
                            callback.onFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        callback.onFailure();
                    }
                });
                return null;
            }
        }.execute();
    }

    /**
     * responsebody写入文件
     * @param body
     * @param filePath
     * @param callback
     * @return
     */
    private boolean writeResponseBodyToDisk(ResponseBody body, String filePath, FileDownloadCallback callback) {
        try {
            File file = new File(filePath);

            String dir = filePath.substring(0, filePath.lastIndexOf('/'));
            File fileDir = new File(dir);
            if(!fileDir.exists()) {
                fileDir.mkdirs();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    callback.onProcess(fileSizeDownloaded, fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                file.delete();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public interface ApiStore {
        @Streaming
        @GET
        Call<ResponseBody> downloadFile(@Url String fileUrl);
    }
}