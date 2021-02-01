package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.adapter.provider.AdministrationItemAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.mvp.presenter.AdministrationPaifangPresneter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.tdjpartner.adapter.AdministrationAdapter.LIST;

public class AdministrationPaifangHistoryActivity extends BaseActivity<AdministrationPaifangPresneter> implements AdministrationAdapter.OnItemChildClickListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
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
    private Map<String,Object> hashmap;
    private List<Message> userandCustomer=new ArrayList<>();
    private  ParentList.Headinfo headinfo, headinfo1;
    private AdministrationAdapter administrationAdapter;
    private RxPermissions rxPermissions;
    @Override
    protected AdministrationPaifangPresneter loadPresenter() {
        return new AdministrationPaifangPresneter();
    }

    @Override
    protected void initData() {
        if (hashmap.size()>0){
            hashmap.clear();
        }
        hashmap.put("callId",UserUtils.getInstance().getLoginBean().getEntityId());
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
        recyclerView_list.setLayoutManager(layout);
        administrationAdapter=new AdministrationAdapter(userandCustomer);
        recyclerView_list.setAdapter(administrationAdapter);
        administrationAdapter.setOnItemChildClickListener(this);

        tv_menber_name.setText("经理："+ UserUtils.getInstance().getLoginBean().getRealname());
        tv_date.setText(sdf.format(new Date()));
        tv_title.setText("专员拜访记录");
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

        hashmap=new HashMap<>();
        clearList();

         headinfo=new ParentList.Headinfo();
       headinfo1=new ParentList.Headinfo();
    }
    private void clearList() {

        if (hashmap.size()>0){
            hashmap.clear();
        }
        hashmap.put("callId",UserUtils.getInstance().getLoginBean().getEntityId());
        hashmap.put("callDate",tv_date.getText().toString());
        mPresenter.distinctList(hashmap);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.administration_baifang_history_layout;
    }

    public void getDistinctList(ParentList parentList) {
        if (userandCustomer.size()>0){
            userandCustomer.clear();
        }



        headinfo.setCurr(parentList.getUser_call_num());//已出勤人数
        headinfo.setTotal(parentList.getUser_num());//已出勤总人数
        headinfo.setType(1);
        userandCustomer.add(headinfo);
        userandCustomer.addAll(parentList.getUser_list());

        headinfo1.setCurr(parentList.getCustomer_order_num());//下单数
        headinfo1.setTotal(parentList.getCustomer_num());//酒店总数
        userandCustomer.add(headinfo1);
        userandCustomer.addAll(parentList.getCustomer_list());
        administrationAdapter.setNewData(userandCustomer);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter.getItemViewType(i)==LIST){
            ParentList.UserListBean userListBean = (ParentList.UserListBean)baseQuickAdapter.getItem(i);
            if (view.getId()==R.id.tv_num){

                    Intent intent=new Intent(AdministrationPaifangHistoryActivity.this,MenberPaifangHistoryActivity.class);
                    intent.putExtra("User_id",userListBean.getUser_id());
                intent.putExtra("Phone",userListBean.getPhone());
                    startActivity(intent);

            }else if (view.getId()==R.id.ll){

                GeneralUtils.action_call(rxPermissions,userListBean.getPhone(),this);
            }


        }
    }
}
