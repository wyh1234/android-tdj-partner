package com.tdjpartner.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamPreviewAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.TeamMember;
import com.tdjpartner.model.TeamPreview;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamMemberActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private TeamMemberAdapter teamMemberAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<TeamMember> data=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected IPresenter loadPresenter() {
        return null;
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
        data.add(new TeamMember());
        data.add(new TeamMember());
        data.add(new TeamMember());
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
}
