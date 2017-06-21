package com.hades.mylibrary.pojo;

/**
 * Created by Hades on 16/10/17.
 * 抽象数据层
 */

public class CommenBean<B,C> {
    private C list;
    private B config;//对象解析为Map 方便后续扩展
    private CodeInfoBean codeInfo;

    public C getList() {
        return list;
    }

    public void setList(C list) {
        this.list = list;
    }

    public B getConfig() {
        return config;
    }

    public void setConfig(B config) {
        this.config = config;
    }

    public CodeInfoBean getCodeInfo() {
        return codeInfo;
    }

    public void setCodeInfo(CodeInfoBean codeInfo) {
        this.codeInfo = codeInfo;
    }
}
