package com.qg.kinectdoctor.ui.register;

import android.app.Activity;

import com.qg.kinectdoctor.util.FormatChecker;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/27.
 */
public class RegisterFirstPresenter implements RegisterFirstContract.Presenter {

    private final RegisterFirstContract.View mRegisterFirstView;

    public RegisterFirstPresenter(RegisterFirstContract.View registerFirstView) {
        mRegisterFirstView = checkNotNull(registerFirstView, "registerFirstView cannot be null!");
        mRegisterFirstView.setPresenter(this);
    }

    @Override
    public void start() {
        // Do nothing
    }

    @Override
    public void next(String phone, String password) {
        mRegisterFirstView.hidePhoneError();
        mRegisterFirstView.hidePasswordError();
        boolean accept = true;
        if (!FormatChecker.isMobile(phone)) {
            mRegisterFirstView.showPhoneError();
            accept = false;
        }
        if (!FormatChecker.isAcceptablePassword(password)) {
            mRegisterFirstView.showPasswordError();
            accept = false;
        }
        if (accept) {
            mRegisterFirstView.showNext(phone, password);
        }
    }

    @Override
    public void login() {
        mRegisterFirstView.showLogin();
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // If it was successfully registered, show login screen.
        if (RegisterFirstActivity.REQUEST_REGISTER == requestCode && Activity.RESULT_OK == resultCode) {
            mRegisterFirstView.showLogin();
        }
    }
}
