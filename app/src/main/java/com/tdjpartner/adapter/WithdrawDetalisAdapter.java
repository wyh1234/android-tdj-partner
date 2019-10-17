package com.tdjpartner.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class WithdrawDetalisAdapter extends BaseQuickAdapter<WithdrawDetalis.WithdrawDetalisData, BaseViewHolder> {
    private Context context;
    public WithdrawDetalisAdapter(int layoutResId, @Nullable List<WithdrawDetalis.WithdrawDetalisData> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WithdrawDetalis.WithdrawDetalisData withdrawDetalis) {
        if (withdrawDetalis.getStatus()==1){
            //提现成功
            baseViewHolder.setText(R.id.tv_right,"提现成功");
            baseViewHolder.setTextColor(R.id.tv_right, GeneralUtils.getColor(context,R.color.view_bg1));
        }else if (withdrawDetalis.getStatus()==2){
            //提现失败
            baseViewHolder.setText(R.id.tv_right,"提现失败");
            baseViewHolder.setTextColor(R.id.tv_right, GeneralUtils.getColor(context,R.color.gray_69));
        }else {
            //提现中
            baseViewHolder.setText(R.id.tv_right,"提现中");
            baseViewHolder.setTextColor(R.id.tv_right, GeneralUtils.getColor(context,R.color.view_bg1));
        }
        baseViewHolder.setText(R.id.tv_time,withdrawDetalis.getCreateTime());
        baseViewHolder.setText(R.id.tv_money,withdrawDetalis.getCapitalWithdrawal()+"");

    }
}
