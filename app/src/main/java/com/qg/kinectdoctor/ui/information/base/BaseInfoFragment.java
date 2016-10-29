package com.qg.kinectdoctor.ui.information.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.BaseFragment;
import com.qg.kinectdoctor.util.ToastUtil;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/29.
 */

public class BaseInfoFragment extends BaseFragment implements BaseInfoContract.View {

    private BaseInfoContract.Presenter mPresenter;

    private TextView mNameText;

    private TextView mAgeText;

    private TextView mSexText;

    private View mAge;

    private View mSex;

    public static BaseInfoFragment newInstance() {
        Bundle args = new Bundle();

        BaseInfoFragment fragment = new BaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(BaseInfoContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.base_info_fragment, container, false);

        mNameText = (TextView) root.findViewById(R.id.name_text);
        mAgeText = (TextView) root.findViewById(R.id.age_text);
        mSexText = (TextView) root.findViewById(R.id.sex_text);

        mAge = root.findViewById(R.id.age);
        mSex = root.findViewById(R.id.sex);

        return root;
    }

    @Override
    public void setName(String name) {
        mNameText.setText(name);
    }

    @Override
    public void setAge(int age) {
        mAgeText.setText(String.valueOf(age));
    }

    @Override
    public void setSex(String sex) {
        mSexText.setText(sex);
    }

    @Override
    public void showError(String error) {
        ToastUtil.showToast(getContext(), error);
    }

    @Override
    public void showEditAge(int age) {
    }

    @Override
    public void showEditSex() {
    }

    @Override
    public void showSuccessEdit() {
        ToastUtil.showToast(getContext(), R.string.edit_success);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
