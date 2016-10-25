package com.qg.kinectdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.adapter.ChatAdapter;
import com.qg.kinectdoctor.emsdk.EMConstants;
import com.qg.kinectdoctor.emsdk.IMManager;
import com.qg.kinectdoctor.model.ChatInfoBean;
import com.qg.kinectdoctor.model.VoiceBean;
import com.qg.kinectdoctor.util.ToastUtil;
import com.qg.kinectdoctor.view.TopbarL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class ChatActivity extends BaseActivity implements EMMessageListener, ChatAdapter.OnItemVoiceClickListener, View.OnLongClickListener, View.OnTouchListener{
    private static final String TAG = ChatActivity.class.getSimpleName();

    public static void startForResult(Activity activity, int requestCode){
        Intent intent = new Intent(activity, ChatActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startForResult(Activity activity, Bundle b,int requestCode){
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra(EMConstants.EXTRA_FROM_CHAT_CONTACT_LIST, b);
        activity.startActivityForResult(intent, requestCode);
    }

    private TopbarL mTopbar;
    private Button mRecordBtn;
    private RecyclerView mRecyclerView;
    private List<VoiceBean> mList;
    private ChatAdapter  mAdapter;

    private ChatInfoBean curChatingBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle b = getIntent().getBundleExtra(EMConstants.EXTRA_FROM_CHAT_CONTACT_LIST);
        curChatingBean = (ChatInfoBean) b.getSerializable(EMConstants.KEY_CHATINFO_BEAN);

        initUI();
        initEM();
        mediaRecorder = new MediaRecorder();
        initRecorder();
//        recordBtn.performClick();
    }

    private void initUI(){
        mTopbar = (TopbarL) findViewById(R.id.chat_topbar);
        initTopbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.chat_recyclerview);
        initRecyclerView();

        mRecordBtn = (Button) findViewById(R.id.chat_record_btn);
        mRecordBtn.setOnClickListener(this);
    }

    private void initTopbar(){
        mTopbar.setLeftImage(true, R.drawable.back_selector, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTopbar.setRightImage(true, R.drawable.person_info_selector, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jump to where
            }
        });

        mTopbar.setCenterText(true, curChatingBean.getPUser().getName(), null);
    }

    private void initRecyclerView(){
        mList = new ArrayList<>();
        mAdapter = new ChatAdapter(this, mList);
        mAdapter.setOnItemVoiceClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
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
            case R.id.chat_record_btn:

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

                        //send record to network



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
        String usernmae = curChatingBean.getIMUsername();
        try {
            List<String> usernames = IMManager.getInstance(this).getFriendsList();

            //get info from our server

        } catch (HyphenateException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onVoiceClick(EMVoiceMessageBody body, int position) {
        //body.getRemoteUrl();
        //body.getLocalUrl();
    }


    private boolean isLongClick = false;

    @Override
    public boolean onLongClick(View view) {
        isLongClick = true;
        return true;
    }


    //handle record button 's gesture action, longclick to record
    //up to check whether is longclick,if true send record, else cancel
    //
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                //give longclick to handle
                return false;
            case MotionEvent.ACTION_UP:
                //check whether is longclick
                break;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
        }
        return false;
    }
}
