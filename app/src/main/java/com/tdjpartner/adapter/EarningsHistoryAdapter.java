package com.tdjpartner.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class EarningsHistoryAdapter extends BaseQuickAdapter<EarningsHistory.ObjBean.ListBean, BaseViewHolder> {
    private Context context;
    public EarningsHistoryAdapter(int layoutResId, @Nullable List<EarningsHistory.ObjBean.ListBean> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, EarningsHistory.ObjBean.ListBean earningsHistory) {
        baseViewHolder.setText(R.id.tv_customerName,earningsHistory.getCustomerName());
        baseViewHolder.setText(R.id.tv_customerNickName,earningsHistory.getCustomerNickName());
        baseViewHolder.setText(R.id.tv_customerNickName,earningsHistory.getCustomerNickName());
        baseViewHolder.setText(R.id.tv_customerPhone,earningsHistory.getCustomerPhone());
        baseViewHolder.setText(R.id.tv_createTime,earningsHistory.getCreateTime().substring(0,10));

        if (earningsHistory.getBizType()==1){
            baseViewHolder.setText(R.id.tv_amount,"+"+earningsHistory.getAmountCommission());
            baseViewHolder.setTextColor(R.id.tv_amount, GeneralUtils.getColor(context,R.color.view_bg1));
        }else {
            baseViewHolder.setTextColor(R.id.tv_amount, GeneralUtils.getColor(context,R.color.gray_69));
            baseViewHolder.setText(R.id.tv_amount,earningsHistory.getAmountCommission()+"");
        }

    }
}
