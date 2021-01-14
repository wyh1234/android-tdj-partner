package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.mvp.presenter.TeamMemberPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.TeamMemberPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamMemberActivity extends BaseActivity<TeamMemberPresenter> implements
        SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.OnItemClickListener {
    private TeamMemberAdapter teamMemberAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<MyTeam.ObjBean> data=new ArrayList<>();
    private List<String> stringList=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.search_text)
    EditText search_text;
    private TeamMemberPopuWindow teamMemberPopuWindow;
    private int grade,userId;

    @OnClick({R.id.btn_back,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
                refreshLayout.setRefreshing(true);
                onRefresh();
                break;
        }
    }
    @Override
    protected TeamMemberPresenter loadPresenter() {
        return new TeamMemberPresenter();
    }

    @Override
    protected void initData() {
        refreshLayout.setRefreshing(true);
        onRefresh();

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
        teamMemberAdapter.setOnItemClickListener(this);
        grade=UserUtils.getInstance().getLoginBean().getGrade();
        userId=UserUtils.getInstance().getLoginBean().getEntityId();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_layout;
    }

    @Override
    public void onRefresh() {
        getData();
    }

    public void getData(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", Integer.parseInt(getIntent().getStringExtra("userId")));
        map.put("websiteId",UserUtils.getInstance().getLoginBean().getSite());
        map.put("keyword", GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())?"":search_text.getText().toString().trim());
        map.put("grade",grade);

        mPresenter.memberList(map);
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }


    public void myTeamPartnerList_Success(MyTeam myTeam) {
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }
        stop();
        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(myTeam.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        data.addAll(myTeam.getObj());
        teamMemberAdapter.setNewData(data);
        teamMemberAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置

    }

    public View getStateViewRoot() {
        return recyclerView_list;
    }

    public void myTeamPartnerList_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            mStateView.showEmpty();//显示重试的布局
        }
        teamMemberAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,HomePageActivity.class);
        intent.putExtra("userId",data.get(i).getPartnerId()+"");
        startActivity(intent);

    }

}
