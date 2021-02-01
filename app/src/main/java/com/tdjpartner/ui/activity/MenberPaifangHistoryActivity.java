package com.tdjpartner.ui.activity;


import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MenberPaifangHistoryAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.mvp.presenter.MenberPaifangHistoryPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MenberPaifangHistoryActivity extends BaseActivity<MenberPaifangHistoryPresenter> {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_num1)
    TextView tv_num1;
    @BindView(R.id.tv_num2)
    TextView tv_num2;
    @BindView(R.id.tv_menber_name)
    TextView tv_menber_name;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn)
    Button btn;
    private List<DistinctList.ListBean> list=new ArrayList<>();
    private MenberPaifangHistoryAdapter menberPaifangHistoryAdapter;
    private int callId;
    private Map<String,Object> hashmap;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private TimePickerView pvTime;
    private RxPermissions rxPermissions;
    @Override
    protected MenberPaifangHistoryPresenter loadPresenter() {
        return new MenberPaifangHistoryPresenter();
    }
    @OnClick({R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                GeneralUtils.action_call(rxPermissions,getIntent().getStringExtra("Phone"),this);
                break;
        }


    }
    @Override
    protected void initData() {
        if (getIntent().getIntExtra("User_id",0)>0){
            callId=getIntent().getIntExtra("User_id",0);
            btn.setVisibility(View.VISIBLE);
        }else {
            callId=UserUtils.getInstance().getLoginBean().getEntityId();
            btn.setVisibility(View.GONE);
        }

         hashmap=new HashMap<>();
        clearList();

    }
    public void clearList(){
        if (hashmap.size()>0){
            hashmap.clear();
        }
        hashmap.put("callId",callId);
        hashmap.put("callDate",tv_date.getText().toString());
        mPresenter.distinctList(hashmap);
    }
    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        rxPermissions = new RxPermissions(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        menberPaifangHistoryAdapter= new MenberPaifangHistoryAdapter(R.layout.paifang_history_item,list);
        tv_menber_name.setText("专员："+UserUtils.getInstance().getLoginBean().getRealname());
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
                pvTime=new TimePickerView.Builder(MenberPaifangHistoryActivity.this, new TimePickerView.OnTimeSelectListener() {
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

    @Override
    protected int getLayoutId() {
        return R.layout.menber_paifang_histoty_layout;
    }

    public void getDistinctList(DistinctList distinctList){
        tv_num.setText(distinctList.getCall_num()+"");

        tv_num1.setText(distinctList.getConversion_num()+"");
        tv_num2.setText(distinctList.getOrder_total_money().toString());
        if (list.size()>0){
            list.clear();
            menberPaifangHistoryAdapter.notifyDataSetChanged();
        }
        if (distinctList.getList().size()>0){
            rl.setVisibility(View.GONE);
            list.addAll(distinctList.getList());
            menberPaifangHistoryAdapter.notifyDataSetChanged();

        }else {
            rl.setVisibility(View.VISIBLE);
        }


    }



}