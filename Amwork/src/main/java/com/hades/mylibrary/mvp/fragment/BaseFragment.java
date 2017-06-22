package com.hades.mylibrary.mvp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.mylibrary.mvp.interf.IRootView;
import com.hades.mylibrary.mvp.presenter.IRootPresenter;


/**
 * Created by Hades on 16/10/8.
 */

public abstract class BaseFragment<P extends IRootPresenter> extends Fragment implements IRootView {
    protected P mPresenter;
    Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        init(view, savedInstanceState);
        return view;
    }

    public void init(View view, Bundle savedInstanceState){
        mPresenter = onLoadPresenter();
        initView(view, savedInstanceState);
        getPresenter().attachView(this);
        initData();
        initEvent();
        getPresenter().start();
    }

    //加载实现类的 布局资源


    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null) {
            detachTings();
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract P onLoadPresenter();
    protected abstract void initView(View view, Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();

    protected boolean isdetach;
    protected void detachTings(){
        isdetach = true;
    }

}
