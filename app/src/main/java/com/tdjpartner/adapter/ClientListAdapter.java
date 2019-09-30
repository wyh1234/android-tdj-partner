package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientInfo;

import java.util.List;

public class ClientListAdapter extends BaseQuickAdapter<ClientInfo, BaseViewHolder> {
    public ClientListAdapter(int layoutResId, @Nullable List<ClientInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientInfo clientInfo) {

    }
}
