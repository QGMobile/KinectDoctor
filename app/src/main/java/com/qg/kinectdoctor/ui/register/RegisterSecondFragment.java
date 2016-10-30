package com.qg.kinectdoctor.ui.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.BaseFragment;
import com.qg.kinectdoctor.util.ToastUtil;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/27.
 */
public class RegisterSecondFragment extends BaseFragment implements RegisterSecondContract.View {

    private RegisterSecondContract.Presenter mPresenter;

    private TextView mHospital;

    private TextView mClinicDepartment;

    private TextView mJobTitle;

    private View mRegisterButton;

    public static RegisterSecondFragment newInstance() {
        return new RegisterSecondFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(RegisterSecondContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.register_second_fragment, container, false);
        mHospital = (TextView) root.findViewById(R.id.hospital_input);
        mClinicDepartment = (TextView) root.findViewById(R.id.clinic_department_input);
        mJobTitle = (TextView) root.findViewById(R.id.job_title_input);

        // Set up register button.
        mRegisterButton = root.findViewById(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.register(
                        mHospital.getText().toString(),
                        mClinicDepartment.getText().toString(),
                        mJobTitle.getText().toString()
                );
            }
        });

//        // Set up clinic department spinner.
//        View cds = root.findViewById(R.id.clinic_department_spinner);
//        cds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        return root;
    }

    @Override
    public void setUploadingIndicator(boolean active) {
        if (active) {
            mRegisterButton.setEnabled(false);
        } else {
            mRegisterButton.setEnabled(true);
        }
    }

    @Override
    public void showError(String error) {
        ToastUtil.showToast(getContext(), error);
    }

    @Override
    public void showEmptyError() {
        ToastUtil.showToast(getContext(), getString(R.string.forbid_empty_input));
    }

    @Override
    public void showSuccess() {
        ToastUtil.showToast(getContext(), getString(R.string.register_success));
    }

    @Override
    public void showPrevious() {
        getActivity().finish();
    }

    @Override
    public void showLogin() {
        FragmentActivity host = getActivity();
        host.setResult(Activity.RESULT_OK);
        host.finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
