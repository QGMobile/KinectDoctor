package com.qg.kinectdoctor.ui.register;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/27.
 */
public class RegisterSecondPresenter implements RegisterSecondContract.Presenter {

    private final RegisterSecondContract.View mRegisterSecondView;

    private final String mPhone;

    private final String mPassword;

    public RegisterSecondPresenter(RegisterSecondContract.View registerSecondView, String phone, String password) {
        mPhone = checkNotNull(phone);
        mPassword = checkNotNull(password);
        mRegisterSecondView = checkNotNull(registerSecondView, "registerFirstView cannot be null!");
        mRegisterSecondView.setPresenter(this);
    }

    @Override
    public void start() {
        // Do nothing
    }

    @Override
    public void register(String hospital, String clinicDepartment, String jobTitle) {
        mRegisterSecondView.setUploadingIndicator(true);
//        LogicImpl.getInstance().login();
    }

    @Override
    public void previous() {
        mRegisterSecondView.showPrevious();
    }
}
