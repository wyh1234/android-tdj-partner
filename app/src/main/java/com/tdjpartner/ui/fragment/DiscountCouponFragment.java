package com.tdjpartner.ui.fragment;

import android.content.Context;
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
import com.tdjpartner.base.Fragment;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.mvp.presenter.DiscountCouponPresenter;
import com.tdjpartner.ui.activity.DiscountCouponActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class DiscountCouponFragment extends Fragment<DiscountCouponPresenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private List<DiscountCoupon.ItemsBean> discountCouponArrayList=new ArrayList<>();
    private DiscountCouponAdapter discountCouponAdapter;
    private DiscountCouponActivity activity;

    //status：0未使用，1已使用，2已过期，
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
        discountCouponAdapter=new DiscountCouponAdapter(R.layout.discount_coupon_item,discountCouponArrayList);
        recyclerView_list.setAdapter(discountCouponAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(DiscountCouponActivity)context;
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
    protected DiscountCouponPresenter loadPresenter() {
        return new DiscountCouponPresenter();
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
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", activity.customerId);
        map.put("userType",0);
        map.put("status",index);
        map.put("pn",pn);
        map.put("ps",10);
        map.put("site",UserUtils.getInstance().getLoginBean().getSite());
        mPresenter.coupons_findByUser(map);
    }


    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        LogUtils.i(refreshLayout.isRefreshing());
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
            if (!ListUtils.isEmpty(discountCouponArrayList)) {
                discountCouponArrayList.clear();
                discountCouponAdapter.notifyDataSetChanged();
            }
        }
        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }

    public void coupons_findByUser_Success(DiscountCoupon discountCoupon) {
        discountCouponAdapter.setMindex(index);
        stop();
        if (ListUtils.isEmpty(discountCouponArrayList)) {
            if (ListUtils.isEmpty(discountCoupon.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(discountCoupon.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
            }else {
                GeneralUtils.showToastshort("暂无数据");
            }
            return;
        }
        discountCouponArrayList.addAll(discountCoupon.getObj());
        discountCouponAdapter.setNewData(discountCouponArrayList);
    }

    public void coupons_findByUser_Failed() {
        stop();
        if (ListUtils.isEmpty(discountCouponArrayList)) {
            mStateView.showEmpty();//显示重试的布局
        }
    }
}
