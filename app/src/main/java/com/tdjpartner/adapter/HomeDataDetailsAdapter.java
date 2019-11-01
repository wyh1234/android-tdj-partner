package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HomeDataDetails;

import java.util.List;

public class HomeDataDetailsAdapter extends BaseQuickAdapter<HomeDataDetails.ObjBean.ListBean, BaseViewHolder> {
    public HomeDataDetailsAdapter(int layoutResId, @Nullable List<HomeDataDetails.ObjBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeDataDetails.ObjBean.ListBean objBean) {
        baseViewHolder.setText(R.id.tv_name,objBean.getName());
        baseViewHolder.setText(R.id.tv_boss,"负责人:"+objBean.getPartnerName());
        if (objBean.getAuth()==0){
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.weirenzheng);
        }else {//已认证
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.yirenzheng);
        }

        baseViewHolder.setText(R.id.tv_regionCollNo,objBean.getRegionCollNo());
        baseViewHolder.setText(R.id.tv_num,objBean.getTodayAmount()+"");
        baseViewHolder.setText(R.id.tv_num1,objBean.getAverageAmount()+"");
        baseViewHolder.setText(R.id.tv_num2,objBean.getMonthTimes()+"");
        baseViewHolder.setText(R.id.tv_num3,objBean.getNotCallDays()+"");
        baseViewHolder.setText(R.id.tv_username,objBean.getBoss());
        baseViewHolder.setText(R.id.tv_address,objBean.getAddress());
    }
}
