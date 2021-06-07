package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tdjpartner.R;
import com.tdjpartner.adapter.ShareAcAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.IntegralItem;
import com.tdjpartner.mvp.presenter.InvitationPresenter;
import com.tdjpartner.utils.ShareUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;
import com.umeng.socialize.UMShareAPI;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class InvitationActivityNew extends BaseActivity<InvitationPresenter>  {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_share)
    ImageView img_share;
    @BindView(R.id.text_invite_code)
    TextView text_invite_code;
    @BindView(R.id.layout)
    LinearLayout mLayout;
    @BindView(R.id.rv_share_record)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_right)
    TextView tv_right;
    private String shareURL;
    private ShareAcAdapter mAdapter;

    @OnClick({R.id.ok,R.id.btn_back,R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ok:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"请允许淘创客读取存储卡",Toast.LENGTH_SHORT).show();
                    //申请权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 110);
                } else shareWeb();
                break;
            case R.id.tv_share_reward_record:
                Intent intent1 = new Intent(this, WebViewActivity.class);
                intent1.putExtra("url", PublicCache.getROOT_URL().get(2)+"tdj-user/user/integral/shareRulePage");
                startActivity(intent1);
                break;
                case R.id.btn_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(new Intent(this,ShareShopListActivity.class));
                break;
        }
    }
    @Override
    protected InvitationPresenter loadPresenter() {
        return new InvitationPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.version_check();
//        String path = "http://finance.51taodj.com/fund/static/images/qrcode/"
//                +UserUtils.getInstance().getLoginBean().getVerifyCode()+ ".png";
        String path = "http://tdjtest.51taodj.com:8080/fund/qrcode/create?verifyCode"
                +UserUtils.getInstance().getLoginBean().getVerifyCode();
    ImageLoad.loadImageViewLoding(path,img_share);
        mPresenter.initRecordList();
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_right.setText("分享列表");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShareAcAdapter();
        mAdapter.setEmptyView(getEmptyView());
        recyclerView.setAdapter(mAdapter);
        tv_title.setText("邀请有礼");
        toolbar.setBackgroundResource(R.mipmap.home_bg);
        text_invite_code.setText("我的邀请码："+ UserUtils.getInstance().getLoginBean().getVerifyCode());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myself_share;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 110) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareWeb();
                } else {
                    //提示没有权限，安装不了咯
                    Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public void version_check_success(AppVersion appVersion) {
        shareURL=appVersion.getQrcodeImage();

    }
    private void shareWeb() {
        new ShareUtils(this).shareWeb(shareURL, "淘大集-专业酒店食材供应链平台", "淘大集食材覆盖：新鲜蔬菜、禽肉蛋类、米面粮油、调料、水果等。食材相对市场价低20%~50%，更省钱省心省力。专业配送和服务团队。");
    }

    public void queryInviteListSuccess(List<IntegralItem> integralItem) {
        mAdapter.setNewData(integralItem);
        mAdapter.notifyDataSetChanged();
    }

    private View getEmptyView(){
        return LayoutInflater.from(this).inflate(R.layout.page_empty,null);
    }
}
