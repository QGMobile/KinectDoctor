package com.qg.kinectdoctor.activity;


import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.ChatListFragment;
import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.util.ToastUtil;



public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ChatListFragment fragment = new ChatListFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(android.R.id.content, fragment);
//        transaction.commit();

        test(this, (TextView)findViewById(R.id.tv));
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
}
