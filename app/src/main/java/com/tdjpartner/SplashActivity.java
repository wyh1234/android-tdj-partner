package com.tdjpartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tdjpartner.ui.activity.LoginActivity;
import com.tdjpartner.utils.cache.DataUtils;
import com.tdjpartner.utils.cache.UserUtils;
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
        Eyes.translucentStatusBar(this, true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (DataUtils.getInstance().getFirstStartup()) {//后续登录
                    if (UserUtils.getInstance().getLoginBean() != null) {//首页
                        Intent intent = new Intent(SplashActivity.this, MainTabActivity.class);
                        startActivity(intent);
                        finish();

                    } else {//登录
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                } else {//首次登陆显示广告屏
                    Intent intent = new Intent(SplashActivity.this, PageGuideActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        };
        timer.schedule(task, 3 * 1000 + 200);
    }


}
