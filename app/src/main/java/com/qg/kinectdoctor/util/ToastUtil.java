package com.qg.kinectdoctor.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.qg.kinectdoctor.activity.App;
import com.qg.kinectdoctor.result.Result;

/**
 * Created by ZH_L on 2016/10/22.
 */
public class ToastUtil {

    private static Toast mToast = null;

    public static void showToast(Context context, String str){
        if(mToast == null){
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(str);
        }
        mToast.show();
    }

    public static void showToast(Context context, @StringRes int strId){
        showToast(context, context.getResources().getString(strId));
    }

    public static void showResultErrorToast(Context context, Result r){
        showToast(context, r.errMsg);
    }
}
