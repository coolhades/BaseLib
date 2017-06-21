package com.hades.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;

import java.util.List;

/**
* 创建时间 2016/11/10
* auther Hades
* 描述
**/
public abstract class CommonAdapter<T> extends BaseAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        this.setOnLayoutParams(setLayoutParams());

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new BaseItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(BaseViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });

    }

    protected abstract GridLayoutManager.LayoutParams setLayoutParams();
    protected abstract void convert(BaseViewHolder holder, T t, int position);

}
