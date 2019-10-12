package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private BaseQuickAdapter baseQuickAdapter;
    private List<OrderList> orderLists=new ArrayList<>();
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
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
