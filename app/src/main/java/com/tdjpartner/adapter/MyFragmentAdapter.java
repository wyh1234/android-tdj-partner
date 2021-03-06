package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.MyFragmentBottom;

import java.util.List;

public class MyFragmentAdapter extends BaseQuickAdapter<MyFragmentBottom, BaseViewHolder> {
    private  List<MyFragmentBottom> data;
    public MyFragmentAdapter(int layoutResId, @Nullable List<MyFragmentBottom> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyFragmentBottom myFragmentBottom) {
        baseViewHolder.setText(R.id.tv_tiltle,myFragmentBottom.getTitle());
                baseViewHolder.setGone(R.id.tv_sm,myFragmentBottom.isF());

        if (baseViewHolder.getLayoutPosition()==data.size()){
            baseViewHolder.setBackgroundRes(R.id.fl,R.drawable.home_item_shap);
            baseViewHolder.setVisible(R.id.view,false);

        } else {
            baseViewHolder.setVisible(R.id.view,true);
        }

        if (baseViewHolder.getLayoutPosition()==data.size()){


        }

        if (baseViewHolder.getLayoutPosition()==1){
            baseViewHolder.setBackgroundRes(R.id.fl,R.drawable.hone_item_shap_one);
        }

    }
}
