package com.hades.mylibrary.viewholder;

import android.content.Context;

import com.hades.mylibrary.pojo.CommenBean;
import com.hades.mylibrary.utils.GsonUtils;

/**
 * Created by Hades on 2016/11/3.
 * Recyclerview中的adapter，data 绑定 view
 */
public class ViewHolderDataLoader {

    private static ViewHolderDataLoader ourInstance = new ViewHolderDataLoader();

    public static ViewHolderDataLoader getInstance() {
        return ourInstance;
    }

    private ViewHolderDataLoader() {
    }

    /**
    * 创建时间 2016/11/8
    * auther Hades
    * 描述  holder 所有holder的基类
     *     BaseBean 封装了基本数据类型
    **/
    public void loadView(RootViewHolder holder, CommenBean bean, Context context) {
        holder.setConfig(GsonUtils.getInstance().toJson(bean.getConfig()), context);
        holder.setCodeInfo(GsonUtils.getInstance().toJson(bean.getCodeInfo()), context);
        //此处需要还原Json后重新解析，因为Gson是强映射，有类型擦除问题
        holder.setData(GsonUtils.getInstance().toJson(bean.getList()), context);
    }


}
