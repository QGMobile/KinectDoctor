package com.qg.kinectdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.view.MyNumberPicker;

import java.lang.reflect.Field;

/**
 * Created by 攀登者 on 2016/10/27.
 */
public class NewRecordsActivity extends BaseActivity implements NumberPicker.OnValueChangeListener, NumberPicker.OnScrollListener, NumberPicker.Formatter {

    private MyNumberPicker mNumberPicker;

    public static void start(Activity context, int requestCode) {
        Intent intent = new Intent(context, NewRecordsActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_records);

        initUI();
    }

    private void initUI() {
        View view = LayoutInflater.from(this).inflate(R.layout.age_choose, null);
        mNumberPicker = (MyNumberPicker) view.findViewById(R.id.age_choose);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        initMyNumberPicker();
        dialog.show();
    }

    private void initMyNumberPicker() {
        setNumberPickerDividerColor(mNumberPicker);
        mNumberPicker.setFormatter(this);
        mNumberPicker.setOnValueChangedListener(this);
        mNumberPicker.setOnScrollListener(this);
        mNumberPicker.setMaxValue(300);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setValue(20);
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.xiahuaxian)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("tag", "oldValue:" + oldVal + "   ; newValue: " + newVal);
    }

    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING:
                // 手离开之后还在滑动
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                // 不滑动
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                // 滑动中
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) {
            return;
        }
//        case :
//        ;
//            break;
//        default:
//
//            break;
    }
}
