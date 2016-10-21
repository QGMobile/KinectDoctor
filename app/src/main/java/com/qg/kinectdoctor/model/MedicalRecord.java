package com.qg.kinectdoctor.model;

import android.media.MediaRecorder;

import java.util.ArrayList;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class MedicalRecord {
    private int id;
    private int puserId;//所属病人
    private int duserId;//所属医生
    private int age;
    private String pname;//病患名
    private  int sex;
    private String pphone;//用户手机
    private String birth;
    private String dname;//医生名
    private String dphone;//医生手机
    private String hospital;//医院
    private String department;//科室
    private String setTime;//建立时间
    private String condition;//病况
    private ArrayList<String> allergicDrug;//过敏药物
    private ArrayList<RcStage> rcStages;//康复阶段

    public MedicalRecord(){};

    public MedicalRecord(int puserId, int duserId, int age, String pname, int sex, String pphone, String birth, String dname, String dphone, String hospital, String department, String setTime, String condition, ArrayList<String> allergicDrug, ArrayList<RcStage> rcStages) {
        this.puserId = puserId;
        this.duserId = duserId;
        this.age = age;
        this.pname = pname;
        this.sex = sex;
        this.pphone = pphone;
        this.birth = birth;
        this.dname = dname;
        this.dphone = dphone;
        this.hospital = hospital;
        this.department = department;
        this.setTime = setTime;
        this.condition = condition;
        this.allergicDrug = allergicDrug;
        this.rcStages = rcStages;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuserId() {
        return puserId;
    }

    public void setPuserId(int puserId) {
        this.puserId = puserId;
    }

    public int getDuserId() {
        return duserId;
    }

    public void setDuserId(int duserId) {
        this.duserId = duserId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ArrayList<String> getAllergicDrug() {
        return allergicDrug;
    }

    public void setAllergicDrug(ArrayList<String> allergicDrug) {
        this.allergicDrug = allergicDrug;
    }

    public ArrayList<RcStage> getRcStages() {
        return rcStages;
    }

    public void setRcStages(ArrayList<RcStage> rcStages) {
        this.rcStages = rcStages;
    }
}
