package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class BaiFangHistoryAdapter extends BaseQuickAdapter<BaiFangHistory.ObjBean, BaseViewHolder> {
    public BaiFangHistoryAdapter(int layoutResId, @Nullable List<BaiFangHistory.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaiFangHistory.ObjBean baiFangHistory) {
        ImageLoad.loadImageViewLoding(baiFangHistory.getBuyPic(),baseViewHolder.getView(R.id.iv));
        baseViewHolder.setText(R.id.tv_buyName,baiFangHistory.getBuyName());
        baseViewHolder.setText(R.id.tv_callName,baiFangHistory.getCallName());

        baseViewHolder.setText(R.id.tv_address,"拜访地点："+baiFangHistory.getAddress());
        baseViewHolder.setText(R.id.tv_time,"拜访日期："+baiFangHistory.getCallDate());
        baseViewHolder.setText(R.id.tv_call_name_one,"拜访人："+baiFangHistory.getCallName());
        baseViewHolder.setText(R.id.tv_matters,"主要事宜："+baiFangHistory.getMatters());
        baseViewHolder.setText(R.id.tv_results,"拜访结果："+baiFangHistory.getResults());

    }
}
