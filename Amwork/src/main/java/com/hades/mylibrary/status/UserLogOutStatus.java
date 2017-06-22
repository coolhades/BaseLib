package com.hades.mylibrary.status;

/**
 * Created by Hades on 2017/6/21.
 */

public class UserLogOutStatus implements BaseStatus {

    @Override
    public void pay() {
        doLogin();
    }


    @Override
    public void collection() {
        doLogin();
    }


    private void doLogin() {

    }

}
