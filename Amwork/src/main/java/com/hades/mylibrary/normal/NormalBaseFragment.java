package com.hades.mylibrary.normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hades on 2016/11/24.
 */

public abstract class NormalBaseFragment extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), container, false);
        init(view, savedInstanceState);
        return view;
    }

    public void init(View view, Bundle savedInstanceState){
        initView(view, savedInstanceState);
        initData();
        initEvent();
    }

    protected abstract int getLayout();
    protected abstract void initView(View view, Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();
}
