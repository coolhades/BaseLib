package com.hades.mylibrary.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hades.mylibrary.mvp.interf.IRootView;
import com.hades.mylibrary.mvp.presenter.IRootPresenter;
import com.hades.mylibrary.utils.restart.AppStatusConstant;
import com.hades.mylibrary.utils.restart.AppStatusManager;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by Hades on 16/10/8.
 */

public abstract class BaseActivity<P extends IRootPresenter> extends AppCompatActivity implements IRootView {

    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        init(savedInstanceState);
    }

    public void init(Bundle savedInstanceState){
        checkStatus(savedInstanceState);
//        initView(savedInstanceState);

    }


    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract int getLayout();
    protected abstract P onLoadPresenter();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();

    @Override
    protected void onPostResume() {
        super.onPostResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void checkStatus(Bundle savedInstanceState) {
        switch (AppStatusManager.getInstance().getAppStatus()) {
            case AppStatusConstant.STATUS_FORCE_KILLED:
                restartApp(savedInstanceState);
                break;
            case AppStatusConstant.STATUS_NORMAL:
                setUpViewAndData(savedInstanceState);
                break;
        }

    }

    protected  void setUpViewAndData(Bundle savedInstanceState){
        initView(savedInstanceState);
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initData();
        initEvent();
        getPresenter().start();
    }

    protected abstract void restartApp(Bundle savedInstanceState);

}
