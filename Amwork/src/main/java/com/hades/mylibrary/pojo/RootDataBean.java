package com.hades.mylibrary.pojo;

/**
 * Gson返回Ret基本格式
 * 成功:ret=1 + 业务数据
 * 失败:ret=0 + err_code + err_msg
 * Created by Hades on 16/10/9.
 * data = List<BaseBean> list;
 */
public class RootDataBean<T> {
    public int status;         //成功-1 失败-0
    public String message;  //成功、错误msg
    public T data;          //成功返回的数据

}
