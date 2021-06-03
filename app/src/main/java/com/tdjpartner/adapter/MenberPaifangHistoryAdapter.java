package com.tdjpartner.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;

public class MenberPaifangHistoryAdapter extends BaseQuickAdapter<DistinctList.ListBean, BaseViewHolder> {
    private String time;
    public MenberPaifangHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }
    public void setTime(String time) {
        this.time = time;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DistinctList.ListBean listBean) {
        baseViewHolder.setText(R.id.tv_name,listBean.getBuy_name());
        baseViewHolder.setText(R.id.tv_address,listBean.getAddress());
        baseViewHolder.setText(R.id.tv_time,listBean.getCall_date().substring(11,19));
        ImageLoad.loadImageView(baseViewHolder.getView(R.id.iv).getContext(),listBean.getCall_pic(),baseViewHolder.getView(R.id.iv));
        baseViewHolder.addOnClickListener(R.id.iv);
        baseViewHolder.addOnClickListener(R.id.tv_notification);

//        baseViewHolder.setText(R.id.tv_call_name,"("+listBean.getCall_name()+")"+listBean.getMobile());
        baseViewHolder.setText(R.id.tv_call_name,"("+listBean.getUser_name()+")"+listBean.getMobile());


        if (listBean.getOrder_status()==0){//未下单
            baseViewHolder.getView(R.id.ll).setVisibility(View.INVISIBLE);
            baseViewHolder.setText(R.id.tv_is_order,"未下单");
            baseViewHolder.setTextColor(R.id.tv_is_order, Color.parseColor("#999999"));
            if (time.equals(GeneralUtils.getCurr())){
                baseViewHolder.getView(R.id.tv_notification).setVisibility(View.VISIBLE);
            }else {
                baseViewHolder.getView(R.id.tv_notification).setVisibility(View.INVISIBLE);
            }
        }else {
            baseViewHolder.getView(R.id.ll).setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_money,"￥"+listBean.getOrder_amount());
            baseViewHolder.setText(R.id.tv_is_order,"已下单");
            baseViewHolder.setTextColor(R.id.tv_is_order, Color.parseColor("#ff0000"));
            baseViewHolder.setGone(R.id.tv_notification,false);
        }
        baseViewHolder.setText(R.id.tv_matters,listBean.getMatters());
        baseViewHolder.setText(R.id.tv_results,listBean.getResults());

    }
}
