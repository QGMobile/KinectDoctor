package com.qg.kinectdoctor.ui.login;

import com.qg.kinectdoctor.activity.App;
import com.qg.kinectdoctor.emsdk.IMManager;
import com.qg.kinectdoctor.emsdk.LoginCallback;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.model.DUser;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.util.FormatChecker;

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
            mLoginView.setPhone("13549991585");
            mLoginView.setPassword("qgmobile");
        }
    }

    @Override
    public void login(final String phone, String password, final boolean rememberPassword) {
        if (!FormatChecker.isMobile(phone) || !FormatChecker.isAcceptablePassword(password)) {
            mLoginView.showInputError();
            return;
        }
        LogicImpl.getInstance().login(new LoginParam(phone, password), new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if (!result.isOk() || !onUIThread) {
                    return;
                }
                final DUser dUser = result.getdUser();
                IMManager.getInstance(App.getInstance()).login(phone, new LoginCallback() {
                    @Override
                    public void onSuccess() {
                        if (mLoginView.isActive()) {
                            mLoginView.showMain(dUser);
                        }
                    }

                    @Override
                    public void onError(String errorMsg) {
                        if (mLoginView.isActive()) {
                            mLoginView.showError(errorMsg);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void register() {
        mLoginView.showRegister();
    }
}
