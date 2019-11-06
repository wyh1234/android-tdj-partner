package com.tdjpartner;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.tdjpartner.utils.Density;
import com.tdjpartner.utils.GeneralUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.zhihu.matisse.internal.utils.UIUtils;

public class AppAplication extends Application {
    private static Application app = null;
    private static long mMainThreadId;//主线程id
    private static Handler mHandler;//主线程Handler
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Density.setDensity(this);
        app = this;
        initX5();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();

        UMConfigure.init(this,"5db927390cafb2661b00069b"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wxf2a72e872e154fd3", "d4d60cdb8106906a4572c30f55e5c186");
        PlatformConfig.setQQZone("1109665317", "KkdvL0CH53fsPSLb");

    }
    /**
     * 获取Application的Context
     **/
    public static Context getAppContext() {
        if (app == null)
            return null;
        return app.getApplicationContext();
    }
    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);
        //x5内核初始化接口//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.initX5Environment(getApplicationContext(),  new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }
            @Override
            public void onCoreInitFinished() {
            }
        });
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }
    public static Handler getMainThreadHandler() {
        return mHandler;
    }
}
