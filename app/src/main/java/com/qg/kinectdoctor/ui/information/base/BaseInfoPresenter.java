package com.qg.kinectdoctor.ui.information.base;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/29.
 */
public class BaseInfoPresenter implements BaseInfoContract.Presenter {

    private BaseInfoContract.View mView;

    private final int mDoctorId;

    public BaseInfoPresenter(int doctorId, BaseInfoContract.View view) {
        mDoctorId = doctorId;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void saveBaseInfo(String name, int age, String sex) {
        mView.showSuccessEdit();
    }
}
