package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.ToMakeMoneyyRanking;

import java.util.List;

public class ToMakeMoneyRankingAdapter extends BaseQuickAdapter<ToMakeMoneyyRanking, BaseViewHolder> {
    public ToMakeMoneyRankingAdapter(int layoutResId, @Nullable List<ToMakeMoneyyRanking> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ToMakeMoneyyRanking toMakeMoneyyRanking) {

    }
}
