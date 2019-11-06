package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberPopuAdapter;
import com.tdjpartner.adapter.TeamMembersPopuAdapter;
import com.tdjpartner.model.MyTeam;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class TeamMemberPopuWindow extends BasePopupWindow implements BaseQuickAdapter.OnItemClickListener{
    private View popupView;
    private TeamMemberPopuWindowListener listener;
    private TextView tv_one,tv_two,tv_three;
    private RecyclerView recyclerView;
    private TeamMembersPopuAdapter teamMembersPopuAdapter;

    public void setTeamMemberPopuWindowListener(TeamMemberPopuWindowListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
      listener.onOk((String)baseQuickAdapter.getData().get(i));
    }

    public interface TeamMemberPopuWindowListener {
        void onOk(String str);
    }
    public TeamMemberPopuWindow(Context context, List<String> data) {
        super(context);
        recyclerView=popupView.findViewById(R.id.recyclerview);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        teamMembersPopuAdapter=new TeamMembersPopuAdapter(R.layout.team_preview_item,data);
        recyclerView.setAdapter(teamMembersPopuAdapter);
        teamMembersPopuAdapter.setOnItemClickListener(this);
/*
        tv_one=popupView.findViewById(R.id.tv_one);
        tv_two=popupView.findViewById(R.id.tv_two);
        tv_three=popupView.findViewById(R.id.tv_three);
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(3);
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(2);
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(1);
            }
        });
*/

    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = createPopupById(R.layout.teammenber_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}