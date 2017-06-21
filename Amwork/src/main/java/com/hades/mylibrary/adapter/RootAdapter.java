package com.hades.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.corelib.AmFactory;
import com.hades.corelib.LayoutInflaterFactory;
import com.hades.corelib.MAPTYPE;
import com.hades.mylibrary.R;
import com.hades.mylibrary.pojo.CommenBean;
import com.hades.mylibrary.viewholder.EmptyHolder;
import com.hades.mylibrary.viewholder.RootViewHolder;
import com.hades.mylibrary.viewholder.ViewHolderDataLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hades on 2016/11/4.
 */

public abstract class RootAdapter extends RecyclerView.Adapter<RootViewHolder> {

    public List<CommenBean> rootDataList;
    //传进来几个ViewHolder 全部加入list
    public List<String> holdertypelist;//已去掉重复项
    Context mContext;

    public RootAdapter(List<CommenBean> data, Context context) {
        this.rootDataList = data;
        this.mContext = context;
        holdertypelist = new ArrayList<>();
        initViewType();
    }

    //待优化
    private void initViewType() {
        for (int i = 0; i < rootDataList.size(); i++) {
            holdertypelist.add(rootDataList.get(i).getCodeInfo().getShowCode());
        }
//      去掉重复的showCode 剩下的就是item的type
        for (int i = 0; i < holdertypelist.size(); i++)  //外循环是循环的次数
        {
            for (int j = holdertypelist.size() - 1; j > i; j--)  //内循环是 外循环一次比较的次数
            {
                if (holdertypelist.get(i).equalsIgnoreCase(holdertypelist.get(j))) {
                    holdertypelist.remove(j);
                }
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        //循环遍历每个position的type，和datalist的所有type对比，选出index值作为type的flag
        String type = rootDataList.get(position).getCodeInfo().getShowCode();
        return holdertypelist.indexOf(type);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RootViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据Viewtype【服务器返回的Viewtype转成的int值】
//        获取注册时关联的
        if (!AmFactory.getInstance().getHoldermap().containsKey(holdertypelist.get(viewType))
                || (LayoutInflaterFactory.getInstance().inflaterView(holdertypelist.get(viewType), parent) )==null
                ) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_errorsetting_ly, parent, false);
            EmptyHolder holder = new EmptyHolder(view);
            return holder;
        }

        try {
            return (RootViewHolder) AmFactory.getInstance()
                    .createHolder(holdertypelist.get(viewType), MAPTYPE.HOLDERMAP)
                    .newInstance(LayoutInflaterFactory.getInstance().inflaterView(holdertypelist.get(viewType), parent));
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RootViewHolder holder, int position) {
        //Viewholder 绑定单例
        ViewHolderDataLoader.getInstance().loadView(holder, rootDataList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return rootDataList.size();
    }



}
