package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.AttentionData;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class AttentionDataAdapter extends BaseQuickAdapter<HomeData.PartnerApproachDataBean, BaseViewHolder> {
    public AttentionDataAdapter(int layoutResId, @Nullable List<HomeData.PartnerApproachDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeData.PartnerApproachDataBean attentionData) {
        baseViewHolder.setText(R.id.image_name,attentionData.getTitle());
        ImageLoad.loadImageView(baseViewHolder.getView(R.id.image_ani).getContext(),attentionData.getMenuPic(),baseViewHolder.getView(R.id.image_ani));
        if (attentionData.getSubscriptNum()==null||attentionData.getSubscriptNum()==0){
            baseViewHolder.setGone(R.id.count_image,false);
        }else {
            baseViewHolder.setGone(R.id.count_image,true);
            baseViewHolder.setText(R.id.count_image,attentionData.getSubscriptNum()+"");

        }

    }
}
