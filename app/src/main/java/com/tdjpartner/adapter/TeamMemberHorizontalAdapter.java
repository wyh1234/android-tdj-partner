package com.tdjpartner.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;

import java.util.List;

public class TeamMemberHorizontalAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TeamMemberHorizontalAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        if (baseViewHolder.getAdapterPosition()==0){
            baseViewHolder.setGone(R.id.image,true);
            baseViewHolder.setBackgroundRes(R.id.image,R.mipmap.city_one);
            baseViewHolder.setText(R.id.tv_city,s);

        }else {
            baseViewHolder.setGone(R.id.image,false);
            baseViewHolder.setText(R.id.tv_city,">"+s);
            baseViewHolder.setTextColor(R.id.tv_city, Color.parseColor("#fb7646"));
        }


    }
}
