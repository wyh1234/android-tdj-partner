package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.MyTeam;

import java.util.List;

public class TeamMembersPopuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TeamMembersPopuAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String teamMember) {
        baseViewHolder.setText(R.id.tv_name,teamMember.split(",")[1]);

    }
}
