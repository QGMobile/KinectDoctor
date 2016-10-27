package com.qg.kinectdoctor.activity;


import android.os.Bundle;

import com.qg.kinectdoctor.R;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toFragment();
    }


    private void toFragment() {
        ChatListFragment fragment = new ChatListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }
}
