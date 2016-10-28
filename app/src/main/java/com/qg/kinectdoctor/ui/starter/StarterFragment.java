package com.qg.kinectdoctor.ui.starter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.BaseFragment;
import com.qg.kinectdoctor.ui.login.LoginActivity;

import static com.qg.kinectdoctor.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/27.
 */

public class StarterFragment extends BaseFragment implements StarterContract.View {

    private StarterContract.Presenter mPresenter;

    public static StarterFragment newInstance() {
        return new StarterFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(StarterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.starter_fragment, container, false);

        // Set up login button.
        View lb = root.findViewById(R.id.login);
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });

        // Set up register button.
        View rb = root.findViewById(R.id.register);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.register();
            }
        });

        return root;
    }

    @Override
    public void showLogin() {
        LoginActivity.start(getContext());
    }

    @Override
    public void showRegister() {
//        RegisterActivity.start(getContext());
    }

}
