package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.DropOuting;

import java.util.List;

public class DropOutingAdapter extends BaseQuickAdapter<DropOuting, BaseViewHolder> {
    public DropOutingAdapter(int layoutResId, @Nullable List<DropOuting> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DropOuting dropOuting) {

    }
}