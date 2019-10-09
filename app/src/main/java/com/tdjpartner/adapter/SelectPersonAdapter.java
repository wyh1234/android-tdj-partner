package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.SelectPerson;

import java.util.List;

public class SelectPersonAdapter extends BaseQuickAdapter<SelectPerson, BaseViewHolder> {
    public SelectPersonAdapter(int layoutResId, @Nullable List<SelectPerson> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SelectPerson selectPerson) {
        baseViewHolder.getView(R.id.iv).setSelected(selectPerson.isF());
    }
}
