package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.ClientMapInfo;

import java.util.List;

public class ClientMapAdapter extends BaseQuickAdapter<ClientMapInfo, BaseViewHolder> {
    public ClientMapAdapter(int layoutResId, @Nullable List<ClientMapInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientMapInfo clientMapInfo) {

    }
}
