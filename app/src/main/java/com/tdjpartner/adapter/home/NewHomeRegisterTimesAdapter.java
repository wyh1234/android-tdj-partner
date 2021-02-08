package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.NewHomeData;

import java.util.List;

public class NewHomeRegisterTimesAdapter extends BaseQuickAdapter<NewHomeData.RegisterTimesTopListBean, BaseViewHolder> {
    public NewHomeRegisterTimesAdapter(int layoutResId, @Nullable List<NewHomeData.RegisterTimesTopListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewHomeData.RegisterTimesTopListBean rankingData) {
//        LogUtils.e(baseViewHolder.getAdapterPosition());
        if (rankingData!=null){

        baseViewHolder.setText(R.id.tv_name,rankingData.getPartnerName());
        baseViewHolder.setText(R.id.tv_phone,rankingData.getDayRegisterTimes()+"家");


        baseViewHolder.setText(R.id.tv_num,rankingData.getName());
        }

    }
}
