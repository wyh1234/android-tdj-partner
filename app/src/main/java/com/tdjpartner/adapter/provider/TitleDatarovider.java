package com.tdjpartner.adapter.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.model.TitleData;

public class TitleDatarovider extends BaseItemProvider<TitleData,BaseViewHolder> {
    @Override
    public int viewType() {
        return MessageListAdapter.TYPE_TITLE;
    }

    @Override
    public int layout() {
        return R.layout.titledata_layout;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, TitleData titleData, int i) {



    }

}
