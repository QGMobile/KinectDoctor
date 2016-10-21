package com.qg.kinectdoctor.model;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class RcStage {
    private int id;
    private int mrId;//病历id，所属病历
    private int num;//所属阶段
    private Action action;//阶段动作
    private float matchValue;//匹配数值

    public RcStage(){};

    public RcStage(int mrId, int num, Action action, float matchValue) {
        this.mrId = mrId;
        this.num = num;
        this.action = action;
        this.matchValue = matchValue;
    }

    public int getMrId() {
        return mrId;
    }

    public void setMrId(int mrId) {
        this.mrId = mrId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public float getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(float matchValue) {
        this.matchValue = matchValue;
    }
}
