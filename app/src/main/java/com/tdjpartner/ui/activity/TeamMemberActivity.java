package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamMemberHorizontalAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.model.NewMyTeam;
import com.tdjpartner.mvp.presenter.TeamMemberPresenter;
import com.tdjpartner.ui.fragment.HomepageFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamMemberActivity extends BaseActivity<TeamMemberPresenter> implements
        SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private TeamMemberAdapter teamMemberAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.recyclerView_horizontal)
    RecyclerView recyclerView_horizontal;

    private List<NewMyTeam> data=new ArrayList<>();
    private List<String> horizontal_data=new ArrayList<>();
    private List<String> horizontal_data_temp=new ArrayList<>();
    private Map<String,List<NewMyTeam>> myTeamMap=new HashMap<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.search_text)
    EditText search_text;
    private TeamMemberHorizontalAdapter teamMemberHorizontalAdapter;


    @OnClick({R.id.btn_back,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
//                if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())){
//                    return;
//                }
                recyclerView_horizontal.setVisibility(View.GONE);
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
        teamMemberAdapter.setOnItemChildClickListener(this);
        LinearLayoutManager layout_horizontal = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);


        horizontal_data.add("战区");
        recyclerView_horizontal.setLayoutManager(layout_horizontal);
        teamMemberHorizontalAdapter=new TeamMemberHorizontalAdapter(R.layout.horizontal_team_item,horizontal_data);
        recyclerView_horizontal.setAdapter(teamMemberHorizontalAdapter);
        teamMemberHorizontalAdapter.setOnItemClickListener(this);
  /*      search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                refreshLayout.setRefreshing(true);
                onRefresh();

            }
        });*/

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
        if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())){
            recyclerView_horizontal.setVisibility(View.VISIBLE);
        }else {
            recyclerView_horizontal.setVisibility(View.GONE);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userId", Integer.parseInt(getIntent().getStringExtra("userId")));
        map.put("websiteId",UserUtils.getInstance().getLoginBean().getSite());
        map.put("keyword", GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())?"":search_text.getText().toString().trim());

        mPresenter.memberList(map);
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }


    public void myTeamPartnerList_Success(List<NewMyTeam> myTeam) {

        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
            if (!ListUtils.isEmpty(horizontal_data)){
                horizontal_data.clear();
                horizontal_data.add("战区");
            }
            if (!ListUtils.isEmpty(horizontal_data_temp)){
                horizontal_data_temp.clear();
            }
            if (myTeamMap.size()>0){
                myTeamMap.clear();
            }
                if (ListUtils.isEmpty(myTeam)) {
                    mStateView.showEmpty();
                }else {
                    mStateView.showContent();//显示内容
                }

            }
        stop();
        data.addAll(myTeam);
        teamMemberAdapter.setNewData(data);
        teamMemberHorizontalAdapter.setNewData(horizontal_data);

    }

    public View getStateViewRoot() {
        return recyclerView_list;
    }

    public void myTeamPartnerList_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            mStateView.showEmpty();//显示重试的布局
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter instanceof TeamMemberAdapter){
            List<NewMyTeam> myTeam= baseQuickAdapter.getData();
            if (myTeam.get(i).getPartnerId()!=0){
                if (myTeam.get(i).getGrade()==3){
                    Intent intent=new Intent(this,HomePageActivity.class);
                    intent.putExtra("userId",myTeam.get(i).getPartnerId()+"");
                    startActivity(intent);
                }else {
                    Intent show=new Intent(this, MenberHomepageActivity.class);
                    show.putExtra("userId",myTeam.get(i).getPartnerId());
                    startActivity(show);
                }
            }

        }else {
            if (i!=horizontal_data.size()-1){
                if (horizontal_data_temp.size()>0){
                    horizontal_data_temp.clear();
                }
                for (int k = 0;k<=i;k++){
                    horizontal_data_temp.add(horizontal_data.get(k));
                }
                horizontal_data.clear();
                horizontal_data.addAll(horizontal_data_temp);
                teamMemberHorizontalAdapter.setNewData(horizontal_data);
                if (i==0){
                    teamMemberAdapter.setNewData(data);

                }else {
                    teamMemberAdapter.setNewData(myTeamMap.get(horizontal_data.get(i)));
                }




            }else {
                teamMemberAdapter.setNewData(data);
            }
        }




    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {


        if (baseQuickAdapter instanceof TeamMemberAdapter){
            List<NewMyTeam> myTeam= baseQuickAdapter.getData();
            if (myTeam.get(i).getChildPartnerTree()!=null){
                teamMemberAdapter.setNewData(myTeam.get(i).getChildPartnerTree());
                horizontal_data.add(myTeam.get(i).getGradeName());
                myTeamMap.put(myTeam.get(i).getGradeName(),myTeam.get(i).getChildPartnerTree());
                teamMemberHorizontalAdapter.setNewData(horizontal_data);
            }

            if(recyclerView_horizontal.getVisibility()==View.GONE){
                if (myTeam.get(i).getGrade()==3){
                    Intent intent=new Intent(this,MenberHomepageActivity.class);
                    intent.putExtra("userId",myTeam.get(i).getPartnerId());
                    LogUtils.e(myTeam.get(i).getPartnerId());
                    startActivity(intent);
                }else {
                    Intent show=new Intent(this, HomePageActivity.class);
                    LogUtils.e(myTeam.get(i).getPartnerId());
                    show.putExtra("userId",myTeam.get(i).getPartnerId()+"");
                    startActivity(show);
                }

            }
        }

    }
}
