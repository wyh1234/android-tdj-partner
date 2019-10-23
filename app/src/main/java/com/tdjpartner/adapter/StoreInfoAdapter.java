package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientDetailsStoreInfo;
import com.tdjpartner.model.StoreInfo;

import java.util.List;

public class StoreInfoAdapter extends BaseQuickAdapter<ClientDetailsStoreInfo, BaseViewHolder> {
    public StoreInfoAdapter(int layoutResId, @Nullable List<ClientDetailsStoreInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientDetailsStoreInfo storeInfo) {
        baseViewHolder.setImageResource(R.id.iv,storeInfo.getRes());
        baseViewHolder.setText(R.id.tv_tilte,storeInfo.getTitle());
        if (storeInfo.getTotal()!=null){
            baseViewHolder.setText(R.id.tv_total,String.valueOf(storeInfo.getTotal()));
        }


    }
}
