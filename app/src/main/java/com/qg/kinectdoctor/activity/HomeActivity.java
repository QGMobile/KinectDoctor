package com.qg.kinectdoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.ChatListFragment;
import com.qg.kinectdoctor.fragment.MeFragment;
import com.qg.kinectdoctor.fragment.MessageFragment;
import com.qg.kinectdoctor.fragment.PatientFragment;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.model.DUser;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;

/**
 * Created by 攀登者 on 2016/10/28.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    public static final String ACTION_CHECK_UNREADMSG = "com.qg.onlinemedicineforpatient.ACTION_CHECK_UNREADMSG";

    private static final String TAG = "HomeActivity";
    private static final int REQUEST_BUY_VIP = 0xff;
    private PatientFragment mPatientFragment;
    private ChatListFragment mMessageFragment;
    private MeFragment mMeFragment;
    private final static int RECORDS_DETAIL = 0; // 病历详情
    private final static int NEW_RECORDS = 1; // 创建病历

    private ImageButton pacient, message, me;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        test();
//        initViews();
//        fragmentManager = getSupportFragmentManager();
//        setTabSelection(1);
//        setTabSelection(2);
//        setTabSelection(0);
    }

    DUser dUser;
    private void test() {
        LoginParam param = new LoginParam("13549991585", "qgmobile");
        LogicImpl.getInstance().login(param, new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if (result.isOk() && onUIThread && result.status == 1) {
                    App.getInstance().setUser(result.getdUser());
                    Log.e(TAG, result.getdUser().toString());
                    initViews();
                    fragmentManager = getSupportFragmentManager();
                    setTabSelection(1);
                    setTabSelection(2);
                    setTabSelection(0);
                } else if (!result.isOk() && onUIThread) {
                    Toast.makeText(HomeActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        pacient = (ImageButton) findViewById(R.id.pacient);
        message = (ImageButton) findViewById(R.id.message);
        me = (ImageButton) findViewById(R.id.me);
        pacient.setImageResource(R.drawable.pacient_click);
        message.setImageResource(R.drawable.me_normal);
        me.setImageResource(R.drawable.me_normal);
        pacient.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pacient:
                setTabSelection(0);
                break;
            case R.id.message:
                setTabSelection(1);
                break;
            case R.id.me:
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。
     */
    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                pacient.setImageResource(R.drawable.pacient_click);
                if (mPatientFragment == null) {
                    mPatientFragment = new PatientFragment();
                    transaction.add(R.id.fragmentlayout, mPatientFragment);
                } else {
                    transaction.show(mPatientFragment);
                }
                break;
            case 1:
                message.setImageResource(R.drawable.message_click_null);
                if (mMessageFragment == null) {
                    mMessageFragment = new ChatListFragment();
                    transaction.add(R.id.fragmentlayout, mMessageFragment);
                } else {
                    transaction.show(mMessageFragment);
                }
                break;
            case 2:
                me.setImageResource(R.drawable.me_click);
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    transaction.add(R.id.fragmentlayout, mMeFragment);
                } else {
                    transaction.show(mMeFragment);
                }
                break;
            default:

                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        pacient.setImageResource(R.drawable.pacient_normal);
        message.setImageResource(R.drawable.message_normal_null);
        me.setImageResource(R.drawable.me_normal);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mPatientFragment != null) {
            transaction.hide(mPatientFragment);
        }
        if (mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case RECORDS_DETAIL:
                mPatientFragment.initData();
                break;
            case NEW_RECORDS:
                mPatientFragment.initData();
                break;
            default:
                break;
        }
    }
}