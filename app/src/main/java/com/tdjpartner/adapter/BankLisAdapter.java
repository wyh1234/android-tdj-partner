package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.Bank;

import java.util.List;

public class BankLisAdapter extends BaseQuickAdapter<Bank, BaseViewHolder> {
    public BankLisAdapter(int layoutResId, @Nullable List<Bank> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Bank bank) {

    }
}
