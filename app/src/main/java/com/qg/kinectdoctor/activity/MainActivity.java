package com.qg.kinectdoctor.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.emsdk.IMManager;
import com.qg.kinectdoctor.emsdk.LoginCallback;
import com.qg.kinectdoctor.fragment.ChatListFragment;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.util.ToastUtil;



public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
