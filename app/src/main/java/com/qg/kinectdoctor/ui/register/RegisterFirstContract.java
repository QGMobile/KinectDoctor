package com.qg.kinectdoctor.ui.register;

import com.qg.kinectdoctor.ui.BasePresenter;
import com.qg.kinectdoctor.ui.BaseView;

/**
 * Created by TZH on 2016/10/27.
 */
public class RegisterFirstContract {

    interface View extends BaseView<Presenter> {
        void showPhoneError();

        void hidePhoneError();

        void showPasswordError();

        void hidePasswordError();

        void setPasswordVisibility(boolean visible);

        void showLogin();

        void showNext(String phone, String password);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void next(String phone, String password);

        void login();

        void result(int requestCode, int resultCode);
    }
}
