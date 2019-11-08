package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.InvitationHistory;

import java.util.List;

public class InvitationHistoryAdapter extends BaseQuickAdapter<InvitationHistory.ObjBean, BaseViewHolder> {
    public InvitationHistoryAdapter(int layoutResId, @Nullable List<InvitationHistory.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, InvitationHistory.ObjBean invitationHistory) {

        baseViewHolder.setText(R.id.tv_name,invitationHistory.getCustomerName());
        baseViewHolder.setText(R.id.tv_username,invitationHistory.getCustomerNickName()+"\t\t"+invitationHistory.getCustomerPhone());
       /* baseViewHolder.setText(R.id.tv_phone,invitationHistory.getCustomerPhone());*/
        baseViewHolder.setText(R.id.tv_time,invitationHistory.getCreateTime().substring(0,10));
        if (invitationHistory.getIsActive()==1){
            baseViewHolder.setText(R.id.tv_isActive,"有效");
        }else {
            baseViewHolder.setText(R.id.tv_isActive,"无效");
        }


    }
}
