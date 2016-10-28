package com.qg.kinectdoctor.result;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class Result {
    public int status = 1;
    public String errMsg = "";

    public boolean isOk(){
        return status == 1;
    }
}
