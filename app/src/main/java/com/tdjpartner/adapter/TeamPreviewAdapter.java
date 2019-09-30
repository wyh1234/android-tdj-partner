package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.TeamPreview;

import java.util.List;

public class TeamPreviewAdapter extends BaseQuickAdapter<TeamPreview, BaseViewHolder> {
    public TeamPreviewAdapter(int layoutResId, @Nullable List<TeamPreview> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamPreview teamPreview) {
        baseViewHolder.addOnClickListener(R.id.rl_cy);

    }
}
