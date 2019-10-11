package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.DiscountCoupon;

import java.util.List;

public class DiscountCouponAdapter extends BaseQuickAdapter<DiscountCoupon, BaseViewHolder> {
    private int mindex;
    public DiscountCouponAdapter(int layoutResId, @Nullable List<DiscountCoupon> data,int index) {
        super(layoutResId, data);
        this.mindex=index;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DiscountCoupon discountCoupon) {
        if (mindex==0){
            baseViewHolder.setGone(R.id.iv_status,false);
        }else if (mindex==1){
            baseViewHolder.setGone(R.id.iv_status,true);
            baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.yishiyong);
        }else {
            baseViewHolder.setGone(R.id.iv_status,true);
            baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.yiguoqi);
        }

    }
}
