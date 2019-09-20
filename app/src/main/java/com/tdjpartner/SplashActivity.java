package com.tdjpartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tdjpartner.utils.statusbar.Eyes;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2018/5/4.
 */

public class SplashActivity extends AppCompatActivity {
    public Timer timer = new Timer();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);//解决启动白频；
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Eyes.translucentStatusBar(this,true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

            }
        };
        timer.schedule(task, 3 * 1000 + 200);
    }



}
