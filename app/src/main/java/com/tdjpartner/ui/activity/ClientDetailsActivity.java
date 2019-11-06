package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.adapter.HistoryInfoAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.StoreInfoAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.ClientDetailsStoreInfo;
import com.tdjpartner.model.HistoryInfo;
import com.tdjpartner.mvp.presenter.ClientDetailsPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ClientDetailsActivity extends BaseActivity<ClientDetailsPresenter> implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.store_info_list)
    MyRecyclerView store_info_list;
    @BindView(R.id.history_info_list)
    MyRecyclerView history_info_list;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_s_useranme)
    TextView tv_s_useranme;
    @BindView(R.id.tv_s_phone)
    TextView tv_s_phone;
    @BindView(R.id.tv_s_address)
    TextView tv_s_address;
    @BindView(R.id.iv_heard)
    ImageView iv_heard;
    @BindView(R.id.rl_call)
    RelativeLayout rl_call;
    private List<ClientDetailsStoreInfo> storeInfoList=new ArrayList<>();
    private List<HistoryInfo> historyInfoList=new ArrayList<>();
    private StoreInfoAdapter storeInfoAdapter;
    private HistoryInfoAdapter historyInfoAdapter;
    private ClientDetails clientDetails;
    private RxPermissions rxPermissions;
    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @OnClick({R.id.btn_back,R.id.btn,R.id.rl_call,R.id.tv_s_phone})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
                Intent intent=new Intent(this,BaiFangActivity.class);
                intent.putExtra("clientDetails",getClientDetails());
                startActivity(intent);

                break;
            case R.id.rl_call:
                if (getClientDetails()!=null){
                    GeneralUtils.action_call(rxPermissions,getClientDetails().getMobile(),getContext());
                }

                break;
            case R.id.tv_s_phone:
                if (getClientDetails()!=null){
                    GeneralUtils.action_call(rxPermissions,getClientDetails().getReceiveMobile(),getContext());
                }
                break;
        }
    }
    @Override
    protected ClientDetailsPresenter loadPresenter() {
        return new ClientDetailsPresenter();
    }

    @Override
    protected void initData() {
        Map<String ,Object> map=new HashMap<>();
        map.put("customerId",getIntent().getStringExtra("customerId"));
        mPresenter.client_details(map);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        rxPermissions = new RxPermissions(this);
        CustomLinearLayout customLinearLayout=  new CustomLinearLayout(getContext(), LinearLayoutManager.VERTICAL, false);
        customLinearLayout.setScrollEnabled(false);
        ScrollLinearLayoutManager layout = new ScrollLinearLayoutManager(getContext(), 4);
        layout.setScrollEnable(false);
        store_info_list.setLayoutManager(customLinearLayout);
        history_info_list.setLayoutManager( layout);

        storeInfoAdapter=new StoreInfoAdapter(R.layout.storeinfo_item_layout,storeInfoList);
        store_info_list.setAdapter(storeInfoAdapter);

        historyInfoAdapter=new HistoryInfoAdapter(R.layout.hisroryinfo_item_layout,historyInfoList);
        historyInfoAdapter.setOnItemClickListener(this);
        history_info_list.setAdapter(historyInfoAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.client_details_layout;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (i==0){
            Intent intent=new Intent(this,GoodsAndStoreActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);
        }else if (i==1){
            Intent intent=new Intent(this,OrderListActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);

        }else if (i==2){
            Intent intent=new Intent(this,DiscountCouponActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);

        }else {
            Intent intent=new Intent(this,BaiFangHistoryActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);

        }

    }

    public void client_details_Success(ClientDetails clientDetails) {
        setClientDetails(clientDetails);
        ImageLoad.loadImageViewLoding(clientDetails.getHeadUrl(),iv_heard,R.mipmap.xingxiangzhao_bg);
        tv_name.setText(clientDetails.getName());
        tv_username.setText(clientDetails.getBoss()+":"+clientDetails.getMobile());
        tv_num.setText(clientDetails.getRegionCollNo());
        tv_time.setText(clientDetails.getDeliveredTimeBegin()+"-"+clientDetails.getDeliveredTimeEnd());
        tv_s_useranme.setText(clientDetails.getReceiveName());
        tv_s_phone.setText(":"+clientDetails.getReceiveMobile());
        tv_s_address.setText(clientDetails.getAddress());
        ClientDetailsStoreInfo clientDetailsStoreInfo=new ClientDetailsStoreInfo();
        clientDetailsStoreInfo.setTitle("当月下单总额");
        clientDetailsStoreInfo.setTotal(clientDetails.getMonthAmount().toString());
        clientDetailsStoreInfo.setRes(R.mipmap.clientdetails);
        ClientDetailsStoreInfo clientDetailsStoreInfo1=new ClientDetailsStoreInfo();
        clientDetailsStoreInfo1.setTitle("当月下单总数");
        clientDetailsStoreInfo1.setTotal(clientDetails.getMonthTimes()+"");
        clientDetailsStoreInfo1.setRes(R.mipmap.clientdetails_one);
        ClientDetailsStoreInfo clientDetailsStoreInfo2=new ClientDetailsStoreInfo();
        clientDetailsStoreInfo2.setTitle("售后退换货");
        clientDetailsStoreInfo2.setTotal(clientDetails.getAfterSaleTimes().toString());
        clientDetailsStoreInfo2.setRes(R.mipmap.clientdetails_two);
        ClientDetailsStoreInfo clientDetailsStoreInfo3=new ClientDetailsStoreInfo();
        clientDetailsStoreInfo3.setTitle("距上次下单");
        clientDetailsStoreInfo3.setTotal(clientDetails.getNotOrderDays().toString());
        clientDetailsStoreInfo3.setRes(R.mipmap.clientdetails_three);
        ClientDetailsStoreInfo clientDetailsStoreInfo4=new ClientDetailsStoreInfo();
        clientDetailsStoreInfo4.setTitle("距上次拜访");
        clientDetailsStoreInfo4.setTotal(clientDetails.getNoCallDay().toString());
        clientDetailsStoreInfo4.setRes(R.mipmap.clientdetails_four);

        storeInfoList.add(clientDetailsStoreInfo);
        storeInfoList.add(clientDetailsStoreInfo1);
        storeInfoList.add(clientDetailsStoreInfo2);
        storeInfoList.add(clientDetailsStoreInfo3);
        storeInfoList.add(clientDetailsStoreInfo4);

        historyInfoList.add(new HistoryInfo("商品/店铺",R.mipmap.client_sc));
        historyInfoList.add(new HistoryInfo("订单记录",R.mipmap.client_dd));
        historyInfoList.add(new HistoryInfo("使用券数",R.mipmap.client_q));
        historyInfoList.add(new HistoryInfo("拜访记录",R.mipmap.client_bf));
        storeInfoAdapter.setNewData(storeInfoList);
        historyInfoAdapter.setNewData(historyInfoList);
        storeInfoAdapter.notifyDataSetChanged();
        historyInfoAdapter.notifyDataSetChanged();
    }
}
