package com.qg.kinectdoctor.ui.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.qg.kinectdoctor.activity.SingleFragmentActivity;

public class RegisterSecondActivity extends SingleFragmentActivity {

    public static void startForResult(Activity context, int requestCode, String phone, String password) {
        Intent starter = new Intent(context, RegisterSecondActivity.class);
        context.startActivityForResult(starter, requestCode);
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
