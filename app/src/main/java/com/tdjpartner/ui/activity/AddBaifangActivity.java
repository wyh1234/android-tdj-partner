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
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.SelTypePopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBaifangActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_sel_time)
    RelativeLayout rl_sel_time;
    @BindView(R.id.rl_type)
    RelativeLayout rl_type;

    @BindView(R.id.tv_time)
    TextView tv_time;
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
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
         selectedDate = Calendar.getInstance();
         startDate = Calendar.getInstance();
        startDate.set(2016, 0, 23);
         endDate = Calendar.getInstance();
        endDate.set(2029, 11, 28);

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
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_baifang_layout;
    }
}
