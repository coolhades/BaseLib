package com.hades.mylibrary.mvp.interf;

/**
 * Created by Hades on 2017/6/22.
 * P层为View层提供数据
 */

public interface IPresenterResponse extends IRootView {
    void onSuccess(String data);
    void onError(String data);
    void onFailer(String data);
}
