package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class BankLisAdapter extends BaseQuickAdapter<BankList, BaseViewHolder> {
    public BankLisAdapter(int layoutResId, @Nullable List<BankList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BankList bank) {
        ImageLoad.loadImageViewLoding(bank.getImgUrl(),baseViewHolder.getView(R.id.iv));
        baseViewHolder.setText(R.id.tv_bank,bank.getBankName());

    }
}
