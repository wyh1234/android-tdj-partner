package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.ArrayList;
import java.util.List;

public class SimpleAfterSalesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleAfterSalesAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        ImageLoad.loadImageViewLoding(s,baseViewHolder.getView(R.id.image));

    }
}
