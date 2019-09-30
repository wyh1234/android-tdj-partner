package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.StoreInfo;

import java.util.List;

public class StoreInfoAdapter extends BaseQuickAdapter<StoreInfo, BaseViewHolder> {
    public StoreInfoAdapter(int layoutResId, @Nullable List<StoreInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StoreInfo storeInfo) {

    }
}
