package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.tdjpartner.R;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.utils.GeneralUtils;

import java.util.Calendar;
import java.util.List;

public class TeamPreviewAdapter extends BaseQuickAdapter<TeamOverView, BaseViewHolder> {
    private String tiltle;

    public void setTiltle(String tiltle){
        this.tiltle=tiltle;
    }



    public TeamPreviewAdapter(int layoutResId, @Nullable List<TeamOverView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamOverView teamPreview) {
        baseViewHolder.addOnClickListener(R.id.rl_cy);
        baseViewHolder.addOnClickListener(R.id.rl_right);
        baseViewHolder.setText(R.id.tv_tiltle,tiltle);

//        baseViewHolder.setText(R.id.tv_amountCommission,teamPreview.getAmountCommission().toString());
        baseViewHolder.setText(R.id.tv_amountCommission,String.format("%.2f", Double.valueOf(teamPreview.getAmountCommission().toString())));

        baseViewHolder.setText(R.id.tv_afterSaleAmount,teamPreview.getAfterSaleAmount().toString());
        baseViewHolder.setText(R.id.tv_userNum,teamPreview.getUserNum()+"人");
        baseViewHolder.setText(R.id.tv_activeNum,teamPreview.getActiveNum()+"家");
        baseViewHolder.setText(R.id.tv_addActiveNum,teamPreview.getAddActiveNum()+"家");
        baseViewHolder.setText(R.id.tv_noOrderNum,teamPreview.getNoOrderNum()+"家");
        baseViewHolder.setText(R.id.tv_amount,teamPreview.getAmount().toString());
        baseViewHolder.setText(R.id.tv_addAmount,teamPreview.getAddAmount().toString());
        baseViewHolder.setText(R.id.tv_callNum,teamPreview.getCallNum()+"次");
        if (!GeneralUtils.isNullOrZeroLenght(teamPreview.getDate())){
            baseViewHolder.setText(R.id.tv_today,teamPreview.getDate());

        }else {
            baseViewHolder.setText(R.id.tv_today,(Calendar.getInstance().get(Calendar.MONTH)+1)+"月"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))+"日");

        }

    }
}
