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
                flag=true;
                getImage();
                break;
            case R.id.id_card_positive_iv://正
                flag=false;
                getImage();
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
                ed_idcard.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
            }
        }

    }

    public void getImage(){
        GeneralUtils.getImage(rxPermissions,this);
   /*     rxPermissions.request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //从相册中选择图片 此处使用知乎开源库Matisse
                    Matisse.from(RealNameAuthenticationActivity.this)
                            .choose(MimeType.ofImage())
                            .theme(R.style.Matisse_Dracula)
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(1)
                            .capture(true)
                            .captureStrategy(new CaptureStrategy(true, "com.tdjpartner.fileProvider")) //是否拍照功能，并设置拍照后图片的保存路径; FILE_PATH = 你项目的包名.fileprovider,必须配置不然会抛异常
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .originalEnable(true)
                            .maxOriginalSize(10)
                            .thumbnailScale(0.85f)
                            .imageEngine(new MyGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE_GRIDE);

                }
            }
        });*/

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
