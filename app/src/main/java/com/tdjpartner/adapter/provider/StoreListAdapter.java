package com.tdjpartner.adapter.provider;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class StoreListAdapter extends BaseItemProvider<StoreInfo.ObjBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return MessageListAdapter.StoreList;
    }

    @Override
    public int layout() {
        return R.layout.store_list_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, StoreInfo.ObjBean storeInfo, int i) {
        ImageLoad.loadImageViewLoding(storeInfo.getLogoImageUrl(),baseViewHolder.getView(R.id.iv));
        baseViewHolder.setText(R.id.tv,storeInfo.getName());
        baseViewHolder.setText(R.id.tv_gz,storeInfo.getFavoriteCount()+"人关注");
        baseViewHolder.setText(R.id.tv_pf,storeInfo.getStoreScore()+"分");
    }

}
