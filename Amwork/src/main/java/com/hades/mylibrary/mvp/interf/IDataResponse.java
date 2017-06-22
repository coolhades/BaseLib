package com.hades.mylibrary.mvp.interf;

/**
 * Created by Hades on 2017/6/22.
 * Model 为P层提供数据
 */

public interface IDataResponse extends IRootModel {
    void onResponseSuccess(String data);
    void onResponseError(String data);
    void onResponseFailer(String data);
}
