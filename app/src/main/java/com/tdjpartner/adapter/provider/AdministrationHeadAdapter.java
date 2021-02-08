package com.tdjpartner.adapter.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.model.ParentList;

public class AdministrationHeadAdapter extends BaseItemProvider<ParentList.Headinfo, BaseViewHolder> {
    @Override
    public int viewType() {
        return AdministrationAdapter.HEAD;
    }

    @Override
    public int layout() {
        return R.layout.administration_baifang_head_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ParentList.Headinfo headinfo, int pos) {
        if (headinfo.getType()==1){
            baseViewHolder.setText(R.id.tv_type,"出勤专员");
            baseViewHolder.setText(R.id.tv_one,"拜访转化数");
            baseViewHolder.setText(R.id.tv_head_staste,"出勤状态");
            baseViewHolder.setGone(R.id.tv_head_money,false);
            baseViewHolder.setText(R.id.tv_two,"转化数/拜访数");

        }else {
            baseViewHolder.setText(R.id.tv_type,"拜访酒店");
            baseViewHolder.setText(R.id.tv_one,"酒店名称");
            baseViewHolder.setText(R.id.tv_head_staste,"下单状态");
            baseViewHolder.setText(R.id.tv_head_money,"下单金额");
            baseViewHolder.setGone(R.id.tv_head_money,true);
            baseViewHolder.setText(R.id.tv_two,"专员");

        }
        baseViewHolder.setText(R.id.tv_curr,headinfo.getCurr()+"/");
        baseViewHolder.setText(R.id.tv_total,headinfo.getTotal()+"");

    }
}
