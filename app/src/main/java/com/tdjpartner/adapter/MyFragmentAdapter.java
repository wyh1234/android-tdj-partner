package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.MyFragmentBottom;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.List;

public class MyFragmentAdapter extends BaseQuickAdapter<MyFragmentBottom, BaseViewHolder> {
    private  List<MyFragmentBottom> data;
    public MyFragmentAdapter(int layoutResId, @Nullable List<MyFragmentBottom> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyFragmentBottom myFragmentBottom) {
        LogUtils.e(baseViewHolder.getAdapterPosition());
        baseViewHolder.setText(R.id.tv_tiltle,myFragmentBottom.getTitle());
        if (UserUtils.getInstance().getLoginBean()!=null){
            if (GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                baseViewHolder.setGone(R.id.tv_sm,false);
            }else {
                baseViewHolder.setGone(R.id.tv_sm,true);
            }

        }

        if (baseViewHolder.getLayoutPosition()==data.size()){
            baseViewHolder.setBackgroundRes(R.id.rl,R.drawable.home_item_shap);
            baseViewHolder.setVisible(R.id.view,false);

        } else {
            baseViewHolder.setVisible(R.id.view,true);
        }

        if (baseViewHolder.getLayoutPosition()==data.size()){


        }

        if (baseViewHolder.getLayoutPosition()==1){
            baseViewHolder.setBackgroundRes(R.id.rl,R.drawable.hone_item_shap_one);
        }

    }
}
