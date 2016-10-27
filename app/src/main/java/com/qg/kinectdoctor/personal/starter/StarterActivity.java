package com.qg.kinectdoctor.personal.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.util.ActivityUtils;

public class StarterActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, StarterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_activity);

        StarterFragment starterFragment = (StarterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (starterFragment == null) {
            starterFragment = StarterFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    starterFragment, R.id.contentFrame);
        }

        new StarterPresenter(starterFragment);
    }
}
