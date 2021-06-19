package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.TeamOverView;

import java.util.List;
import java.util.Random;

/**
 * Created by LFM on 2021/3/3.
 */
public class DayPreviewAdapter extends BaseQuickAdapter<TeamOverView, BaseViewHolder> {
    private String tiltle;

    public void setTiltle(String tiltle){
        this.tiltle=tiltle;
    }



    public DayPreviewAdapter(int layoutResId, @Nullable List<TeamOverView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamOverView teamPreview) {
        System.out.println("baseViewHolder = " + baseViewHolder + ", teamPreview = " + teamPreview);


        baseViewHolder.addOnClickListener(R.id.tv_today);//增加监听子View

        Random random = new Random();
        baseViewHolder.setText(R.id.callNum, String.valueOf(random.nextInt(100)));
        baseViewHolder.setText(R.id.firstOrderNum, String.valueOf(random.nextInt(100)));
        baseViewHolder.setText(R.id.activeNum, String.valueOf(random.nextInt(100)));
        baseViewHolder.setText(R.id.yesterdayActiveNum, String.valueOf(random.nextInt(100)));
        baseViewHolder.setText(R.id.priceNum, String.valueOf(random.nextInt(100)));
    }
}
