package com.tdjpartner.adapter.provider;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.model.StoreInfo;

import java.util.List;

public class StoreListAdapter extends BaseItemProvider<StoreInfo, BaseViewHolder> {
    @Override
    public int viewType() {
        return MessageListAdapter.StoreList;
    }

    @Override
    public int layout() {
        return R.layout.store_list_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, StoreInfo storeInfo, int i) {

    }

}
