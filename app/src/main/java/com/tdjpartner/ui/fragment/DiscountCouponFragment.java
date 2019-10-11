package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.DiscountCouponAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DiscountCouponFragment extends BaseFrgment implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private List<DiscountCoupon> discountCouponArrayList=new ArrayList<>();
    private DiscountCouponAdapter discountCouponAdapter;
    public static DiscountCouponFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        DiscountCouponFragment f = new DiscountCouponFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        discountCouponAdapter=new DiscountCouponAdapter(R.layout.discount_coupon_item,discountCouponArrayList,index);
        recyclerView_list.setAdapter(discountCouponAdapter);

    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");
        LogUtils.i(index);
        refreshLayout.autoRefresh();
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getContentId() {
        return R.layout.discount_coupon_fragment_layout;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        getData(1);
    }
    protected  void getData(int pn){
        get_client_success();


    }

    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(discountCouponArrayList)) {
                discountCouponArrayList.clear();
            }
        }
        discountCouponArrayList.add(new DiscountCoupon());
        discountCouponArrayList.add(new DiscountCoupon());
        discountCouponArrayList.add(new DiscountCoupon());

        discountCouponAdapter.setNewData(discountCouponArrayList);
//        mStateView.showEmpty();
        stop();
    }

    public View getStateViewRoot() {
        return recyclerView_list;
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


}
