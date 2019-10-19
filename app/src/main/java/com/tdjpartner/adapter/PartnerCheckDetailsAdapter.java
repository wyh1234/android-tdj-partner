package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.PartnerCheckDetails;

import java.util.List;

public class PartnerCheckDetailsAdapter extends BaseQuickAdapter<PartnerCheckDetails, BaseViewHolder> {
    private  List<PartnerCheckDetails> data;
    public PartnerCheckDetailsAdapter(int layoutResId, @Nullable List<PartnerCheckDetails> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerCheckDetails partnerCheckDetails) {
  /*      if (partnerCheckDetails.isF()){
            baseViewHolder.setGone(R.id.ll_isf,false);
            baseViewHolder.setImageResource(R.id.iv1,R.mipmap.xuanzhong);
            baseViewHolder.setImageResource(R.id.iv2,R.mipmap.weixuanzhong);
        }else {
            baseViewHolder.setGone(R.id.ll_isf,true);
            baseViewHolder.setImageResource(R.id.iv1,R.mipmap.weixuanzhong);
            baseViewHolder.setImageResource(R.id.iv2,R.mipmap.xuanzhong);
        }*/
        if (baseViewHolder.getAdapterPosition()==0){
            baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.hongdian_bg);
        }else {
            baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.huidian_bg);
        }
        if (baseViewHolder.getAdapterPosition()==data.size()-1){
            baseViewHolder.setBackgroundRes(R.id.ll_name,R.drawable.ll_shap_one);
        }else {
            baseViewHolder.setBackgroundRes(R.id.ll_name,R.drawable.ll_shap);
        }
        baseViewHolder.addOnClickListener(R.id.rl_one);
        baseViewHolder.addOnClickListener(R.id.rl_two);
    }
}
