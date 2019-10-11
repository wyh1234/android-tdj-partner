package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.WithdrawDetalis;

import java.util.List;

public class WithdrawDetalisAdapter extends BaseQuickAdapter<WithdrawDetalis, BaseViewHolder> {
    public WithdrawDetalisAdapter(int layoutResId, @Nullable List<WithdrawDetalis> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WithdrawDetalis withdrawDetalis) {

    }
}
