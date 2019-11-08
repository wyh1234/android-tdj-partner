package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HomeDataDetails;

import java.util.List;

public class HomeDataDetailsAdapter extends BaseQuickAdapter<HomeDataDetails.ObjBean.ListBean, BaseViewHolder> {
    public String title;


    public void setTitle(String title) {
        this.title = title;
    }

    public HomeDataDetailsAdapter(int layoutResId, @Nullable List<HomeDataDetails.ObjBean.ListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeDataDetails.ObjBean.ListBean objBean) {
        baseViewHolder.addOnClickListener(R.id.rl_call);
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
        if (title.equals("所有统计")){
            baseViewHolder.setText(R.id.tv_num3,objBean.getNotOrderDays()+"");
        }else {
            baseViewHolder.setText(R.id.tv_num3,objBean.getMonthAfterSaleTimes()+"");
        }

        baseViewHolder.setText(R.id.tv_username,objBean.getBoss());
        baseViewHolder.setText(R.id.tv_address1,objBean.getAddress());
        if (title.equals("所有统计")){
            baseViewHolder.setText(R.id.tv1,"总下单金额");
            baseViewHolder.setText(R.id.tv2,"总客均价");
            baseViewHolder.setText(R.id.tv3,"近30天频次");
            baseViewHolder.setText(R.id.tv4,"连续未下单");

        }else if (title.equals("月统计")){
            baseViewHolder.setText(R.id.tv1,"当月下单额");
            baseViewHolder.setText(R.id.tv2,"当月客均价");
            baseViewHolder.setText(R.id.tv3,"当月频次");
            baseViewHolder.setText(R.id.tv4,"退款次数");
        }else {
            baseViewHolder.setText(R.id.tv1,"今日下单额");
            baseViewHolder.setText(R.id.tv2,"今日客均价");
            baseViewHolder.setText(R.id.tv3,"下单次数");
            baseViewHolder.setText(R.id.tv4,"退款次数");
        }
    }
}
