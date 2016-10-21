package com.qg.kinectdoctor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }
}
