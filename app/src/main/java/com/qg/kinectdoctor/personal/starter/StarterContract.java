package com.qg.kinectdoctor.personal.starter;

import com.qg.kinectdoctor.mvp.BasePresenter;
import com.qg.kinectdoctor.mvp.BaseView;

/**
 * Created by TZH on 2016/10/27.
 */

public class StarterContract {

    interface View extends BaseView<Presenter> {
        void showLogin();
        void showRegister();
    }

    interface Presenter extends BasePresenter {
        void login();
        void register();
    }
}
