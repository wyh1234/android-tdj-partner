package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.Filterinfo;

import java.util.List;

public class WithdrawDetalisFilterAdapter extends BaseQuickAdapter<Filterinfo, BaseViewHolder> {
    public WithdrawDetalisFilterAdapter(int layoutResId, @Nullable List<Filterinfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Filterinfo filterinfo) {
        baseViewHolder.setText(R.id.tv_tiltle,filterinfo.getTitle());
        baseViewHolder.getView(R.id.tv_tiltle).setSelected(filterinfo.isF());

    }
}
