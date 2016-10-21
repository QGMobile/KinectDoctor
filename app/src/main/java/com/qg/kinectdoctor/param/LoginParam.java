package com.qg.kinectdoctor.param;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class LoginParam extends Param{
    private String username;
    private String password;

    public LoginParam(String username, String password){
        this.username = username;
        this.password = password;
    }
}
