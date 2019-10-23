package com.tdjpartner;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class PartnerCheckAdapter extends BaseQuickAdapter<PartnerCheck.ObjBean, BaseViewHolder> {
    public PartnerCheckAdapter(int layoutResId, @Nullable List<PartnerCheck.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerCheck.ObjBean partnerCheck) {
        baseViewHolder.setText(R.id.tv_leaderName,"负责人："+partnerCheck.getLeaderName()+"(主账号)");
        baseViewHolder.setText(R.id.tv_enterpriseCode,partnerCheck.getEnterpriseCode());
        baseViewHolder.setText(R.id.tv_nickNameAndPhone,partnerCheck.getNickName()+"\t"+partnerCheck.getPhone());
        baseViewHolder.setText(R.id.tv_createdAt,partnerCheck.getCreatedAt());
        baseViewHolder.setText(R.id.tv_address,partnerCheck.getEnterpriseMsg());

        ImageLoad.loadImageViewLoding(partnerCheck.getImageUrl(),baseViewHolder.getView(R.id.tv_imageUrl),R.mipmap.yingyezhao_bg);
        ImageLoad.loadImageViewLoding(partnerCheck.getBzlicenceUrl(),baseViewHolder.getView(R.id.tv_bzlicenceUrl),R.mipmap.yingyezhao_bg);
        if (partnerCheck.getVerifyStatus()==0){
            baseViewHolder.setText(R.id.tv_verifyStatus,"待审核");
            baseViewHolder.setBackgroundRes(R.id.tv_verifyStatus,R.mipmap.check_item_hui_bg);
            baseViewHolder.setTextColor(R.id.tv_verifyStatus, GeneralUtils.getColor(baseViewHolder.getView(R.id.tv_verifyStatus).getContext(),R.color.gray_66));
        }else if (partnerCheck.getVerifyStatus()==3){
            baseViewHolder.setText(R.id.tv_verifyStatus,"审核驳回");
            baseViewHolder.setBackgroundRes(R.id.tv_verifyStatus,R.mipmap.shbh);
            baseViewHolder.setTextColor(R.id.tv_verifyStatus, GeneralUtils.getColor(baseViewHolder.getView(R.id.tv_verifyStatus).getContext(),R.color.white));
        }else if (partnerCheck.getVerifyStatus()==2){
            baseViewHolder.setText(R.id.tv_verifyStatus,"审核通过");
            baseViewHolder.setBackgroundRes(R.id.tv_verifyStatus,R.mipmap.shtg);
            baseViewHolder.setTextColor(R.id.tv_verifyStatus, GeneralUtils.getColor(baseViewHolder.getView(R.id.tv_verifyStatus).getContext(),R.color.white));
        }else {
            baseViewHolder.setGone(R.id.tv_verifyStatus,false);

        }
    }
}
