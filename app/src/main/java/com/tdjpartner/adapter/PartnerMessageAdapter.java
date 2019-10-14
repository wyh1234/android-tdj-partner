package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class PartnerMessageAdapter  extends BaseQuickAdapter<PartnerMessageInfo, BaseViewHolder> {
    public PartnerMessageAdapter(int layoutResId, @Nullable List<PartnerMessageInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerMessageInfo partnerMessageInfo) {
        baseViewHolder.setText(R.id.tv_content,partnerMessageInfo.getTitle());
        if (partnerMessageInfo.getCountNum()>0){
            baseViewHolder.setText(R.id.count_image,partnerMessageInfo.getCountNum()+"");
            baseViewHolder.setGone(R.id.count_image,true);
        }else {
            baseViewHolder.setGone(R.id.count_image,false);
        }
        baseViewHolder.setText(R.id.tv_remarks,partnerMessageInfo.getContent());
        if (!GeneralUtils.isNullOrZeroLenght(partnerMessageInfo.getImgUrl())){
            ImageLoad.loadImageViewLoding(partnerMessageInfo.getImgUrl(),baseViewHolder.getView(R.id.image_ani));

        }

    }
}
