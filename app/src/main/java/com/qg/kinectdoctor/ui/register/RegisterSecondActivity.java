package com.qg.kinectdoctor.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.qg.kinectdoctor.activity.SingleFragmentActivity;

public class RegisterSecondActivity extends SingleFragmentActivity {

    public static String ARGS_PASSWORD = "password";
    public static String ARGS_PHONE = "phone";

    public static void startForResult(Activity context, int requestCode, String phone, String password) {
        Intent starter = new Intent(context, RegisterSecondActivity.class);
        starter.putExtra(ARGS_PHONE, phone);
        starter.putExtra(ARGS_PASSWORD, password);
        context.startActivityForResult(starter, requestCode);
    }

    private String mPhone;
    private String mPassword;

    @Override
    protected void preCreatePresenter() {
        mPhone = getIntent().getStringExtra(ARGS_PHONE);
        mPassword = getIntent().getStringExtra(ARGS_PASSWORD);
    }

    @Override
    protected Fragment newFragment() {
        return RegisterSecondFragment.newInstance();
    }

    @Override
    protected <T extends Fragment> void createPresenter(T fragment) {
        new RegisterSecondPresenter((RegisterSecondContract.View) fragment, mPhone, mPassword);
    }
}
