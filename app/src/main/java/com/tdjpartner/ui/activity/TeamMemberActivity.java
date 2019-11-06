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
import com.tdjpartner.utils.popuwindow.TeamMemberPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamMemberActivity extends BaseActivity<TeamMemberPresenter> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener,TeamMemberPopuWindow.TeamMemberPopuWindowListener {
    private TeamMemberAdapter teamMemberAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<MyTeam.ObjBean> data=new ArrayList<>();
    private List<String> stringList=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.search_text)
    EditText search_text;
    private TeamMemberPopuWindow teamMemberPopuWindow;
    private int type=3;
    public int pageNo = 1;//翻页计数器
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @OnClick({R.id.btn_back,R.id.tv_name,R.id.tv_list_type})
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
                    teamMemberPopuWindow = new TeamMemberPopuWindow(this,stringList);
                    teamMemberPopuWindow.setDismissWhenTouchOutside(false);
                    teamMemberPopuWindow.setInterceptTouchEvent(false);
                    teamMemberPopuWindow.showPopupWindow(rl_seach);
                    teamMemberPopuWindow.setPopupWindowFullScreen(true);//铺满
                    teamMemberPopuWindow.setTeamMemberPopuWindowListener(this);
                }
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
        Map<String,Object> map=new HashMap<>();
        map.put("userId", Integer.parseInt(getIntent().getStringExtra("userId")));
        mPresenter.myTeamPartnerSelectList(map);
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
        teamMemberAdapter.setOnLoadMoreListener(this,recyclerView_list);
        teamMemberAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_layout;
    }

    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }

    public void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", Integer.parseInt(getIntent().getStringExtra("userId")));
        map.put("pn", pn);
        map.put("ps", 10);
        map.put("timeType",getIntent().getStringExtra("timeType"));
        map.put("startTime",GeneralUtils.isNullOrZeroLenght(getIntent().getStringExtra("startTime"))?"":getIntent().getStringExtra("startTime"));
        map.put("nickName", GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())?"":search_text.getText().toString().trim());
        map.put("grade",getType());

        mPresenter.myTeamPartnerList(map);//今月；
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (teamMemberAdapter.isLoadMoreEnable()){
            teamMemberAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onOk(String str) {
        setType(Integer.parseInt(str.split(",")[0]));
        tv_name.setText(str.split(",")[1]);
        teamMemberPopuWindow.dismiss();
        refreshLayout.setRefreshing(true);
        onRefresh();
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

        if (ListUtils.isEmpty(myTeam.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                teamMemberAdapter.loadMoreEnd();
            }
            return;
        }
        data.addAll(myTeam.getObj());
        teamMemberAdapter.setNewData(data);
        teamMemberAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置

    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setRefreshing(false);
        getData(++pageNo);
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

    public void myTeamPartnerSelectList_Success(List<String> stringLists) {
        if (!ListUtils.isEmpty(stringLists)){
            stringList.addAll(stringLists);
        }


    }
}
