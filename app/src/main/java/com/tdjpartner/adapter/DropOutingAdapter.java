package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.DropOuting;

import java.util.List;

public class DropOutingAdapter extends BaseQuickAdapter<DropOuting.ObjBean, BaseViewHolder> {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DropOutingAdapter(int layoutResId, @Nullable List<DropOuting.ObjBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DropOuting.ObjBean dropOuting) {
        baseViewHolder.setText(R.id.tv_name,dropOuting.getName());
        baseViewHolder.setText(R.id.tv_boss,"负责人:"+dropOuting.getPartnerName());
        if (dropOuting.getAuth()==0){
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.weirenzheng);
        }else {//已认证
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.yirenzheng);
        }


        baseViewHolder.setText(R.id.tv_regionCollNo,dropOuting.getRegionCollNo());
        if (type.equals("order")){
            if (dropOuting.getNotOrderDays()!=null){
                if (dropOuting.getNotOrderDays()>0){
                    baseViewHolder.setGone(R.id.tv_exceed,true);
                    baseViewHolder.setText(R.id.tv_exceed,"超"+dropOuting.getNotOrderDays()+"天");
                }else {
                    baseViewHolder.setGone(R.id.tv_exceed,false);
                }

            }else {
                baseViewHolder.setGone(R.id.tv_exceed,false);
            }

        }else {
            if (dropOuting.getNotCallDays()!=null){
                if (dropOuting.getNotCallDays()>0){
                    baseViewHolder.setGone(R.id.tv_exceed,true);
                    baseViewHolder.setText(R.id.tv_exceed,"超"+dropOuting.getNotCallDays()+"天");
                }else {
                    baseViewHolder.setGone(R.id.tv_exceed,false);
                }

            }else {
                baseViewHolder.setGone(R.id.tv_exceed,false);
            }

        }
        baseViewHolder.setText(R.id.tv_num,dropOuting.getTodayAmount()+"");
        baseViewHolder.setText(R.id.tv_num1,dropOuting.getAverageAmount()+"");
        baseViewHolder.setText(R.id.tv_num2,dropOuting.getMonthTimes()+"");
        baseViewHolder.setText(R.id.tv_num3,dropOuting.getNotCallDays()+"");
        baseViewHolder.setText(R.id.tv_username,dropOuting.getBoss());
        baseViewHolder.setText(R.id.tv_address,dropOuting.getAddress());
        baseViewHolder.addOnClickListener(R.id.rl_call);
    }
}
