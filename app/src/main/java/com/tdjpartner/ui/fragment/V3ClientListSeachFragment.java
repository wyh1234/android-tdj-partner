package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.Fragment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.CustomerInfo;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.V3ClientListSeachPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class V3ClientListSeachFragment extends Fragment<V3ClientListSeachPresenter> implements OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index = 0, pageNo = 1, pageSum = 15;
    private BaseQuickAdapter<ClientInfo, BaseViewHolder> clientListSeachAdapter;
    private List<ClientInfo> data = new ArrayList<>();
    private SeachTag seachTag;
    private RxPermissions rxPermissions;
    private LocationBean locationBean;


    public void setSeachTag(SeachTag seachTag) {
        this.seachTag = seachTag;
    }

    public static V3ClientListSeachFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        V3ClientListSeachFragment f = new V3ClientListSeachFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this::onLoadmore);
        refreshLayout.setEnableLoadmore(true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        clientListSeachAdapter = new BaseQuickAdapter<ClientInfo, BaseViewHolder>(R.layout.client_list_seach_item, data) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ClientInfo clientInfo) {
                baseViewHolder.setText(R.id.tv_name, clientInfo.getName());
            }
        };
        recyclerView_list.setAdapter(clientListSeachAdapter);
        clientListSeachAdapter.setOnItemClickListener(this);
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

    @Subscribe
    public void eventCode(SeachTag seachTag) {
        this.seachTag = seachTag;
        refreshLayout.autoRefresh();
        if (!ListUtils.isEmpty(data)) {
            data.clear();
            clientListSeachAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void eventCode(LocationBean locationBean) {
        if (!locationBean.getTag().contains("LOCATION")) return;
        this.locationBean = locationBean;
        pull(1);

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        index = getArguments().getInt("intent");

        if (seachTag != null) {
            refreshLayout.autoRefresh();
        } else {
            mStateView.showEmpty();//显示重试的布局
        }
    }

    @Override
    protected V3ClientListSeachPresenter loadPresenter() {
        return new V3ClientListSeachPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.client_list_seach_fragment;
    }

    /**
     * StateView的根布局，默认是整个界面，如果需要变换可以重写此方法
     */
    public View getStateViewRoot() {
        return recyclerView_list;
    }

    public void onLoadmore(RefreshLayout refreshlayout) {
        System.out.println("~~ClientListFragment.onLoadmore~~");
        pull(++pageNo);
    }

    public void stop() {
        LogUtils.i(refreshLayout.isRefreshing());
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }

        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }

    private void getData() {
        if (seachTag != null) {
            if (locationBean == null) {
                if (rxPermissions == null) rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe(b -> {
                            if (b) {
                                if (locationBean == null) {
                                    LocationUtils.getInstance().startLocalService("LOCATION");
                                }
                            }
                        });
            } else {
                pull(1);
            }

        } else {
            mStateView.showEmpty();//显示重试的布局
            GeneralUtils.showToastshort("请输入门店名称或者手机号");
        }
        stop();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent(getContext(), ClientDetailsActivity.class);
        intent.putExtra("customerId", data.get(i).getCustomerId() + "");
        startActivity(intent);
    }

    private void pull(int pageNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("pn", pageNo);
        map.put("ps", pageSum);
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("userType", index + 1);
        map.put("longitude", locationBean.getLongitude());
        map.put("latitude", locationBean.getLatitude());
        map.put("orderBy", "");
        map.put("scope", "");
        map.put("keyword", seachTag.getTag());
        mPresenter.listData(map);
    }

    public void listData_Success(CustomerInfo clientInfoList) {
        System.out.println(hashCode() + "|" + (index + 1) + "|" + clientInfoList.total + "|" + "clientInfoList = " + clientInfoList);

        if (refreshLayout.isRefreshing()) {
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }
        stop();

        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(clientInfoList.obj.partnerCustomerList)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                clientListSeachAdapter.setNewData(data);
                return;
            }
            mStateView.showContent();//显示内容
        }
        data.addAll(clientInfoList.obj.partnerCustomerList);
        clientListSeachAdapter.setNewData(data);

//        data.addAll(customerInfo.obj.partnerCustomerList);
//        clientListAdapter.setIndex(index);
//        clientListAdapter.setNewData(data);
//        clientListAdapter.notifyDataSetChanged();
    }

    public void hotelMap_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
        clientListSeachAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);
    }

}
