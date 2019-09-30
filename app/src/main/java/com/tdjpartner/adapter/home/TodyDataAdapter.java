package com.tdjpartner.adapter.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.StatisticalData;

import java.util.List;

public class TodyDataAdapter extends BaseQuickAdapter<StatisticalData,BaseViewHolder> {
    private List<StatisticalData> data;

    public TodyDataAdapter(int layoutResId, @Nullable List<StatisticalData> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StatisticalData statisticalData) {
        LogUtils.e(baseViewHolder.getLayoutPosition());
        if (baseViewHolder.getLayoutPosition()==4){
            baseViewHolder.setVisible(R.id.view,false);
        }else {
            baseViewHolder.setVisible(R.id.view,true);
        }
    }
}
