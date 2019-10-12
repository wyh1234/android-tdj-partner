package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.PartnerMessageInfo;

import java.util.List;

public class PartnerMessageItemAdapter extends BaseQuickAdapter<PartnerMessageInfo, BaseViewHolder> {
    public PartnerMessageItemAdapter(int layoutResId, @Nullable List<PartnerMessageInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerMessageInfo partnerMessageInfo) {

    }
}
