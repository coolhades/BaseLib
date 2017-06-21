package com.hades.mylibrary.mvp.presenter;


import com.hades.mylibrary.mvp.interf.IRootModel;
import com.hades.mylibrary.mvp.interf.IRootView;

/**
 * Created by Hades on 16/10/8.
 * Presenter基类
 * 子类需要初始化model
 */
public abstract class BasePresenter<T extends IRootView, M extends IRootModel> implements IRootPresenter<T> {

    protected static final String TAG = "BasePresenter";
    protected T mView;
    private M mModel;

    @Override
    public void attachView(T view) {
        mView = view;

    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }

    protected M getModel() {
        return mModel;
    }

    protected void setmModel(M model){
        mModel = model;
    }
}
