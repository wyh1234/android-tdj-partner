package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.utils.popuwindow.AfterSalesTypePopuWindow;

import java.util.List;

public class AfterSalesTypeAdapter extends BaseQuickAdapter<AfterSalesType, BaseViewHolder> {
    public AfterSalesTypeAdapter(int layoutResId, @Nullable List<AfterSalesType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AfterSalesType afterSalesType) {
        baseViewHolder.getView(R.id.iv_sel).setSelected(afterSalesType.isFlag());
        baseViewHolder.setText(R.id.tv_type,afterSalesType.getTypeName());


    }

}
