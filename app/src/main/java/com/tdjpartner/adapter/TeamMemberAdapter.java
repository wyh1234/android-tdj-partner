package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.model.NewMyTeam;

import java.util.List;

public class TeamMemberAdapter extends BaseQuickAdapter<NewMyTeam, BaseViewHolder> {
    public TeamMemberAdapter(int layoutResId, @Nullable List<NewMyTeam> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewMyTeam teamMember) {
        baseViewHolder.addOnClickListener(R.id.iv_right);

        if (teamMember.getGrade()!=0){
            baseViewHolder.setVisible(R.id.tv_grade_name,true);
            baseViewHolder.setVisible(R.id.tv_phone,true);
            baseViewHolder.setText(R.id.tv_name,teamMember.getGradeName());
            baseViewHolder.setBackgroundRes(R.id.tv_name,R.mipmap.name_image);

            if (teamMember.getChildPartnerTree()!=null){
                baseViewHolder.setText(R.id.tv_grade_name,teamMember.getNickName()+"("+teamMember.getChildPartnerTree().size()+")");
                baseViewHolder.setVisible(R.id.iv_right,true);
                baseViewHolder.setGone(R.id.iv_grade_name,false);
            }else {
                if (teamMember.getGrade()==3){
                    baseViewHolder.setGone(R.id.tv_name,false);
                    baseViewHolder.setVisible(R.id.iv_right,false);
                    baseViewHolder.setGone(R.id.iv_grade_name,true);
                    baseViewHolder.setBackgroundRes(R.id.iv_grade_name,R.mipmap.zhuanyuan);

                }else {
                    baseViewHolder.setGone(R.id.iv_grade_name,false);
                    baseViewHolder.setGone(R.id.tv_name,true);
                    baseViewHolder.setVisible(R.id.iv_right,false);
                }

                baseViewHolder.setText(R.id.tv_grade_name,teamMember.getNickName());
            }
            baseViewHolder.setText(R.id.tv_phone,teamMember.getPhone());
        }else {
            baseViewHolder.setGone(R.id.iv_grade_name,false);
            if (teamMember.getChildPartnerTree()!=null){
                baseViewHolder.setText(R.id.tv_name,teamMember.getWebStieName()+"("+teamMember.getChildPartnerTree().size()+")");
                baseViewHolder.setBackgroundRes(R.id.tv_name,R.drawable.bg_white_radius_6);
                baseViewHolder.setGone(R.id.tv_name,true);
                baseViewHolder.setGone(R.id.iv_right,true);
            }else {
                baseViewHolder.setText(R.id.tv_name,teamMember.getWebStieName());
                baseViewHolder.setGone(R.id.tv_name,true);
                baseViewHolder.setBackgroundRes(R.id.tv_name,R.drawable.bg_white_radius_6);
                baseViewHolder.setGone(R.id.iv_right,false);

            }

            baseViewHolder.setGone(R.id.tv_grade_name,false);
            baseViewHolder.setGone(R.id.tv_phone,false);
        }

    }
}
