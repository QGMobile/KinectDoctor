package com.qg.kinectdoctor.ui.information.job;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/29.
 */

class JobInfoPresenter implements JobInfoContract.Presenter {

    private final JobInfoContract.View mView;

    private final int mDoctorId;

    JobInfoPresenter(int doctorId, JobInfoContract.View view) {
        mDoctorId = doctorId;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadInfo();
    }

    private void loadInfo() {

    }

    @Override
    public void saveJobInfo(String hospital, String department, String jobTitle) {
    }

}
