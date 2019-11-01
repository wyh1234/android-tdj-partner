package com.tdjpartner.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.StatisticalData;

import java.util.List;

public class AllDataAdapter extends BaseQuickAdapter<StatisticalData, BaseViewHolder> {
    private List<StatisticalData> data;

    public AllDataAdapter(int layoutResId, @Nullable List<StatisticalData> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StatisticalData statisticalData) {
        if (baseViewHolder.getLayoutPosition()==3){
            baseViewHolder.setVisible(R.id.view,false);
        }else {
            baseViewHolder.setVisible(R.id.view,true);
        }
        baseViewHolder.setText(R.id.tv_tiltle,statisticalData.getTitle());
        baseViewHolder.setText(R.id.tv_num,statisticalData.getNum()+"");
    }
}
