package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ProblemType;

import java.util.List;

public class ProblemTypeAdapter extends BaseQuickAdapter<ProblemType, BaseViewHolder> {
    public ProblemTypeAdapter(int layoutResId, @Nullable List<ProblemType> data) {
        super(layoutResId, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProblemType problemType) {
        baseViewHolder.setText(R.id.tv_name,problemType.getTypeName());
        baseViewHolder.getView(R.id.tv_name).setSelected(problemType.isFlag());
    }
}
