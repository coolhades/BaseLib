package com.hades.mylibrary.status;

/**
* 创建时间 2017/6/21
* author Hades
* 描述 用户状态管理 通过注入不同私有云用户状态
**/
public class StatusController {

    private boolean isLogin = false;
    private BaseStatus baseStatus = new UserLogOutStatus();

    public void setBaseStatus(BaseStatus baseStatus) {
        this.baseStatus = baseStatus;
    }

    public boolean isLogin(){
        return isLogin;
    }

    public void pay(){
        baseStatus.pay();
    }

    public void collection(){
        baseStatus.collection();
    }


    public void login(){
        setBaseStatus(new UserLoginStatus());
        isLogin = true;
    }

    public void logout(){
        setBaseStatus(new UserLogOutStatus());
        isLogin = false;
    }


    private StatusController() {
    }

    private static class SingletonInstance {
        private static final StatusController INSTANCE = new StatusController();
    }

    public static StatusController getInstance() {
        return SingletonInstance.INSTANCE;
    }
}