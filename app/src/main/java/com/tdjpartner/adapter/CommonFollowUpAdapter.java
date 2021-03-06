package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class CommonFollowUpAdapter extends BaseQuickAdapter<DropOuting.ObjBean, BaseViewHolder> {
    public CommonFollowUpAdapter(int layoutResId, @Nullable List<DropOuting.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DropOuting.ObjBean dropOuting) {
        baseViewHolder.addOnClickListener(R.id.tv_gj_status);

        baseViewHolder.setText(R.id.tv_name,dropOuting.getName());
        baseViewHolder.setGone(R.id.tv_boss,false
        );
        if (dropOuting.getAuth()==0){
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.weirenzheng);
        }else {//已认证
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.yirenzheng);
        }


        baseViewHolder.setText(R.id.tv_regionCollNo,dropOuting.getRegionCollNo());
        if (type.equals("followNot")){
            baseViewHolder.setText(R.id.tv_gj_status,"待跟进");
            baseViewHolder.setTextColor(R.id.tv_gj_status, GeneralUtils.getColor(baseViewHolder.getView(R.id.tv_gj_status).getContext(),R.color.white));
            baseViewHolder.setBackgroundRes(R.id.tv_gj_status,R.mipmap.dgj_bg);

        }else {
            baseViewHolder.setText(R.id.tv_gj_status,"已跟进");
            baseViewHolder.setBackgroundRes(R.id.tv_gj_status,R.mipmap.dgj_hui_bg);
        }

        baseViewHolder.setText(R.id.tv_num,dropOuting.getTodayAmount()+"");
        baseViewHolder.setText(R.id.tv_num1,dropOuting.getAverageAmount()+"");
        baseViewHolder.setText(R.id.tv_num2,dropOuting.getTodayTimes()+"");
        baseViewHolder.setText(R.id.tv_num3,dropOuting.getTodayAfterSaleTimes()+"");
        baseViewHolder.setText(R.id.tv_username,dropOuting.getBoss());
        baseViewHolder.setText(R.id.tv_address,dropOuting.getAddress());
        baseViewHolder.addOnClickListener(R.id.ll_call);

    }


    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
