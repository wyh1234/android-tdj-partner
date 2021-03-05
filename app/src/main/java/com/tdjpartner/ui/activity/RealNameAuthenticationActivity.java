package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.RealNameAuthenticationPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.GifSizeFilter;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.glide.MyGlideEngine;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class RealNameAuthenticationActivity extends BaseActivity<RealNameAuthenticationPresenter> {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.id_card_positive_iv)
    ImageView id_card_positive_iv;
    @BindView(R.id.id_card_negative_iv)
    ImageView id_card_negative_iv;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.ed_idcard)
    EditText ed_idcard;
    @BindView(R.id.tv_idcard)
    TextView tv_idcard;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private RxPermissions rxPermissions;

    private Map<String,Object> map=new HashMap<>();
    private boolean flag;

    @OnClick({R.id.btn_back,R.id.id_card_positive_iv,R.id.id_card_negative_iv,R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.id_card_negative_iv://反
                if (GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                    flag=true;
                    getImage();
                }

                break;
            case R.id.id_card_positive_iv://正
                if (GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                    flag=false;
                    getImage();
                }

                break;
            case R.id.btn:
                if (GeneralUtils.isNullOrZeroLenght(ed_idcard.getText().toString())){
                    GeneralUtils.showToastshort("请输入15或18位身份证号");
                    return;
                }
                if (!GeneralUtils.isLegalId(ed_idcard.getText().toString())){
                    GeneralUtils.showToastshort("身份证号码格式不正确");
                    return;
                }
                if (map.size()<2){
                    GeneralUtils.showToastshort("请上传身份证照片");
                    return;
                }
                map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("idCard",ed_idcard.getText().toString());
                mPresenter.addUserCard(map);

                break;
        }
    }
    @Override
    protected RealNameAuthenticationPresenter loadPresenter() {
        return new RealNameAuthenticationPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        rxPermissions = new RxPermissions(this);
        tv_title.setText("实名认证");
        if (UserUtils.getInstance().getLoginBean()!=null){
            if (GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                tv_idcard.setVisibility(View.GONE);
                ed_idcard.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
            }else {
                tv_idcard.setVisibility(View.VISIBLE);
                tv_idcard.setText(UserUtils.getInstance().getLoginBean().getIdCard());
                ed_idcard.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
                ImageLoad.loadImageViewLoding(UserUtils.getInstance().getLoginBean().getCardUrlPositive(),id_card_positive_iv,R.mipmap.sfzz);
                ImageLoad.loadImageViewLoding(UserUtils.getInstance().getLoginBean().getCardUrlNegative(),id_card_negative_iv,R.mipmap.sfzf);
            }
        }
        tv_name.setText(UserUtils.getInstance().getLoginBean().getRealname());
    }

    public void getImage(){
        GeneralUtils.getImage(rxPermissions,this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE_GRIDE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            LogUtils.i(Matisse.obtainPathResult(data).get(0));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainPathResult(data).get(0)));
                mPresenter.imageUpload(Matisse.obtainPathResult(data).get(0));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.realname_authentication_layout;
    }

    public void getImage_Success(String data) {
        LogUtils.e(data);

        if (flag){
            map.put("cardUrlNegative",data);
            ImageLoad.loadImageView(id_card_negative_iv.getContext(),data,id_card_negative_iv);
        }else {
            map.put("cardUrlPositive",data);
            ImageLoad.loadImageView(id_card_positive_iv.getContext(),data,id_card_positive_iv);
        }
    }

    public void addUserCardSuccess(Integer data) {
        finish();
    }
}
