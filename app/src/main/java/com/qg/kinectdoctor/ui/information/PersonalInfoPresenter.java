package com.qg.kinectdoctor.ui.information;

import android.support.annotation.NonNull;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 20mDoctorId6/10/29.
 */

class PersonalInfoPresenter implements PersonalInfoContract.Presenter {
    private PersonalInfoContract.View mView;
    private int mDoctorId;

    PersonalInfoPresenter(int doctorId, @NonNull PersonalInfoContract.View view) {
        mDoctorId = doctorId;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadInfo(mDoctorId);
    }

    private void loadInfo(int doctorId) {

    }

    @Override
    public void baseInfo() {
        mView.showBaseInfo(mDoctorId);
    }

    @Override
    public void jobInfo() {
        mView.showJobInfo(mDoctorId);
    }

    @Override
    public void manageAccount() {
        mView.showAccountManage(mDoctorId);
    }

}
