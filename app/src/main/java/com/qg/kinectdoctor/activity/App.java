package com.qg.kinectdoctor.activity;

import android.app.Application;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class App extends Application{

    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }
}
