package com.tdjpartner;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.adapter.MainTabAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.model.ImageUploadOk;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.MainTabPresenter;
import com.tdjpartner.ui.fragment.ClientFragment;
import com.tdjpartner.ui.fragment.HomepageFragment;
import com.tdjpartner.ui.fragment.IronIndexFragment;
import com.tdjpartner.ui.fragment.MenberHomepageFragment;
import com.tdjpartner.ui.fragment.MyFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.appupdate.ApkUtil;
import com.tdjpartner.utils.appupdate.DownloadManager;
import com.tdjpartner.utils.appupdate.OnDownloadListener;
import com.tdjpartner.utils.appupdate.UpdateConfiguration;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.bottombar.BottomBarItem;
import com.tdjpartner.widget.bottombar.BottomBarLayout;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainTabActivity extends BaseActivity<MainTabPresenter> implements OnDownloadListener {
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;
    private List<BaseFrgment> mFragmentList;
    private MainTabAdapter mainTabAdapter;
    private AppVersion appVersion;

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    protected MainTabPresenter loadPresenter() {
        return new MainTabPresenter();
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        if (UserUtils.getInstance().getLoginBean().getGrade() != 3) {
//            mFragmentList.add(new HomepageFragment());//非创客
            mFragmentList.add(new IronIndexFragment());//铁军
        }else {
            mFragmentList.add(new MenberHomepageFragment());//创客
        }

        mFragmentList.add(new ClientFragment());
        mFragmentList.add(new MyFragment());
        mainTabAdapter=new MainTabAdapter(mFragmentList,getSupportFragmentManager());
        mVpContent.setAdapter(mainTabAdapter);
        mVpContent.setOffscreenPageLimit(mFragmentList.size());
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {
                Log.i("MainActivity", "position: " + currentPosition);
                setStatusBarColor(currentPosition);
                if (currentPosition == 0) {}
            }
        });

//        mBottomBarLayout.setUnread(0, 20);//设置第一个页签的未读数为20
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(MainTabActivity.this,true);
    }
    private void setStatusBarColor(int position) {
            //如果是我的页面，状态栏设置为透明状态栏
        Eyes.translucentStatusBar(MainTabActivity.this,true);
        Eyes.setLightStatusBar(this,true);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Subscribe
    public void checkClientFragment(ClientFragmentType clientFragmentType){
            ((ClientFragment) mainTabAdapter.getBaseFrgment()).checkClientFragment(clientFragmentType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE_GRIDE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            LogUtils.i(Matisse.obtainPathResult(data).get(0));
            EventBus.getDefault().post(new ImageUploadOk(Matisse.obtainPathResult(data).get(0)));
        }

    }


    public void version_check_success(AppVersion appVersion) {
        setAppVersion(appVersion);
        if (appVersion != null) {
            if (!appVersion.getVersion().equals(GeneralUtils.getAppVersionName(AppAplication.getAppContext()))){


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
                    .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                    .setApkVersionCode(2)
                    .setApkVersionName(appVersion.getVersion())
                    .setApkSize("" + appVersion.getSize())
                    .setApkDescription(appVersion.getRemark())
                    .download();

            }
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getAppVersion()==null||(!appVersion.getVersion().equals(GeneralUtils.getAppVersionName(AppAplication.getAppContext())))){
                mPresenter.version_check();
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
