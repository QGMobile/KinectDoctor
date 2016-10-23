package com.qg.kinectdoctor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.activity.ChatActivity;
import com.qg.kinectdoctor.adapter.ChatListAdapter;
import com.qg.kinectdoctor.emsdk.EMConstants;
import com.qg.kinectdoctor.emsdk.IMCallback;
import com.qg.kinectdoctor.emsdk.IMManager;
import com.qg.kinectdoctor.model.ChatInfoBean;
import com.qg.kinectdoctor.util.ActivityCollector;
import com.qg.kinectdoctor.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatListFragment extends BaseFragment implements ChatListAdapter.OnChatItemClickListener, EMMessageListener, EMContactListener{

    private static final String TAG = ChatListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private List<ChatInfoBean> mList;
    private ChatListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chatlist,null);
        if(v != null){
            mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            initRecyclerView();
            initEM();
            getDataFromServer();

        }
        return v;
    }

    private void initRecyclerView(){
        mList = new ArrayList<>();
        mAdapter = new ChatListAdapter(getActivity(), mList, R.layout.item_chatlist);
        mAdapter.setOnChatItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getDataFromServer(){
        try {
            List<String> username = IMManager.getInstance(getActivity()).getFriendsList();
        } catch (HyphenateException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    private void test(){
        ChatInfoBean bean = new ChatInfoBean( null,10);
        mList.add(bean);
        mAdapter.notifyDataSetChanged();
    }



    private void initEM(){
        //监听消息回调
        IMManager.getInstance(getActivity()).addMessageListener(this);
        //监听联系人回调
        IMManager.getInstance(getActivity()).setContactListener(this);
    }


    private ChatInfoBean curChatingBean = null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EMConstants.REQCODE_START_CHAT){
            Log.d(TAG,"onActivityResult");
            curChatingBean = null;
        }
    }


    @Override
    public void onChatItemClick(View v, int position) {
        ChatInfoBean bean = mList.get(position);
        curChatingBean  = bean;
        ChatActivity.startForResult(getActivity(), EMConstants.REQCODE_START_CHAT);
    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        if(list == null)return;
        for(EMMessage message:list){
            String fromWho = message.getFrom();
            String fromPhone = filterUsernameToPhone(fromWho);

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

    private String filterUsernameToPhone(String username){
        if(username == null)return "";
        return username.replace(EMConstants.DOCTOR_USERNAME_PREFIX,"").replace(EMConstants.PATIENT_USERNAME_PREFIX, "");
    }

    @Override
    public void onContactAdded(String username) {
        //增加了某个联系人

    }

    @Override
    public void onContactDeleted(String username) {
        //被删除时调用

    }

    @Override
    public void onContactInvited(String s, String s1) {

    }

    @Override
    public void onContactAgreed(String s) {

    }

    @Override
    public void onContactRefused(String s) {

    }

    private void showMessage(String text){
        ToastUtil.showToast(getActivity(), text);
    }
}
