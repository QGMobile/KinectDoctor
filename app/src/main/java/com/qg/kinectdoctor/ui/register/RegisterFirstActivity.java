package com.qg.kinectdoctor.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.activity.BaseActivity;
import com.qg.kinectdoctor.activity.SingleFragmentActivity;
import com.qg.kinectdoctor.util.ActivityUtils;

public class RegisterFirstActivity extends SingleFragmentActivity {

    public static final int REQUEST_FINISH_TOGETHER = 1;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterFirstActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected Fragment newFragment() {
        return RegisterFirstFragment.newInstance();
    }

    @Override
    protected <T extends Fragment> void createPresenter(T fragment) {
        new RegisterFirstPresenter((RegisterFirstContract.View) fragment);
    }
}
