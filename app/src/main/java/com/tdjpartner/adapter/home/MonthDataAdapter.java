package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.StatisticalData;

import java.util.List;

public class MonthDataAdapter extends BaseQuickAdapter<StatisticalData, BaseViewHolder> {
    private List<StatisticalData> data;
    public MonthDataAdapter(int layoutResId, @Nullable List<StatisticalData> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StatisticalData statisticalData) {
        if (baseViewHolder.getLayoutPosition()==2||baseViewHolder.getLayoutPosition()==5){
            baseViewHolder.setVisible(R.id.view1,false);
        }else {
            baseViewHolder.setVisible(R.id.view1,true);
        }
        if (baseViewHolder.getLayoutPosition()<=3){
            baseViewHolder.setVisible(R.id.view,true);
        }else {
            baseViewHolder.setVisible(R.id.view,false);
        }
        baseViewHolder.setText(R.id.tv_tiltle,statisticalData.getTitle());
    }
}
