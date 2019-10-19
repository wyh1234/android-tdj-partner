package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.ClientSeachInfo;

import java.util.List;

public class ClientListSeachAdapter extends BaseQuickAdapter<ClientSeachInfo.ObjBean, BaseViewHolder> {
    public ClientListSeachAdapter(int layoutResId, @Nullable List<ClientSeachInfo.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientSeachInfo.ObjBean clientInfo) {
        baseViewHolder.setText(R.id.tv_name,clientInfo.getName());

    }
}
