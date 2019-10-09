package com.tdjpartner;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.PartnerCheck;

import java.util.List;

public class PartnerCheckAdapter extends BaseQuickAdapter<PartnerCheck, BaseViewHolder> {
    public PartnerCheckAdapter(int layoutResId, @Nullable List<PartnerCheck> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerCheck partnerCheck) {

    }
}
