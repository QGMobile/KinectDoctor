package com.qg.kinectdoctor.personal.infomation;

import com.qg.kinectdoctor.mvp.BasePresenter;
import com.qg.kinectdoctor.mvp.BaseView;

/**
 * Created by TZH on 2016/10/26.
 */

public class PersonalInfoContract {
    interface View extends BaseView<Presenter> {
        void showRegister();
    }

    interface Presenter extends BasePresenter {
    }
}
