package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.RankingData;

import java.util.List;

public class RankingAdapter extends BaseQuickAdapter<RankingData, BaseViewHolder> {
    public RankingAdapter(int layoutResId, @Nullable List<RankingData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankingData rankingData) {
//        LogUtils.e(baseViewHolder.getAdapterPosition());

    }
}
