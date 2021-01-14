package com.tdjpartner.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class AdministrationPaifangHistoryActivity extends BaseActivity {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.recyclerView_list1)
    RecyclerView recyclerView_list1;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_menber_name)
    TextView tv_menber_name;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private TimePickerView pvTime;
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
        tv_menber_name.setText("专员："+ UserUtils.getInstance().getLoginBean().getRealname());
        tv_date.setText(sdf.format(new Date()));
        tv_title.setText("拜访记录");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime=new TimePickerView.Builder(AdministrationPaifangHistoryActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tv_date.setText(sdf.format(date));
                        clearList();
                    }


                }) //年月日时分秒 的显示与否，不设置则默认全部显示
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setLabel("年", "月", "日", "", "", "")
                        .isCenterLabel(true)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(16)
                        .setDecorView(null)
                        .build();
                pvTime.show();
            }
        });
    }
    private void clearList() {


    }
    @Override
    protected int getLayoutId() {
        return R.layout.administration_baifang_history_layout;
    }
}
