package com.qg.kinectdoctor.model;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatInfoBean {
    private PUser pUser;
    private int unReadCount;

    public ChatInfoBean(PUser pUser, int unReadCount){
        this.pUser = pUser;
        this.unReadCount = unReadCount;
    }

    public ChatInfoBean(PUser pUser){
        this.pUser = pUser;
        this.unReadCount = 0;
    }

    public void setPUser(PUser pUser){
        this.pUser = pUser;
    }

    public PUser getPUser(){
        return pUser;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }


}
