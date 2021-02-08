package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.TeamOverView;

import java.util.Calendar;
import java.util.List;

public class TeamPreviewAllAdapter extends BaseQuickAdapter<TeamOverView, BaseViewHolder> {
    private String tiltle;

    public void setTiltle(String tiltle){
        this.tiltle=tiltle;
    }



    public TeamPreviewAllAdapter(int layoutResId, @Nullable List<TeamOverView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamOverView teamPreview) {
        baseViewHolder.addOnClickListener(R.id.rl_cy);
        baseViewHolder.setGone(R.id.ll_bottom,false);
        baseViewHolder.setText(R.id.tv_tiltle,tiltle);
        baseViewHolder.setGone(R.id.tv_today,false);

        baseViewHolder.setText(R.id.tv_amountCommission,teamPreview.getAmountCommission().toString());
        baseViewHolder.setText(R.id.tv_afterSaleAmount,teamPreview.getAfterSaleAmount().toString());
        baseViewHolder.setText(R.id.tv_userNum,teamPreview.getUserNum()+"人");

        baseViewHolder.setText(R.id.tv_activeNum,teamPreview.getOrderNum()+"家");
        baseViewHolder.setText(R.id.tv_addActiveNum,teamPreview.getNoOrderNum()+"家");
        baseViewHolder.setText(R.id.tv3,"下单客户数");
        baseViewHolder.setText(R.id.tv4,"未下单");
        baseViewHolder.setGone(R.id.view_w,false);
        baseViewHolder.setGone(R.id.rl_w,false);
    }
}
