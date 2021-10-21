package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.AppAplication;
import com.tdjpartner.MainTabActivity;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.ImageUploadOk;
import com.tdjpartner.mvp.presenter.HomeNewPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ToastUtils;
import com.tdjpartner.utils.appupdate.DownloadManager;
import com.tdjpartner.utils.appupdate.OnDownloadListener;
import com.tdjpartner.utils.appupdate.UpdateConfiguration;
import com.tdjpartner.utils.cache.SPUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeNewActivity extends BaseActivity<HomeNewPresenter> implements OnDownloadListener {

    @BindView(R.id.tv_version)
    TextView tv_version;
    private AppVersion appVersion;

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    protected HomeNewPresenter loadPresenter() {
        return new HomeNewPresenter();
    }

    @Override
    protected void initData() {
        tv_version.setText("V " + GeneralUtils.getVersionName());
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);
        if (getAppVersion() == null || (!appVersion.getVersion().equals(GeneralUtils.getAppVersionName(AppAplication.getAppContext())))) {
            mPresenter.version_check();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_new_activity;
    }

    @OnClick({R.id.shichangbu,R.id.zongjinban,R.id.wuliubu,R.id.shangguanbu})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.zongjinban:
                String token = (String) SPUtils.getInstance().get("token", "");
                if (token == null || token.isEmpty()){
                    WebViewToActivity.lunchAc("login.html",this);
                }else {
                    WebViewToActivity.lunchAc("hourData.html",this);
                }
                break;
            case R.id.shichangbu:
                if (UserUtils.getInstance().getLoginBean() != null) {//首页
                        Intent intent = new Intent(this, MainTabActivity.class);
                        startActivity(intent);
                    } else {//登录
                        Intent intent = new Intent(this, V3LoginActivity.class);
                        startActivity(intent);
                    }
                break;
            case R.id.wuliubu:
            case R.id.shangguanbu:
                ToastUtils.showToast(this,"暂未开放");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (data == null || data.getData() == null) return;
                try {
                    File file = File.createTempFile("Avatar_", data.getType() == null ? null : GeneralUtils.getSuffix(data.getType()), getFilesDir());
                    try (InputStream inputStream = getContentResolver().openInputStream(data.getData());
                         OutputStream outputStream = new FileOutputStream(file)) {
                        if (inputStream.available() > 2 * 1024 * 1024) {
                            GeneralUtils.showToastshort("文件尺寸不能超过2M");
                            return;
                        } else {
                            byte[] bytes = new byte[1024];
                            int n;
                            while ((n = inputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, n);
                            }
                            EventBus.getDefault().post(new ImageUploadOk(file.toString()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (file.exists()) file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                LogUtils.i(Matisse.obtainPathResult(data).get(0));
                EventBus.getDefault().post(new ImageUploadOk(Matisse.obtainPathResult(data).get(0)));
            }
        }
    }

    public void version_check_success(AppVersion appVersion) {
        setAppVersion(appVersion);
        if (appVersion != null) {
            if (!appVersion.getVersion().equals(GeneralUtils.getAppVersionName(AppAplication.getAppContext()))) {


                UpdateConfiguration configuration = new UpdateConfiguration()
                        //输出错误日志
                        .setEnableLog(true)
                        //设置自定义的下载
                        //.setHttpManager()
                        //下载完成自动跳动安装页面
                        .setJumpInstallPage(true)
                        //支持断点下载
                        .setBreakpointDownload(true)
                        //设置是否显示通知栏进度
                        .setShowNotification(true)
                        //设置强制更新
                        .setForcedUpgrade(true)
                        //设置下载过程的监听
                        .setOnDownloadListener(this);

                DownloadManager manager = DownloadManager.getInstance(getContext());
                manager.setApkName("partner.apk")
                        .setApkUrl(appVersion.getUrl())
                        .setSmallIcon(R.mipmap.icon)
                        .setShowNewerToast(true)
                        .setConfiguration(configuration)
                        .setDownloadPath(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q ? Environment.getExternalStorageDirectory() + "/AppUpdate" : getCacheDir().toString())
                        .setApkVersionCode(2)
                        .setApkVersionName(appVersion.getVersion())
                        .setApkSize("" + appVersion.getSize())
                        .setApkDescription(appVersion.getRemark())
                        .download();

            }
        }

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void error(Exception e) {

    }
}
