package com.tdjpartner.adapter.provider;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.model.GoodsInfo;

import java.util.List;

public class GoodsListAdapter extends BaseItemProvider<GoodsInfo, BaseViewHolder> {

    @Override
    public int viewType() {
        return MessageListAdapter.GoodsList;
    }

    @Override
    public int layout() {
        return R.layout.goods_list_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, GoodsInfo goodsInfo, int i) {

    }
}
