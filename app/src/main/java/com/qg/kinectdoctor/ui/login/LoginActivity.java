package com.qg.kinectdoctor.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.qg.kinectdoctor.activity.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected LoginFragment newFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected <T extends Fragment> void createPresenter(T fragment) {
        new LoginPresenter((LoginFragment) fragment);
    }

}
