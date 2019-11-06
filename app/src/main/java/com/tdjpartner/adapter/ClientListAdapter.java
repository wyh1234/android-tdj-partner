package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class ClientListAdapter extends BaseQuickAdapter<ClientInfo, BaseViewHolder> {
    private int index;
    public ClientListAdapter(int layoutResId, @Nullable List<ClientInfo> data) {
        super(layoutResId, data);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientInfo clientInfo) {
        baseViewHolder.setText(R.id.tv_name,clientInfo.getName());
        baseViewHolder.setText(R.id.tv_boss,"负责人:"+clientInfo.getPartnerName());
        if (clientInfo.getAuth()==0){
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.weirenzheng);
        }else {//已认证
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.yirenzheng);
        }

        baseViewHolder.setText(R.id.tv_regionCollNo,clientInfo.getRegionCollNo());
        baseViewHolder.setText(R.id.tv_num,clientInfo.getTodayAmount()+"");
        baseViewHolder.setText(R.id.tv_num1,clientInfo.getAverageAmount()+"");
        baseViewHolder.setText(R.id.tv_num2,clientInfo.getMonthTimes()+"");
        baseViewHolder.setText(R.id.tv_num3,clientInfo.getMonthAfterSaleTimes()+"");

        baseViewHolder.setText(R.id.tv_username,clientInfo.getBoss());
        baseViewHolder.setText(R.id.tv_address,clientInfo.getAddress());
        baseViewHolder.addOnClickListener(R.id.rl_call);
        if (index==1){
            baseViewHolder.setGone(R.id.tv_gj_status,true);
        }else {
            baseViewHolder.setGone(R.id.tv_gj_status,false);
        }
        baseViewHolder.addOnClickListener(R.id.tv_gj_status);
    }
}
