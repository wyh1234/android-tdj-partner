package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.AttentionData;

import java.util.List;

public class AttentionDataAdapter extends BaseQuickAdapter<AttentionData, BaseViewHolder> {
    public AttentionDataAdapter(int layoutResId, @Nullable List<AttentionData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AttentionData attentionData) {

    }
}
