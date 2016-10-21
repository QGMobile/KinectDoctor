package com.qg.kinectdoctor.test;

import android.content.Context;
import android.widget.TextView;

import com.qg.kinectdoctor.logic.LogicHandler;
import com.qg.kinectdoctor.logic.LogicImpl;
import com.qg.kinectdoctor.param.LoginParam;
import com.qg.kinectdoctor.result.LoginResult;
import com.qg.kinectdoctor.util.ToastUtil;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class Test {

    public static void test(final Context context,final TextView tv){
        LoginParam param = new LoginParam("13568908156","123456");
        LogicImpl.getInstance().login(param, new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if(onUIThread){
                    if(result.isOk()){
                        ToastUtil.showToast(context, "访问网络成功");
                        tv.setText(result.getStr());
                    }else{
                        ToastUtil.showToast(context, result.errMsg);
                    }
                }
            }
        });
    }
}
