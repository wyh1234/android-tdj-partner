package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.EarningsHistory;

import java.util.List;

public class EarningsHistoryAdapter extends BaseQuickAdapter<EarningsHistory, BaseViewHolder> {
    public EarningsHistoryAdapter(int layoutResId, @Nullable List<EarningsHistory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, EarningsHistory earningsHistory) {

    }
}
