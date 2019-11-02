package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.model.SettingPerson;

import java.util.List;

public class SettingPersonAdapter extends BaseQuickAdapter<SettingPerson.ObjBean.ListBean, BaseViewHolder> {
    public SettingPersonAdapter(int layoutResId, @Nullable List<SettingPerson.ObjBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SettingPerson.ObjBean.ListBean dropOuting) {
//        baseViewHolder.addOnClickListener(R.id.tv_gj_status);

        baseViewHolder.setText(R.id.tv_name,dropOuting.getName());
        baseViewHolder.setText(R.id.tv_boss,"负责人:"+dropOuting.getPartnerName());
        if (dropOuting.getAuth()==0){
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.weirenzheng);
        }else {//已认证
            baseViewHolder.setImageResource(R.id.iv_stastu,R.mipmap.yirenzheng);
        }

        baseViewHolder.setText(R.id.tv_regionCollNo,dropOuting.getRegionCollNo());
        baseViewHolder.setText(R.id.tv_num,dropOuting.getTodayAmount()+"");
        baseViewHolder.setText(R.id.tv_num1,dropOuting.getAverageAmount()+"");
        baseViewHolder.setText(R.id.tv_num2,dropOuting.getMonthTimes()+"");
        baseViewHolder.setText(R.id.tv_num3,dropOuting.getNotCallDays()+"");
        baseViewHolder.setText(R.id.tv_username,dropOuting.getBoss());
        baseViewHolder.setText(R.id.tv_address,dropOuting.getAddress());

    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
