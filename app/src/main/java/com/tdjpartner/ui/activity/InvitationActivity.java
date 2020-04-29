package com.tdjpartner.ui.activity;

import android.Manifest;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.InvitationPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.ShareUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class InvitationActivity extends BaseActivity<InvitationPresenter>  {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.text_invite_code)
    TextView text_invite_code;
    @BindView(R.id.img_share)
    ImageView img_share;
    private String shareURL;

    private RxPermissions rxPermissions;
    @OnClick({R.id.btn,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                rxPermissions.request( Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        if (b){
                            new ShareUtils(InvitationActivity.this).shareWeb("https://m.51taodj.com/tdjh5/new/newRegister/identity?verifyCode="+UserUtils.getInstance().getLoginBean().getVerifyCode(), "淘大集-专业酒店食材供应链平台", "淘大集食材覆盖：新鲜蔬菜、禽肉蛋类、米面粮油、调料、水果等。食材相对市场价低20%~50%，更省钱省心省力。专业配送和服务团队。");
                        }else {
                            GeneralUtils.showToastshort("请允许淘大集读取存储卡");
                        }
             }

                });

                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected InvitationPresenter loadPresenter() {
        return new InvitationPresenter();
    }

    @Override
    protected void initData() {
//        mPresenter.version_check();
    ImageLoad.loadImageViewLoding("http://finance.51taodj.com/fund/static/images/qrcode/"+UserUtils.getInstance().getLoginBean().getVerifyCode()+".png",img_share);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        rxPermissions=new RxPermissions(this);
        tv_title.setText("邀请");
        toolbar.setBackgroundResource(R.mipmap.home_bg);
        text_invite_code.setText("我的邀请码："+ UserUtils.getInstance().getLoginBean().getVerifyCode());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.invitation_layout;
    }

    public void version_check_success(AppVersion appVersion) {
        shareURL=appVersion.getQrcodeImage();



    }
}
