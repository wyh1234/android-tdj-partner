package com.tdjpartner.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.OrderList;

import java.util.List;

public class MenberPaifangHistoryAdapter extends BaseQuickAdapter<DistinctList.ListBean, BaseViewHolder> {
    public MenberPaifangHistoryAdapter(int layoutResId, @Nullable List<DistinctList.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DistinctList.ListBean listBean) {
        baseViewHolder.setText(R.id.tv_name,listBean.getBuy_name());
        baseViewHolder.setText(R.id.tv_time,listBean.getCall_date().substring(11,16));
        baseViewHolder.setText(R.id.tv_call_name,"("+listBean.getCall_name()+")"+listBean.getCall_mobile());
        if (listBean.getOrder_status()==1){//未下单
            baseViewHolder.getView(R.id.ll).setVisibility(View.INVISIBLE);
            baseViewHolder.setText(R.id.tv_is_order,"未下单");
            baseViewHolder.setTextColor(R.id.tv_is_order, Color.parseColor("#999999"));
            baseViewHolder.setVisible(R.id.tv_notification,false);
        }else {
            baseViewHolder.getView(R.id.ll).setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_money,"￥"+listBean.getOrder_amount());
            baseViewHolder.setText(R.id.tv_is_order,"已下单");
            baseViewHolder.setTextColor(R.id.tv_is_order, Color.parseColor("#ff0000"));
            baseViewHolder.setVisible(R.id.tv_notification,true);
        }
        baseViewHolder.setText(R.id.tv_matters,listBean.getMatters());
        baseViewHolder.setText(R.id.tv_results,listBean.getResults());

    }
}
