package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HistoryInfo;

import java.util.List;

public class HistoryInfoAdapter extends BaseQuickAdapter<HistoryInfo, BaseViewHolder> {
    public HistoryInfoAdapter(int layoutResId, @Nullable List<HistoryInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HistoryInfo historyInfo) {
        baseViewHolder.setText(R.id.tv_tiltle,historyInfo.getTitle());

    }
}
