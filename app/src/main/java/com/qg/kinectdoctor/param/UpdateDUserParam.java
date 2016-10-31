package com.qg.kinectdoctor.param;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class UpdateDUserParam extends Param{
    private String phone;
    private String password;

    public UpdateDUserParam(String phone, String password){
        this.phone = phone;
        this.password = password;
    }
}
