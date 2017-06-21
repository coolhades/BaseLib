package com.hades.mylibrary.pojo;

import android.os.Bundle;


/**
* 创建时间 2016/11/17
* auther Hades
* 描述  showCode: 约定了展现组件的code
 *      pushCode
**/
public class CodeInfoBean {
    /**
     * showCode : view_banner
     * pushCode : courseDetial
     * pushData :
     */

    private String showCode;
    private String pushCode;
    private String pushData;
    private Bundle bundle;//附加参数
    private String needLogin;
    private int flags;

    public Bundle getBundle() {
        return bundle;
    }

    public CodeInfoBean setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public CodeInfoBean setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public String getNeedLogin() {
        return needLogin;
    }

    public CodeInfoBean setNeedLogin(String needLogin) {
        this.needLogin = needLogin;
        return this;
    }

    private String task;

    public String getTask() {
        return task;
    }

    public CodeInfoBean setTask(String task) {
        this.task = task;
        return this;
    }

    public String getShowCode() {
        return showCode;
    }

    public CodeInfoBean setShowCode(String showCode) {
        this.showCode = showCode;
        return this;
    }

    public String getPushCode() {
        return pushCode;
    }

    public CodeInfoBean setPushCode(String pushCode) {
        this.pushCode = pushCode;
        return this;
    }

    public String getPushData() {
        return pushData;
    }

    public CodeInfoBean setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }


    public static class Builder{
        private String showCode;//当前view的Code
        private String pushCode;//跳转目标
        private String pushData;
        private Bundle bundle;//附加参数
        private String needLogin;

        private int flags;//Intent Flag

        public Builder setShowCode(String showCode) {
            this.showCode = showCode;
            return this;
        }

        public Builder setPushCode(String pushCode) {
            this.pushCode = pushCode;
            return this;
        }

        public Builder setPushData(String pushData) {
            this.pushData = pushData;
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public Builder setNeedLogin(String needLogin) {
            this.needLogin = needLogin;
            return this;
        }

        public Builder setFlags(int flags) {
            this.flags = flags;
            return this;
        }

        public CodeInfoBean create(){
            CodeInfoBean transactionCode = new CodeInfoBean();
            if (null != showCode)
                transactionCode.setShowCode(showCode);
            if (null != pushCode)
                transactionCode.setPushCode(pushCode);
            if (null != pushData)
                transactionCode.setPushData(pushData);
            if (null != bundle)
                transactionCode.setBundle(bundle);
            if (null != needLogin)
                transactionCode.setNeedLogin(needLogin);
            if (0 != flags)
                transactionCode.setFlags(flags);

            return transactionCode;
        }
    }
}
