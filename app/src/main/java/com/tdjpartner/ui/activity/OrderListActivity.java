package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private BaseQuickAdapter baseQuickAdapter;
    private List<OrderList> orderLists=new ArrayList<>();
    private Handler handler=new Handler();
    @Override
    protected IPresenter loadPresenter() {
        return null;
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
        layout.setScrollEnabled(false);
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
        getData(1);
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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                get_client_success();
            }
        }, 3000);//3秒后执行Runnable中的run方法


    }

    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(orderLists)) {
                orderLists.clear();
            }

        }
        orderLists.add(new OrderList());
        orderLists.add(new OrderList());
        orderLists.add(new OrderList());
        baseQuickAdapter.setNewData(orderLists);
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置
//        mStateView.showEmpty();
        stop();
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);

        }
        if (baseQuickAdapter.isLoadMoreEnable()){
            baseQuickAdapter.loadMoreComplete();
        }
        LogUtils.e(baseQuickAdapter.isLoadMoreEnable());
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,OrderDetailsActivity.class);
        startActivity(intent);

    }
}
