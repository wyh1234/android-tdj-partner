package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.MyTeam;

import java.util.List;

public class TeamMemberAdapter extends BaseQuickAdapter<MyTeam.ObjBean, BaseViewHolder> {
    public TeamMemberAdapter(int layoutResId, @Nullable List<MyTeam.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyTeam.ObjBean teamMember) {
        baseViewHolder.setText(R.id.tv_name,teamMember.getNickName());
        baseViewHolder.setText(R.id.tv_grade_name,"岗位:"+teamMember.getGradeName());
        baseViewHolder.setText(R.id.tv_phone,teamMember.getPhone());
    }
}
