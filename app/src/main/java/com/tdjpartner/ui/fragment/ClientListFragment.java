package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.ClientListPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class ClientListFragment extends BaseFrgment<ClientListPresenter>  implements OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener,FollowUpPopuWindow.FollowUpListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    public int pageNo = 1;//翻页计数器
    private ClientListAdapter clientListAdapter;
    private List<ClientInfo> data=new ArrayList<>();
    public RxPermissions rxPermissions;
    private boolean aBoolean;
    private LocationBean locationBean;
    private FollowUpPopuWindow followUpPopuWindow;
    private int customerId;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public static ClientListFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        ClientListFragment f = new ClientListFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        rxPermissions = new RxPermissions(getActivity());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        clientListAdapter=new ClientListAdapter(R.layout.client_item,data);
        recyclerView_list.setAdapter(clientListAdapter);
        clientListAdapter.setOnItemClickListener(this);
        clientListAdapter.setOnItemChildClickListener(this);

    }

    @Override
    protected void loadData() {

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("onAttach");
        registerEventBus(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("onDetach");
        unregisterEventBus(this);
    }


    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        LogUtils.e("2222222");
        index=getArguments().getInt("intent");
        stop();
        refreshLayout.autoRefresh();

    }
    /*code 不同事件接受處理*/
    @Subscribe
    public void eventCode(LocationBean locationBean) {
        if (locationBean.getTag().contains("LOCATION")){
            LogUtils.e(locationBean);
            Map<String,Object> map=new HashMap<>();
            map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
            map.put("userType",index+1);
            map.put("latitude","30.5998320000");
            map.put("longitude","114.3439610000");
            map.put("keyword","");
            mPresenter.hotelMap(map);
        }


    }

    @Override
    protected ClientListPresenter loadPresenter() {
        return new ClientListPresenter();
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }

    @Override
    protected int getContentId() {
        return R.layout.client_list_fragment;
    }

/*    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
//        getData(++pageNo);
    }*/

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
//        LogUtils.e(index);
//        pageNo=1;
//        getData(pageNo);
        getData();
    }
    protected  void getData(){
        rxPermissions.request( Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Exception {
                aBoolean=b;
                if (b){
                    LocationUtils.getInstance().startLocalService("LOCATION");
                }else {
                    GeneralUtils.isNullOrZeroLenght("请开启位置信息");
                    hotelMap_failed();
                }

            }
        });




    }


    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        LogUtils.i(refreshLayout.isRefreshing());
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
     /*   if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }*/
    }



    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (index==0||index==1){
            Intent intent=new Intent(getContext(), ClientDetailsActivity.class);
            intent.putExtra("customerId",data.get(i).getCustomerId()+"");
            startActivity(intent);
        }

    }

    public void hotelMap_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }

    public void hotelMap_Success(List<ClientInfo> clientInfoList) {
        stop();
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }

        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(clientInfoList)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }


        data.addAll(clientInfoList);
        clientListAdapter.setIndex(index);
        clientListAdapter.setNewData(data);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()==R.id.tv_gj_status){
            setCustomerId(data.get(i).getCustomerId());
            if (followUpPopuWindow!=null){
                if (followUpPopuWindow.isShowing()){
                    return;
                }
                followUpPopuWindow.showPopupWindow();
            }else {

                followUpPopuWindow = new FollowUpPopuWindow(getContext(),data.get(i).getName());
                followUpPopuWindow.setDismissWhenTouchOutside(false);
                followUpPopuWindow.setInterceptTouchEvent(false);
                followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
                followUpPopuWindow.showPopupWindow();
                followUpPopuWindow.setFollowUpListener(this);
            }

        }
    }

    @Override
    public void onCancel() {
        followUpPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        Map<String,Object> map=new HashMap<>();
        map.put("customerId", getCustomerId());
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("userName", "");
        map.put("address", "");
        map.put("lon", "");
        map.put("lat", "");
        mPresenter.internationalWaters(map);

    }

    public void internationalWatersSuccess() {
        clientListAdapter.remove(getPos());
        followUpPopuWindow.dismiss();

    }
}
