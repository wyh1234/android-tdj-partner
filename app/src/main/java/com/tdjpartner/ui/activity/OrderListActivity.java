package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.OrderListPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderListActivity extends BaseActivity<OrderListPresenter> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private BaseQuickAdapter baseQuickAdapter;
    private List<OrderList.ItemsBean> orderLists=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected OrderListPresenter loadPresenter() {
        return new OrderListPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        CustomLinearLayout layout = new CustomLinearLayout(getContext(),
                LinearLayoutManager.VERTICAL, false);
        layout.setScrollEnabled(true);
        recyclerView_list.setLayoutManager(layout);
        baseQuickAdapter=new OrderListAdapter(R.layout.orderlist_item,orderLists);
        recyclerView_list.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnLoadMoreListener(this,recyclerView_list);
        baseQuickAdapter.setOnItemClickListener(this);
        refreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.orderlist_layout;
    }

    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.e(pageNo);
        refreshLayout.setRefreshing(false);
        getData(++pageNo);
    }
    @Override
    public void onPause() {
        super.onPause();
        stop();
    }
    protected  void getData(int pn){
        Map<String ,Object> map=new HashMap<>();
//        map.put("status","trade_success,wait_buyer_evaluate");
        map.put("userType",0);
        map.put("pn",pn);
        map.put("customerId",Integer.parseInt(getIntent().getStringExtra("buyId")));
//        map.put("customerId",45);
        map.put("ps",10);
        map.put("site", UserUtils.getInstance().getLoginBean().getSite());
        mPresenter.order_pageList(map);

    }

    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);

        }
        if (baseQuickAdapter.isLoadMoreEnable()){
            baseQuickAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,OrderDetailsActivity.class);
        intent.putExtra("orderNO",orderLists.get(i).getOrderNo());
        startActivity(intent);

    }

    public void order_pageList_Success(OrderList orderList) {

        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(orderLists)) {
                orderLists.clear();
            }
        }
        stop();
        if (ListUtils.isEmpty(orderLists)) {
                if (ListUtils.isEmpty(orderList.getObj())) {
                    //获取不到数据,显示空布局
                    mStateView.showEmpty();
                    return;
                }
                mStateView.showContent();//显示内容
            }

            if (ListUtils.isEmpty(orderList.getObj())) {
                //已经获取数据
                if (pageNo!=1){
                    baseQuickAdapter.loadMoreEnd();
                }
                return;
            }
        orderLists.addAll(orderList.getObj());
        baseQuickAdapter.setNewData(orderLists);
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//
    }

    public void order_pageList_Failed() {
        stop();
        if (ListUtils.isEmpty(orderLists)) {
            mStateView.showEmpty();//显示重试的布局
        }
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);
    }

    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }

}
