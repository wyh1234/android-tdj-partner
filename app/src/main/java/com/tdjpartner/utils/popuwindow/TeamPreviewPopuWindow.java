package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamMemberPopuAdapter;
import com.tdjpartner.model.MyTeam;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class TeamPreviewPopuWindow extends BasePopupWindow implements BaseQuickAdapter.OnItemClickListener {
    private View popupView;
    private TeamPreviewPopuWindowListener listener;
    private RecyclerView recyclerView;
    private TeamMemberPopuAdapter teamMemberPopuAdapter;
    public void setTeamPreviewPopuWindowListener(TeamPreviewPopuWindowListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        listener.onOk(((MyTeam.ObjBean)baseQuickAdapter.getData().get(i)).getPartnerId(),((MyTeam.ObjBean)baseQuickAdapter.getData().get(i)).getGradeName()+"-"+((MyTeam.ObjBean)baseQuickAdapter.getData().get(i)).getNickName());

    }

    public interface TeamPreviewPopuWindowListener {
        void onOk(int type,String str);
    }
    public TeamPreviewPopuWindow(Context context,List<MyTeam.ObjBean> data) {
        super(context);
        recyclerView=popupView.findViewById(R.id.recyclerview);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        teamMemberPopuAdapter=new TeamMemberPopuAdapter(R.layout.team_preview_item,data);
        recyclerView.setAdapter(teamMemberPopuAdapter);
        teamMemberPopuAdapter.setOnItemClickListener(this);

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
        popupView = createPopupById(R.layout.teampreview_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}