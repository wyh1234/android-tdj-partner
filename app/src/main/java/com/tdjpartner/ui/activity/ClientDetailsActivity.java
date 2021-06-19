package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.HistoryInfoAdapter;
import com.tdjpartner.adapter.StoreInfoAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.ClientDetailsStoreInfo;
import com.tdjpartner.model.HistoryInfo;
import com.tdjpartner.mvp.presenter.ClientDetailsPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.GridSpacingItemDecoration;
import com.tdjpartner.utils.appupdate.ApkUtil;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.CheckHeadImagePopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.MyRecyclerView;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.net.URISyntaxException;
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
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll)
    LinearLayout ll;
/*    @BindView(R.id.tv_s_useranme)
    TextView tv_s_useranme;*/
/*    @BindView(R.id.tv_s_phone)
    TextView tv_s_phone;*/
    @BindView(R.id.tv_s_address)
    TextView tv_s_address;
 /*   @BindView(R.id.tv_heard)
    TextView tv_heard;*/
    @BindView(R.id.iv_heard)
    ImageView iv_heard;
    @BindView(R.id.ll_call)
    RelativeLayout rl_call;
    private List<ClientDetailsStoreInfo> storeInfoList=new ArrayList<>();
    private List<HistoryInfo> historyInfoList=new ArrayList<>();
    private StoreInfoAdapter storeInfoAdapter;
    private HistoryInfoAdapter historyInfoAdapter;
    private ClientDetails clientDetails;
    private RxPermissions rxPermissions;
    private CheckHeadImagePopuWindow checkHeadImagePopuWindow;
    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @OnClick({R.id.btn_back,R.id.btn,R.id.ll_call, R.id.iv_heard,
            R.id.rl_daoHang})
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
            case R.id.ll_call:
                if (getClientDetails()!=null){
                    GeneralUtils.action_call(rxPermissions,getClientDetails().getMobile(),getContext());
                }

                break;
            case R.id.iv_heard:
                    if (checkHeadImagePopuWindow!=null){
                        if (checkHeadImagePopuWindow.isShowing()){
                            return;
                        }
                        checkHeadImagePopuWindow.showPopupWindow();
                    }else {

                        checkHeadImagePopuWindow = new CheckHeadImagePopuWindow(this,clientDetails.getHeadUrl());
                        checkHeadImagePopuWindow.setDismissWhenTouchOutside(false);
                        checkHeadImagePopuWindow.setInterceptTouchEvent(false);
                        checkHeadImagePopuWindow.setPopupWindowFullScreen(true);//铺满
                        checkHeadImagePopuWindow.showPopupWindow();
                    }
                    break;
      /*      case R.id.tv_s_phone:
                if (getClientDetails()!=null){
                    GeneralUtils.action_call(rxPermissions,getClientDetails().getReceiveMobile(),getContext());
                }
                break;*/
            case R.id.rl_daoHang:
                if (getClientDetails() != null){
                    ClientDetails clientDetails = getClientDetails();
                    String address = clientDetails.getAddress();
                    String lat = clientDetails.getLat();
                    String lon = clientDetails.getLon();
                    if (lat == null || lat.isEmpty() || lon == null || lon.isEmpty()) {
                        Toast.makeText(this,"无法开启导航",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (address != null && !address.isEmpty()){
                        Intent intent2 = new Intent();
                        intent2.setAction(Intent.ACTION_VIEW);
                        intent2.addCategory(Intent.CATEGORY_DEFAULT);
                        //将功能Scheme以URI的方式传入data
                        Uri uri2 = Uri.parse("qqmap://map/routeplan?type=drive&to=tvShopName&tocoord=" + lat + "," + lon);
                        intent2.setData(uri2);
                        if (ApkUtil.isAvilible(this, "com.autonavi.minimap")) {
                            try {
                                Intent intent1 = Intent.getIntent("androidamap://navi?sourceApplication=appname&poiname=" + address + "&lat="
                                        + lat
                                        + "&lon="
                                        + lon + "&dev=0");
                                startActivity(intent1);
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }else {

                            Toast.makeText(this,"您尚未安装地图",Toast.LENGTH_SHORT).show();
                            //跳转到应用商店去下载高德地图app
                            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                            Intent intent3 = new Intent(Intent.ACTION_VIEW, uri);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent3);
                        }
                    }
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
        ScrollLinearLayoutManager layout = new ScrollLinearLayoutManager(getContext(), 4);
        layout.setScrollEnable(false);
        history_info_list.setLayoutManager( layout);

        tv_title.setText("客户详情");
        ScrollLinearLayoutManager layout1=    new ScrollLinearLayoutManager(this,3);
        store_info_list.setLayoutManager(layout1);
        layout1.setScrollEnable(false);
        store_info_list.addItemDecoration(new GridSpacingItemDecoration(3, 45,false,30));
        storeInfoAdapter=new StoreInfoAdapter(R.layout.storeinfo_item_layout,storeInfoList);
        store_info_list.setAdapter(storeInfoAdapter);

        historyInfoAdapter=new HistoryInfoAdapter(R.layout.hisroryinfo_item_layout,historyInfoList);
        historyInfoAdapter.setOnItemClickListener(this);
        history_info_list.setAdapter(historyInfoAdapter);
        if (UserUtils.getInstance().getLoginBean().getGrade()==3){
            ll.setVisibility(View.VISIBLE);
        }else {
            ll.setVisibility(View.GONE);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_client_details_layout;
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

        }else if (i==3){
            Intent intent=new Intent(this,BaiFangHistoryActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);

        }else {
            Intent intent=new Intent(this,AdterSalesOrderListActivity.class);
            intent.putExtra("buyId",getClientDetails().getCustomerId()+"");
            startActivity(intent);
        }

    }

    public void client_details_Success(ClientDetails clientDetails) {
        setClientDetails(clientDetails);
//        if (!GeneralUtils.isNullOrZeroLenght(clientDetails.getHeadUrl())){
            ImageLoad.loadImageViewLoding(clientDetails.getHeadUrl(),iv_heard,R.mipmap.xxz);
//            tv_heard.setVisibility(View.GONE);
//        }

        tv_name.setText(clientDetails.getName());
        tv_username.setText(clientDetails.getBoss()+":"+clientDetails.getMobile());
        tv_num.setText(clientDetails.getRegionCollNo());
        tv_time.setText(clientDetails.getDeliveredTimeBegin()+"-"+clientDetails.getDeliveredTimeEnd());
/*        tv_s_useranme.setText(clientDetails.getReceiveName());
        tv_s_phone.setText(":"+clientDetails.getReceiveMobile());*/
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
        historyInfoList.add(new HistoryInfo("售后记录",R.mipmap.dingdanjilu));
        storeInfoAdapter.setNewData(storeInfoList);
        historyInfoAdapter.setNewData(historyInfoList);
        storeInfoAdapter.notifyDataSetChanged();
        historyInfoAdapter.notifyDataSetChanged();
    }
}
