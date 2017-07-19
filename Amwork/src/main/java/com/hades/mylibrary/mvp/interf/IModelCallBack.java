package com.hades.mylibrary.mvp.interf;

/**
 * Created by Hades on 2017/6/29.
 */

public interface IModelCallBack extends IRootModel {
    void onSuccess(String data);
    void onFailed(String message);
}
