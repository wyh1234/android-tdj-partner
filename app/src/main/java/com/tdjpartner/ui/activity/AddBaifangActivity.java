package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.AddBaifangPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.SelTypePopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBaifangActivity extends BaseActivity<AddBaifangPresenter> implements SelTypePopuWindow.SelTypePopuWindowListener,FollowUpPopuWindow.FollowUpListener{
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_sel_time)
    RelativeLayout rl_sel_time;
    @BindView(R.id.rl_type)
    RelativeLayout rl_type;

    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.ed_storeName)
    TextView ed_storeName;
    @BindView(R.id.ed_byCallName)
    TextView ed_byCallName;
    @BindView(R.id.ed_byCallMobile)
    TextView ed_byCallMobile;
    @BindView(R.id.ed_storeAddress)
    TextView ed_storeAddress;
    @BindView(R.id.tv_callType)
    TextView tv_callType;
    @BindView(R.id.ed_matters)
    TextView ed_matters;
    @BindView(R.id.ed_results)
    TextView ed_results;
    @BindView(R.id.iv_upload)
    ImageView iv_upload;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_callMobile)
    TextView tv_callMobile;
    private int type;
    private String path;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private TimePickerView pvTime;
    private   Calendar selectedDate,endDate,startDate;
    private SelTypePopuWindow selTypePopuWindow;
    private FollowUpPopuWindow followUpPopuWindow;
    @OnClick({R.id.btn_back,R.id.btn,R.id.rl_sel_time,R.id.rl_type,R.id.iv_upload})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
                if (GeneralUtils.isNullOrZeroLenght(ed_storeName.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访门店名称");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_byCallName.getText().toString())){
                    GeneralUtils.showToastshort("请输入被拜访人名称");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_byCallMobile.getText().toString())){
                    GeneralUtils.showToastshort("请输入被拜访人手机号");
                    return;
                }

                if (GeneralUtils.isNullOrZeroLenght(ed_storeAddress.getText().toString())){
                    GeneralUtils.showToastshort("请输入门店详细地址");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(tv_time.getText().toString())){
                    GeneralUtils.showToastshort("请选择拜访时间");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(tv_callType.getText().toString())){
                    GeneralUtils.showToastshort("请选择拜访类型");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_matters.getText().toString())){
                    GeneralUtils.showToastshort("请输入主要事宜");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_results.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访结果");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(tv_time.getText().toString())){
                    GeneralUtils.showToastshort("请选择拜访时间");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(tv_callType.getText().toString())){
                    GeneralUtils.showToastshort("选择拜访类型");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(getPath())){
                    GeneralUtils.showToastshort("请上传拜访图片");
                    return;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("city",UserUtils.getInstance().getLoginBean().getSite());
                map.put("callId",UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("callName",UserUtils.getInstance().getLoginBean().getRealname());
                map.put("callMobile",UserUtils.getInstance().getLoginBean().getPhoneNumber());
                map.put("storeName",ed_storeName.getText().toString());
                map.put("byCallName",ed_byCallName.getText().toString());
                map.put("byCallMobile",ed_byCallMobile.getText().toString());
                map.put("storeAddress",ed_storeAddress.getText().toString());
                map.put("callDate",tv_time.getText().toString());
                map.put("callType",getType());
                map.put("callPic",getPath());
                map.put("matters",ed_matters.getText().toString());
                map.put("results",ed_results.getText().toString());
                mPresenter.worship(map);


                break;
            case R.id.rl_sel_time:
                pvTime.show();


                break;
            case R.id.rl_type:

                if (selTypePopuWindow!=null){
                    if (selTypePopuWindow.isShowing()){
                        return;
                    }
                    selTypePopuWindow.showPopupWindow();
                }else {

                    selTypePopuWindow = new SelTypePopuWindow(this);
                    selTypePopuWindow.setDismissWhenTouchOutside(false);
                    selTypePopuWindow.setInterceptTouchEvent(false);
                    selTypePopuWindow.setPopupWindowFullScreen(true);//铺满
                    selTypePopuWindow.setSelTypePopuWindowListener(this);
                    selTypePopuWindow.showPopupWindow();
                }
                break;
            case R.id.iv_upload:
                GeneralUtils.getImage(new RxPermissions(this),this);
                break;
        }

    }

    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return format.format(date);
    }
    @Override
    protected AddBaifangPresenter loadPresenter() {
        return new AddBaifangPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
         startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH))-1,startDate.get(Calendar.DAY_OF_MONTH),startDate.get(Calendar.HOUR_OF_DAY),startDate.get(Calendar.MINUTE));
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH),endDate.get(Calendar.HOUR_OF_DAY),endDate.get(Calendar.MINUTE));

        pvTime=new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(getTimes(date));
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(endDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        tv_username.setText(UserUtils.getInstance().getLoginBean().getRealname());
        tv_callMobile.setText(UserUtils.getInstance().getLoginBean().getPhoneNumber());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_baifang_layout;
    }

    @Override
    public void onSelType(int type) {
        tv_callType.setText(type==1?"未注册":"已注册");
        setType(type);
        selTypePopuWindow.dismiss();

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

    public void getImage_Success(String data) {
        setPath(data);
        ImageLoad.loadImageView(this,data,iv_upload);
    }

    public void worship_Success() {
          if (followUpPopuWindow!=null){
                    if (followUpPopuWindow.isShowing()){
                        return;
                    }
                    followUpPopuWindow.showPopupWindow();
                }else {

                    followUpPopuWindow = new FollowUpPopuWindow(this,"ADDBAIFANG");
                    followUpPopuWindow.setDismissWhenTouchOutside(false);
                    followUpPopuWindow.setInterceptTouchEvent(false);
                    followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
                    followUpPopuWindow.setFollowUpListener(this);
                    followUpPopuWindow.showPopupWindow();
                }
    }

    @Override
    public void onCancel() {
        followUpPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        followUpPopuWindow.dismiss();
        finish();
    }
}
