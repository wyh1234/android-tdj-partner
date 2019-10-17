package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ToMakeMoney;

import java.util.List;

public class ToMakeMoneyRankingAdapter extends BaseQuickAdapter<ToMakeMoney.TopTenDateBean, BaseViewHolder> {
    public ToMakeMoneyRankingAdapter(int layoutResId, @Nullable List<ToMakeMoney.TopTenDateBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ToMakeMoney.TopTenDateBean toMakeMoneyyRanking) {
        baseViewHolder.setText(R.id.tv_partnerPhone,toMakeMoneyyRanking.getPartnerPhone());
        baseViewHolder.setText(R.id.tv_amountCommission,toMakeMoneyyRanking.getAmountCommission()+"元");



    }
}
