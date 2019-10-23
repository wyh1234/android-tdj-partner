package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.AddBaifangPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.SelTypePopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBaifangActivity extends BaseActivity<AddBaifangPresenter> {
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

    private TimePickerView pvTime;
    private   Calendar selectedDate,endDate,startDate;
    private SelTypePopuWindow selTypePopuWindow;
    private FollowUpPopuWindow followUpPopuWindow;
    @OnClick({R.id.btn_back,R.id.btn,R.id.rl_sel_time,R.id.rl_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
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
                    followUpPopuWindow.showPopupWindow();
                }
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
                    selTypePopuWindow.showPopupWindow();
                }
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_baifang_layout;
    }
}
