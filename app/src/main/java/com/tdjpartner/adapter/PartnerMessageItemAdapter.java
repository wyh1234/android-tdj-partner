package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.PartnerMessageItemInfo;

import java.util.List;

public class PartnerMessageItemAdapter extends BaseQuickAdapter<PartnerMessageItemInfo.ObjBean, BaseViewHolder> {
    public PartnerMessageItemAdapter(int layoutResId, @Nullable List<PartnerMessageItemInfo.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerMessageItemInfo.ObjBean objBean) {
        baseViewHolder.setText(R.id.tv,objBean.getTitle());
        baseViewHolder.setText(R.id.tv_content,objBean.getContent());
        baseViewHolder.setText(R.id.tv_time,objBean.getCreateTime());

    }
}
