package com.qg.kinectdoctor.model;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatInfoBean {
    private String name;
    private int unReadCount;

    public ChatInfoBean(String name, int unReadCount){
        this.name = name;
        this.unReadCount = unReadCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }
}
