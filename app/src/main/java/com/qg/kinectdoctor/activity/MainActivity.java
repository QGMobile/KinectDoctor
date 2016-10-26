package com.qg.kinectdoctor.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.emsdk.IMManager;
import com.qg.kinectdoctor.emsdk.LoginCallback;
import com.qg.kinectdoctor.fragment.ChatListFragment;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.util.ToastUtil;



public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEM();
    }

    public static void test(final Context context, final TextView tv){
        LoginParam param = new LoginParam("15521265445","qgmobile");
        LogicImpl.getInstance().login(param, new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if(onUIThread){
                    if(result.isOk()){
                        ToastUtil.showToast(context, "访问网络成功");
                        tv.setText(result.errMsg);
                    }else{
                        ToastUtil.showToast(context, result.errMsg);
                    }
                }
            }
        });
    }

    public void loginEM(){
        String phone = "12345678901";
        IMManager.getInstance(this).login(phone, new LoginCallback() {
            @Override
            public void onSuccess() {
                Log.e(MainActivity.TAG, "loginSuccess");
//                try {
//                    EMClient.getInstance().contactManager().addContact("d12345678901","");
//                    Log.e(TAG, "addContact");
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                }
                ChatListFragment fragment = new ChatListFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(android.R.id.content, fragment);
                transaction.commit();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
}
