package com.qg.kinectdoctor.ui.register;

import com.qg.kinectdoctor.ui.BasePresenter;
import com.qg.kinectdoctor.ui.BaseView;

/**
 * Created by TZH on 2016/10/27.
 */
public class RegisterSecondContract {

    interface View extends BaseView<Presenter> {
        void setUploadingIndicator(boolean active);

        void showError(String error);

        void showSuccess();

        void showPrevious();

        void showLogin();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void register(String hospital, String clinicDepartment, String jobTitle);

        void previous();
    }
}
