package com.hades.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;


public abstract class BaseDialog extends Dialog {

    Context context;

    public BaseDialog(Context context) {
        super(context);
        this.context = context;
    }

    public BaseDialog(Context context, int mstyle){
        super(context, mstyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();

    protected abstract void initEvent();

    public void colseDialog() {
        BaseDialog.this.dismiss();
    }

}
