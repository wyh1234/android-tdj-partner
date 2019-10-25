package com.tdjpartner.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.TeamMember;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.TeamMemberPresenter;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.TeamMemberPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamMemberActivity extends BaseActivity<TeamMemberPresenter> implements SwipeRefreshLayout.OnRefreshListener,TeamMemberPopuWindow.TeamMemberPopuWindowListener {
    private TeamMemberAdapter teamMemberAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<TeamMember> data=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    private TeamMemberPopuWindow teamMemberPopuWindow;
    private int type=3;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @OnClick({R.id.btn_back,R.id.tv_name})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_name:
                if (teamMemberPopuWindow!=null){
                    if (teamMemberPopuWindow.isShowing()){
                        return;
                    }
                    teamMemberPopuWindow.showPopupWindow(rl_seach);
                }else {
                    teamMemberPopuWindow = new TeamMemberPopuWindow(this);
                    teamMemberPopuWindow.setDismissWhenTouchOutside(false);
                    teamMemberPopuWindow.setInterceptTouchEvent(false);
                    teamMemberPopuWindow.showPopupWindow(rl_seach);
                    teamMemberPopuWindow.setPopupWindowFullScreen(true);//铺满
                    teamMemberPopuWindow.setTeamMemberPopuWindowListener(this);
                }
                break;
        }
    }
    @Override
    protected TeamMemberPresenter loadPresenter() {
        return new TeamMemberPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        teamMemberAdapter=new TeamMemberAdapter(R.layout.team_member_item,data);
        recyclerView_list.setAdapter(teamMemberAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_layout;
    }

    @Override
    public void onRefresh() {


        stop();
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onOk(int type) {
        setType(type);
        teamMemberPopuWindow.dismiss();
    }
}
