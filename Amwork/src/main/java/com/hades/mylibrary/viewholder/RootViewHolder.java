package com.hades.mylibrary.viewholder;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Hades on 2016/11/3.
 * 2017/5/9 增加 setCodeInfo
 */

public  class RootViewHolder extends RecyclerView.ViewHolder {

    public RootViewHolder(View itemView) {
        super(itemView);
        initview(itemView);
    }
    protected void initview(View view){

    }
    public void setConfig(String config, Context context){

    }

    public void setCodeInfo(String codeInfo, Context context){

    }

    public void setData(String data, Context context){
        //包含了 blocktype

    }






}
