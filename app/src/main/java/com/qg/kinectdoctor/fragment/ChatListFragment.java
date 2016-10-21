package com.qg.kinectdoctor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.adapter.ChatListAdapter;
import com.qg.kinectdoctor.model.ChatInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatListFragment extends BaseFragment{

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
            test();
        }
        return v;
    }

    private void initRecyclerView(){
        mList = new ArrayList<>();
        mAdapter = new ChatListAdapter(getActivity(), mList, R.layout.item_chatlist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getDataFromServer(){

    }

    private void test(){
        ChatInfoBean bean = new ChatInfoBean("测试",10);
        mList.add(bean);
        mAdapter.notifyDataSetChanged();
    }
}
