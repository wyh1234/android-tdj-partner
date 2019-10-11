package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.BaiFangHistory;

import java.util.List;

public class BaiFangHistoryAdapter extends BaseQuickAdapter<BaiFangHistory, BaseViewHolder> {
    public BaiFangHistoryAdapter(int layoutResId, @Nullable List<BaiFangHistory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaiFangHistory baiFangHistory) {

    }
}
