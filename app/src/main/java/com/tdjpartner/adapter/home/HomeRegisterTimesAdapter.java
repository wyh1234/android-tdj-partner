package com.tdjpartner.adapter.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.utils.GeneralUtils;

import android.support.annotation.Nullable;
import java.util.List;

public class HomeRegisterTimesAdapter  extends BaseQuickAdapter<HomeData.RegisterTimesTopBean, BaseViewHolder> {
    public HomeRegisterTimesAdapter(int layoutResId, @Nullable List<HomeData.RegisterTimesTopBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeData.RegisterTimesTopBean rankingData) {
//        LogUtils.e(baseViewHolder.getAdapterPosition());
        if (rankingData!=null){

        baseViewHolder.setText(R.id.tv_name,rankingData.getPartnerName());
        baseViewHolder.setText(R.id.tv_phone,rankingData.getMobile());


        baseViewHolder.setText(R.id.tv_num,rankingData.getDayRegisterTimes()+"家");
        }

    }
}
