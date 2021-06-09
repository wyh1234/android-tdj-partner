package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.base.Fragment;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.CustomerInfo;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.ClientListPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.SortPopuWindow;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ClientListFragment extends Fragment<ClientListPresenter> implements OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, FollowUpPopuWindow.FollowUpListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_count)
    TextView tv_count;

    private int index = 0;
    String sort = "order";
    String scope = "500";
    private int customerId, pos, pageNo = 1, pageSum = 5;//翻页计数器
    private ClientListAdapter clientListAdapter;
    private List<ClientInfo> data = new ArrayList<>();
    public RxPermissions rxPermissions;
    private boolean aBoolean;
    private LocationBean locationBean;
    private FollowUpPopuWindow followUpPopuWindow;

    private SortPopuWindow sortPopuWindow;

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

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                sortPopuWindow.showPopupWindow();
                break;
        }
    }

    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this::onLoadmore);
        refreshLayout.setEnableLoadmore(true);
        rxPermissions = new RxPermissions(getActivity());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        clientListAdapter = new ClientListAdapter(R.layout.client_item, data);
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
        super.onUserVisible();
        index = getArguments().getInt("intent");
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        switch (index) {
            case 0:
                mPresenter.punchDistance(new ArrayMap<>());
                break;
            case 1:
                Map<String, String> arrayMap = new ArrayMap<>(5);
                arrayMap.put("order", "按下单时间最近排序");
                arrayMap.put("register", "按注册时间最近排序");
                arrayMap.put("gmv", "按GMV最大排序");
                arrayMap.put("letter", "按首字母排序");
                tv_search.setText(arrayMap.get("order"));
                ArrayList<String> list = new ArrayList<>();
                list.add("按下单时间最近排序");
                list.add("按注册时间最近排序");
                list.add("按GMV最大排序");
                list.add("按首字母排序");
                sortPopuWindow = new SortPopuWindow(getContext(), list);
                sortPopuWindow.setPopupWindowFullScreen(true);
                sortPopuWindow.setDayPopuWindowListener(n -> {
                    String s = list.get(n);
                    if (s.equals("按下单时间最近排序")) {
                        sort = "order";
                    } else if (s.equals("按注册时间最近排序")) {
                        sort = "register";
                    } else if (s.equals("按GMV最大排序")) {
                        sort = "gmv";
                    } else if (s.equals("按首字母排序")) {
                        sort = "letter";
                    }
                    tv_search.setText(arrayMap.get(sort));
                    onRefresh(null);
                });
                break;
            case 2:
                tv_search.setVisibility(View.GONE);
        }

        refreshLayout.autoRefresh();
    }

    @Subscribe
    public void eventCode(LocationBean locationBean) {
        if (!locationBean.getTag().contains("LOCATION") || PublicCache.flag != hashCode()) return;
        this.locationBean = locationBean;
        pull(1);
    }

    private void pull(int pageNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("pn", pageNo);
        map.put("ps", pageSum);
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("latitude", locationBean.getLatitude());
        map.put("longitude", locationBean.getLongitude());
        switch (index + 1) {
            case 1:
                map.put("userType", 1);
                map.put("orderBy", "order");
                map.put("scope", "" + scope);
                break;
            case 2:
                map.put("userType", 2);
                map.put("scope", "");
                map.put("orderBy", "" + sort);
                break;
            case 3:
                map.put("userType", 3);
                map.put("scope", "");
                map.put("orderBy", "");
                break;
        }
        mPresenter.listData(map);
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


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        if (!ListUtils.isEmpty(data)) {
            data.clear();
            clientListAdapter.notifyDataSetChanged();
        }
        getData();
        PublicCache.flag = hashCode();
    }

    public void onLoadmore(RefreshLayout refreshlayout) {
        System.out.println("~~ClientListFragment.onLoadmore~~");
        pull(++pageNo);
    }

    protected void getData() {
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        aBoolean = b;
                        if (b) {
                            LocationUtils.getInstance().startLocalService("LOCATION");
                        } else {
                            GeneralUtils.isNullOrZeroLenght("请开启位置信息");
                        }
                        hotelMap_failed();
                    }
                });
    }


    /**
     * StateView的根布局，默认是整个界面，如果需要变换可以重写此方法
     */
    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
            if (!ListUtils.isEmpty(data)) {
                data.clear();
                LogUtils.e(index);
                clientListAdapter.notifyDataSetChanged();
            }
        }

        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//        if (index == 0 || index == 1 ) {
        Intent intent = new Intent(getContext(), ClientDetailsActivity.class);
        intent.putExtra("customerId", data.get(i).getCustomerId() + "");
        startActivity(intent);
//        }

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

    public void listData_Success(CustomerInfo customerInfo) {
        stop();

        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(customerInfo.obj.partnerCustomerList)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                tv_count.setText("满足该条件的有0家");
                return;
            }
            mStateView.showContent();//显示内容
        }
        data.addAll(customerInfo.obj.partnerCustomerList);
        clientListAdapter.setIndex(index);
        clientListAdapter.setNewData(data);
        clientListAdapter.notifyDataSetChanged();
        tv_count.setText(index == 0 ? "满足该条件的有" + customerInfo.total + "家" : "共计有" + customerInfo.total + "家");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LogUtils.e(data.get(i).getName());
        if (view.getId() == R.id.tv_gj_status) {
            setCustomerId(data.get(i).getCustomerId());
            setPos(i);
            followUpPopuWindow = new FollowUpPopuWindow(getContext(), data.get(i).getName());
            followUpPopuWindow.setDismissWhenTouchOutside(false);
            followUpPopuWindow.setInterceptTouchEvent(false);
            followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
            followUpPopuWindow.showPopupWindow();
            followUpPopuWindow.setFollowUpListener(this);
        }
    }

    @Override
    public void onCancel() {
        followUpPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", getCustomerId());
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("userName", "");
        map.put("address", "");
        map.put("lon", "");
        map.put("lat", "");
        mPresenter.internationalWaters(map);

    }

    public void punchDistanceSuccess(String distance) {
        tv_search.setText("打卡范围" + distance + "米内");
        Map<String, String> arrayMap = new ArrayMap<>(2);
        arrayMap.put(distance, "打卡范围" + distance + "米内");
        arrayMap.put("zero", "全部客户");
        ArrayList<String> list = new ArrayList<>();
        list.add("打卡范围" + distance + "米内");
        list.add("全部客户");
        sortPopuWindow = new SortPopuWindow(getContext(), list);
        sortPopuWindow.setPopupWindowFullScreen(true);
        sortPopuWindow.setDayPopuWindowListener(n -> {
            if (n == 1) {
                scope = "";
                tv_search.setText("全部客户");
            } else {
                scope = distance;
                tv_search.setText(arrayMap.get(scope));
            }

            onRefresh(null);
        });
    }

    public void internationalWatersSuccess() {
        clientListAdapter.remove(getPos());
        clientListAdapter.notifyDataSetChanged();
        followUpPopuWindow.dismiss();
        tv_count.setText(index == 0 ? "满足该条件的有" + clientListAdapter.getData().size() + "家" : "共计有" + clientListAdapter.getData().size() + "家");
    }

    public void punchDistance_failed() {
        hotelMap_failed();
        tv_search.setText("打卡范围500米内");
        Map<String, String> arrayMap = new ArrayMap<>(2);
        arrayMap.put("500", "打卡范围500米内");
        arrayMap.put("zero", "全部客户");
        ArrayList<String> list = new ArrayList<>();
        list.add("打卡范围500米内");
        list.add("全部客户");
        sortPopuWindow = new SortPopuWindow(getContext(), list);
        sortPopuWindow.setPopupWindowFullScreen(true);
        sortPopuWindow.setDayPopuWindowListener(n -> {
            if (n == 1) {
                scope = "";
                tv_search.setText("全部客户");
            } else {
                scope = "500";
                tv_search.setText(arrayMap.get(scope));
            }
            onRefresh(null);
        });
    }

    public void internationalWaterFailed() {
        hotelMap_failed();
        Toast.makeText(mActivity, "跟进失败", Toast.LENGTH_SHORT).show();
    }
}
