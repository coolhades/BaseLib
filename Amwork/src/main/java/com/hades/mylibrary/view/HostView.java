package com.hades.mylibrary.view;

import android.content.Context;
import android.view.View;




/**
* 创建时间 2016/11/10
* auther Hades
* 描述  简单封装 viewPager内部使用
**/
public abstract class HostView {
    protected View mView;

    public View getView() {
        mView = View.inflate(getContext(), getLayoutId(), null);
        initView(mView);
        initData();
        initEvent();
        return mView;
    }

    protected abstract int getLayoutId();
    protected abstract Context getContext();
    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract void initEvent();
}
