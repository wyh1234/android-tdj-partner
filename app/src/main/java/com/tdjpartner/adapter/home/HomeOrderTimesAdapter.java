package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class HomeOrderTimesAdapter extends BaseQuickAdapter<HomeData.OrdersTimesTopBean, BaseViewHolder> {
    public HomeOrderTimesAdapter(int layoutResId, @Nullable List<HomeData.OrdersTimesTopBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeData.OrdersTimesTopBean rankingData) {
//        LogUtils.e(baseViewHolder.getAdapterPosition());.
        if (rankingData!=null){
            baseViewHolder.setText(R.id.tv_name,rankingData.getPartnerName());
            baseViewHolder.setText(R.id.tv_phone,rankingData.getMobile());
            baseViewHolder.setText(R.id.tv_num,rankingData.getDayOrderTimes()+"家");
        }


    }
}
