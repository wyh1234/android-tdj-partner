package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.adapter.OrderListDetailsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private BaseQuickAdapter baseQuickAdapter;
    private List<OrderList> orderLists=new ArrayList<>();
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Eyes.translucentStatusBar(this,true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        orderLists.add(new OrderList());
        orderLists.add(new OrderList());
        orderLists.add(new OrderList());
        baseQuickAdapter=new OrderListDetailsAdapter(R.layout.order_details_item,orderLists);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.orderlist_details_layout;
    }
}
