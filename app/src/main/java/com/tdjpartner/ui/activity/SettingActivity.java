package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.Bank;
import com.tdjpartner.mvp.presenter.SettingPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.appupdate.DownloadManager;
import com.tdjpartner.utils.appupdate.UpdateConfiguration;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_bank)
    RelativeLayout rl_bank;
    @BindView(R.id.rl_modification)
    RelativeLayout rl_modification;
    @BindView(R.id.btn_out_login)
    Button btn_out_login;
    @BindView(R.id.rl_check_version)
    RelativeLayout rl_check_version;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    @OnClick({R.id.btn_back,R.id.rl_bank,R.id.rl_modification,R.id.btn_out_login,R.id.rl_bottom,R.id.rl_check_version,R.id.rl_version})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case  R.id.rl_bank://判断是否实名，通过用户信息判断
                if (UserUtils.getInstance().getLoginBean()!=null){
                    if (!GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                        Map<String,Object> map=new HashMap<>();
                        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
                        mPresenter.bankAccount(map);
                    }else {
                        Intent intent=new Intent(this,RealNameAuthenticationActivity.class);
                        startActivity(intent);
                    }
                }


                break;
            case R.id.rl_modification:
                Intent intent1=new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent1);
                    break;
            case R.id.btn_out_login:
                mPresenter.pushMessageLogout();

                break;
            case R.id.rl_bottom:
                Intent intent_=new Intent(this,WebViewActivity.class);
                startActivity(intent_);
                break;
            case R.id.rl_check_version:
                mPresenter.version_check();
                
                break;
            case R.id.rl_version:
                Toast.makeText(this,"当前版本号: "+GeneralUtils.getVersionName()+GeneralUtils.getVersionCode(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected SettingPresenter loadPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected void initData() {
        tv_version.setText("V"+GeneralUtils.getVersionName()+GeneralUtils.getVersionCode());
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.setting_layout;
    }

    public void bankAccountSuccess(Bank bank) {
        if (GeneralUtils.isNullOrZeroLenght(bank.getAccountNo())){
            Intent intent=new Intent(this,BindingBankActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(this,MyBankActivity.class);
            intent.putExtra("bank",bank);
            startActivity(intent);
        }


    }

    public void version_check_success(AppVersion appVersion) {
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
                        .setForcedUpgrade(false);
                        //设置下载过程的监听

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

            }else {
                GeneralUtils.showToastshort("已是最新版本" );
            }
        }
    }
}
