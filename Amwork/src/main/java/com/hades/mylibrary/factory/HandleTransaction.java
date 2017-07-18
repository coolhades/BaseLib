package com.hades.mylibrary.factory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hades.mylibrary.pojo.CodeInfoBean;

/**
 * Created by Hades on 2016/11/3.
 * 路由类
 */
public class HandleTransaction {

    private static class Instance {
        private static HandleTransaction handleTransaction = new HandleTransaction();
    }

    private HandleTransaction() {
    }

    public static HandleTransaction getInstance() {
        return Instance.handleTransaction;
    }


    /**
     * 注意manifest中设置的action是否正确
     **/
    @Deprecated
    public void handleTransaction(CodeInfoBean codeInfoBean, Context context, Bundle bundle) {
        if (!"0".equalsIgnoreCase(codeInfoBean.getNeedLogin())) {
            //需要登录
        }

        String s = TransactionFactory.getInstance().getTargetName(codeInfoBean.getPushCode());
        Intent intent = new Intent();
        intent.setAction(s);
        if (!bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        if (!"".equalsIgnoreCase(codeInfoBean.getPushData())) {
            intent.putExtra("pushData", codeInfoBean.getPushData());
        }

        if ("true".equalsIgnoreCase(codeInfoBean.getTask())) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);

    }

    public void handleTransaction(CodeInfoBean codeInfoBean, IOnStartJumpCallBack jumpCallBack){
        String s = TransactionFactory.getInstance().getTargetName(codeInfoBean.getPushCode());
        Intent intent = new Intent();
        intent.setAction(s);
        if (null != codeInfoBean.getBundle() && !codeInfoBean.getBundle().isEmpty()) {
            intent.putExtras(codeInfoBean.getBundle());
        }

        if (null != codeInfoBean.getPushData() && !"".equalsIgnoreCase(codeInfoBean.getPushData())) {
            intent.putExtra("pushData", codeInfoBean.getPushData());
        }

        if (0 != codeInfoBean.getFlags() ) {
            intent.setFlags(codeInfoBean.getFlags());
        }

        if (null != jumpCallBack){
            jumpCallBack.jump(intent);
        }
    }


}
