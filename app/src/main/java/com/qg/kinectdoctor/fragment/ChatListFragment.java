package com.qg.kinectdoctor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qg.kinectdoctor.R;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatListFragment extends BaseFragment{

    private static final String TAG = ChatListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chatlist,null);
        if(v != null){
            mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            initRecyclerView();
        }
        return v;
    }

    private void initRecyclerView(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
