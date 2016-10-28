package com.qg.kinectdoctor.ui.login;

import android.app.backup.SharedPreferencesBackupHelper;

import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/27.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = checkNotNull(loginView, "loginView cannot be null!");
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAccount();
    }

    private void loadAccount() {
        boolean isRemembered = true;
        if (isRemembered) {
            mLoginView.setPhone("12345");
            mLoginView.setPassword("12345");
        }
    }

    @Override
    public void login(String phone, String password, boolean rememberPassword) {
        LogicImpl.getInstance().login(new LoginParam(phone, password), new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if (onUIThread) {
                    if (!mLoginView.isActive()) {
                        return;
                    }
                    if (result.isOk()) {
                        mLoginView.showMain();
                    } else {
                        mLoginView.showError(result.errMsg);
                    }
                }
            }
        });
    }

    @Override
    public void register() {
        mLoginView.showRegister();
    }
}
