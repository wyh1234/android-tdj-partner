package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.TeamMember;

import java.util.List;

public class TeamMemberAdapter extends BaseQuickAdapter<TeamMember, BaseViewHolder> {
    public TeamMemberAdapter(int layoutResId, @Nullable List<TeamMember> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamMember teamMember) {

    }
}
