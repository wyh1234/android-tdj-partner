package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.OrderList;

import java.util.List;

public class OrderListDetailsAdapter extends BaseQuickAdapter<OrderList, BaseViewHolder> {
    public OrderListDetailsAdapter(int layoutResId, @Nullable List<OrderList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderList orderList) {

    }
}
