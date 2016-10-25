package com.qg.kinectdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.chat.adapter.message.EMAVoiceMessageBody;
import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.adapter.ChatListAdapter;
import com.qg.kinectdoctor.emsdk.EMConstants;
import com.qg.kinectdoctor.util.ToastUtil;

import java.io.File;
import java.io.IOException;
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

    private Button recordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recordBtn = (Button)findViewById(R.id.record_btn);
        recordBtn.setOnClickListener(this);
        File dir = new File(DEFAUL_OUTPUT_FILE);
        if(!dir.exists()){
            boolean success = dir.mkdirs();
            if(success){
                Log.e(TAG, "create dir success");
            }else{
                Log.e(TAG, "create dir failed");
            }
            Log.e(TAG,"mkdirs");
        }
        Log.e(TAG, "dir exist->"+(dir.exists()));
        mediaRecorder = new MediaRecorder();
        initRecorder();
//        recordBtn.performClick();
    }

    private static final int DEFAULT_AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
    private static final int DEFAULT_OUTPUT_FORMAT =  MediaRecorder.OutputFormat.AMR_NB;
    private static final String DEFAUL_OUTPUT_FILE = Environment.getExternalStorageDirectory()+File.separator+App.getInstance().getPackageName()+ File.separator+"voice";
    private static final int DEFAULT_AUDIO_ENCODER = MediaRecorder.AudioEncoder.AMR_NB;

    private MediaRecorder mediaRecorder;
    private boolean isPrepared = false;

    private void initRecorder(){
        isRecording = false;
        isPrepared = false;
        mediaRecorder.setAudioSource(DEFAULT_AUDIO_SOURCE);
        mediaRecorder.setOutputFormat(DEFAULT_OUTPUT_FORMAT);
        File dir = new File(DEFAUL_OUTPUT_FILE);
        if(!dir.exists()){
            dir.mkdirs();
        }
//        Log.e(TAG,"dir is exist->"+(dir.exists()));
        File file  = new File(DEFAUL_OUTPUT_FILE + File.separator + System.currentTimeMillis()+".arm");
        if(!file.exists()){
            try {
                file.createNewFile();
                Log.e(TAG, "new file");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setAudioEncoder(DEFAULT_AUDIO_ENCODER);
        prepareRecorder();
    }

    private void prepareRecorder(){
        try {
            mediaRecorder.prepare();
            isPrepared = true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,e.getMessage());
        }
    }

    private boolean isRecording = false;

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.record_btn:

                    if(!isRecording) {
                        if(isPrepared) {
                            mediaRecorder.start();
                            showMessage("开始录音");
                            isRecording = true;
                        }else{
                            showMessage("mediaRecorder 未prepare");
                            isRecording = false;
                            prepareRecorder();
                        }
                    }else{
                        mediaRecorder.stop();
                        showMessage("停止录音");
                        mediaRecorder.reset();
                        initRecorder();
                    }
//                    isRecording = !isRecording;
                break;
        }
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
        if(list == null) return;
        for(EMMessage message: list){
            EMVoiceMessageBody voice = (EMVoiceMessageBody) message.getBody();
            
        }
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

    private void showMessage(String text){
        ToastUtil.showToast(this, text);
    }
}
