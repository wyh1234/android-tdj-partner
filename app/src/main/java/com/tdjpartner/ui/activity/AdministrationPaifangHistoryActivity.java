package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.adapter.BaifangStoreAdapter;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.adapter.provider.AdministrationItemAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.mvp.presenter.AdministrationPaifangPresneter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.tdjpartner.adapter.AdministrationAdapter.LIST;

public class AdministrationPaifangHistoryActivity extends BaseActivity<AdministrationPaifangPresneter> implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
//    @BindView(R.id.recyclerView_list_one)
    RecyclerView recyclerView_list_one;
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
    private List<Message> userand=new ArrayList<>();
    private List<Message> customer=new ArrayList<>();

    private List<ParentList.CustomerListBean> customerListBeans=new ArrayList<>();
    private List<ParentList.UserListBean> userListBeanList=new ArrayList<>();
    private  ParentList.Headinfo headinfo, headinfo1;
    private AdministrationAdapter administrationAdapter;
    private BaifangStoreAdapter baifangStoreAdapter;
    private RxPermissions rxPermissions;
    private View footView;
    private Calendar endDate;
    @Override
    protected AdministrationPaifangPresneter loadPresenter() {
        return new AdministrationPaifangPresneter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {



        Eyes.translucentStatusBar(this,true);
        rxPermissions = new RxPermissions(this);
        CustomLinearLayout layout = new CustomLinearLayout(getContext(),
                LinearLayoutManager.VERTICAL, false);
        CustomLinearLayout layout1 = new CustomLinearLayout(getContext(),
                LinearLayoutManager.VERTICAL, false);
        layout.setScrollEnabled(true);
        recyclerView_list.setLayoutManager(layout);
        layout1.setScrollEnabled(true);



        administrationAdapter=new AdministrationAdapter(userand);
        recyclerView_list.setAdapter(administrationAdapter);
        administrationAdapter.setOnItemChildClickListener(this);

        View footView = ViewUrils.getFragmentView(recyclerView_list, R.layout.baifang_history_foot_layout);
        recyclerView_list_one=footView.findViewById(R.id.recyclerView_list_one);
        recyclerView_list_one.setLayoutManager(layout1);

        baifangStoreAdapter=new BaifangStoreAdapter(customer);
        recyclerView_list_one.setAdapter(baifangStoreAdapter);
        baifangStoreAdapter.setOnItemChildClickListener(this);
        administrationAdapter.addFooterView(footView);
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));

        if (UserUtils.getInstance().getLoginBean().getGrade()==2){
            tv_menber_name.setText("主管："+ UserUtils.getInstance().getLoginBean().getRealname());
        }else {
            tv_menber_name.setText("经理："+ UserUtils.getInstance().getLoginBean().getRealname());
        }

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
                        .setRangDate(null , endDate)
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
        if (userand.size()>0){
            userand.clear();
        }
        if (customer.size()>0){
            customer.clear();
        }
        if (userListBeanList.size()>0){
            userListBeanList.clear();
        }
        if (customerListBeans.size()>0){
            customerListBeans.clear();
        }


        LogUtils.e(parentList.getUser_list());

        headinfo.setCurr(parentList.getUser_call_num());//已出勤人数
        headinfo.setTotal(parentList.getUser_num());//已出勤总人数
        headinfo.setType(1);
        userand.add(headinfo);
        if (parentList.getUser_list().size()>0){
            if (parentList.getUser_list().size()>3){
                userand.addAll(parentList.getUser_list().subList(0,3));
            }else {
                userand.addAll(parentList.getUser_list());
            }
            userListBeanList.addAll(parentList.getUser_list());
        }



        headinfo1.setCurr(parentList.getCustomer_order_num());//下单数
        headinfo1.setTotal(parentList.getCustomer_num());//酒店总数

        administrationAdapter.setUserItemSize(parentList.getUser_list().size(),tv_date.getText().toString());

        customer.add(headinfo1);
        if (parentList.getCustomer_list().size()>0){
            if (parentList.getCustomer_list().size()>3){
                customer.addAll(parentList.getCustomer_list().subList(0,3));
            }else {
                customer.addAll(parentList.getCustomer_list());
            }
            customerListBeans.addAll(parentList.getCustomer_list());
        }



        administrationAdapter.setNewData(userand);

        baifangStoreAdapter.setUserandCustomerSize(parentList.getCustomer_list().size());

        baifangStoreAdapter.setNewData(customer);

        LogUtils.e(parentList.getCustomer_list().size());

    }


    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if(baseQuickAdapter instanceof AdministrationAdapter){
            if (baseQuickAdapter.getItemViewType(i)==LIST){
                ParentList.UserListBean userListBean = (ParentList.UserListBean)baseQuickAdapter.getItem(i);
                if (view.getId()==R.id.tv_num){

                    Intent intent=new Intent(AdministrationPaifangHistoryActivity.this,MenberPaifangHistoryActivity.class);
                    intent.putExtra("User_id",userListBean.getUser_id());
                    intent.putExtra("call_name",userListBean.getNick_name());
                    startActivity(intent);

                }else if (view.getId()==R.id.ll){

                    GeneralUtils.action_call(rxPermissions,userListBean.getPhone(),this);
                }else if (view.getId()==R.id.ll_bottom){
                    if (userand.size()>0){
                        userand.clear();
                    }
                    userand.add(headinfo);

                    LogUtils.e(userListBean);
                    if (userListBean.isF()){
                        userand.addAll(userListBeanList.subList(0,3));
                        ((ParentList.UserListBean)userand.get(userand.size()-1)).setF(false);
                    }else {
                        userand.addAll(userListBeanList);
                        ((ParentList.UserListBean)userand.get(userand.size()-1)).setF(true);
                    }


                    administrationAdapter.setNewData(userand);

                }


            }
        }else {
            if (baseQuickAdapter.getItemViewType(i)==LIST){

                ParentList.CustomerListBean customerListBean = (ParentList.CustomerListBean)baseQuickAdapter.getItem(i);
                if (view.getId()==R.id.ll_bottom){

                    if (customer.size()>0){
                        customer.clear();
                    }

                    customer.add(headinfo1);

                    LogUtils.e(customer);
                    if (customerListBean.isF()){
                        customer.addAll(customerListBeans.subList(0,3));
                        ((ParentList.CustomerListBean)customer.get(customer.size()-1)).setF(false);
                    }else {
                        customer.addAll(customerListBeans);
                        ((ParentList.CustomerListBean)customer.get(customer.size()-1)).setF(true);
                    }

                    baifangStoreAdapter.setNewData(customer);
                }
            }
        }

        }
}
