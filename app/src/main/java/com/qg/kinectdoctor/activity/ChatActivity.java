package com.qg.kinectdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.qg.kinectdoctor.adapter.ChatListAdapter;
import com.qg.kinectdoctor.emsdk.EMConstants;

import java.util.List;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class ChatActivity extends BaseActivity implements EMMessageListener{
    private static final String TAG = ChatActivity.class.getSimpleName();

    public static void startForResult(Activity activity, int requestCode){
        Intent intent = new Intent(activity, ChatActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(EMConstants.REQCODE_START_CHAT);
    }

    private void initEM(){

    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
}
