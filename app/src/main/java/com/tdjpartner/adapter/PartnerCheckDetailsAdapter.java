package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class PartnerCheckDetailsAdapter extends BaseQuickAdapter<PartnerCheckDetails.UserApplyBean, BaseViewHolder> {
    public    List<PartnerCheckDetails.UserApplyBean> data;
    public PartnerCheckDetailsAdapter(int layoutResId, @Nullable List<PartnerCheckDetails.UserApplyBean> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PartnerCheckDetails.UserApplyBean partnerCheckDetails) {


        baseViewHolder.addOnClickListener(R.id.rl_one);
        baseViewHolder.addOnClickListener(R.id.rl_two);
        if (baseViewHolder.getLayoutPosition()==0){
            baseViewHolder.setText(R.id.tv_nodeName,"创客提交申请");
        }else {
            baseViewHolder.setText(R.id.tv_nodeName,partnerCheckDetails.getNodeName());
        }
        if (baseViewHolder.getLayoutPosition()==0){
            baseViewHolder.setText(R.id.tv_time,"申请日期："+partnerCheckDetails.getCreateTime());
            baseViewHolder.setText(R.id.tv_name,partnerCheckDetails.getNickName()+"\t"+partnerCheckDetails.getPhone()+"\t"+"提交申请");
            baseViewHolder.setGone(R.id.tv_time,true);
            baseViewHolder.setGone(R.id.tv_name,true);
        }else {
            if (partnerCheckDetails.getVerifyStatus()!=0){
                baseViewHolder.setText(R.id.tv_time,"申请日期："+partnerCheckDetails.getCreateTime());
                baseViewHolder.setText(R.id.tv_name,partnerCheckDetails.getNickName()+"\t"+partnerCheckDetails.getPhone()+"\t"+"提交申请");
                baseViewHolder.setGone(R.id.tv_time,true);
                baseViewHolder.setGone(R.id.tv_name,true);
            }else {
                baseViewHolder.setGone(R.id.tv_time,false);
                baseViewHolder.setGone(R.id.tv_name,false);
            }
        }


        if (baseViewHolder.getLayoutPosition()==0){
            baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.hongdian_bg);
            baseViewHolder.setGone(R.id.ll_isf,false);
            if (!GeneralUtils.isNullOrZeroLenght(partnerCheckDetails.getVerifyRemark())){
                baseViewHolder.setGone(R.id.rl_bz,true);
                baseViewHolder.setText(R.id.tv_verifyRemark,"备注:"+partnerCheckDetails.getVerifyRemark());
                baseViewHolder.setGone(R.id.ed_remark,false);
            }else {
                baseViewHolder.setGone(R.id.rl_bz,false);
            }
        }else  {
            if (partnerCheckDetails.getVerifyStatus()==0){
                if (partnerCheckDetails.getIsVerify()==0){
                    baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.huidian_bg);
                    baseViewHolder.setGone(R.id.ll_isf,true);


                    if (partnerCheckDetails.isB()){
                        baseViewHolder.getView(R.id.iv1).setSelected(true);
                        baseViewHolder.getView(R.id.iv2).setSelected(false);
                    }
                    if (partnerCheckDetails.isF()){
                        baseViewHolder.getView(R.id.iv1).setSelected(false);
                        baseViewHolder.getView(R.id.iv2).setSelected(true);
                    }
                }else {
                    baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.huidian_bg);
                    baseViewHolder.setGone(R.id.ll_isf,false);
                }


            }else if (partnerCheckDetails.getVerifyStatus()==2){
                baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.hongdian_bg);
                baseViewHolder.setGone(R.id.ll_isf,false);
            }else if (partnerCheckDetails.getVerifyStatus()==1){
                baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.huidian_bg);
                baseViewHolder.setGone(R.id.ll_isf,false);

            } else {
                baseViewHolder.setGone(R.id.ll_isf,false);
                baseViewHolder.setImageResource(R.id.iv_status,R.mipmap.hongchacha);
            }

            if (!GeneralUtils.isNullOrZeroLenght(partnerCheckDetails.getVerifyRemark())){
                baseViewHolder.setGone(R.id.rl_bz,true);
                if (partnerCheckDetails.getVerifyStatus()!=0){

                        baseViewHolder.setText(R.id.tv_verifyRemark,"备注:"+partnerCheckDetails.getVerifyRemark());
                        baseViewHolder.setGone(R.id.ed_remark,false);


                }else {
                if (partnerCheckDetails.getIsVerify()==0){
                    baseViewHolder.setGone(R.id.ed_remark,false);
                }else {
                    baseViewHolder.setGone(R.id.ed_remark,true);
                }

                }
            }else {
                if (partnerCheckDetails.getVerifyStatus()!=0){
                    baseViewHolder.setGone(R.id.rl_bz,false);
                }else {
                    if (partnerCheckDetails.getIsVerify()==0){
                        baseViewHolder.setGone(R.id.rl_bz,true);
                        baseViewHolder.setGone(R.id.ed_remark,true);
                    }else {
                        baseViewHolder.setGone(R.id.rl_bz,false);
                    }

                }
            }

        }




       if (baseViewHolder.getLayoutPosition()==data.size()-1){
           baseViewHolder.setBackgroundRes(R.id.ll_name,R.drawable.ll_shap_one);
       }

        ((EditText)baseViewHolder.getView(R.id.ed_remark)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                partnerCheckDetails.setVerifyRemark(s.toString());

            }
        });



    }
}
