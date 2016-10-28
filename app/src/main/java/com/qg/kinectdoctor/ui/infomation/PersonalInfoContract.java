package com.qg.kinectdoctor.ui.infomation;

import com.qg.kinectdoctor.ui.BasePresenter;
import com.qg.kinectdoctor.ui.BaseView;

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
