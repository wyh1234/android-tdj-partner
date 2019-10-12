package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.CommonFollowUpAdapter;
import com.tdjpartner.adapter.SettingPersonAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingPersonActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener{
    @BindView(R.id.rl_xd)
    RelativeLayout rl_xd;
    @BindView(R.id.rl_bf)
    RelativeLayout rl_bf;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_num1)
    TextView tv_num1;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.view)
    View view2;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<DropOuting> dropOutingList=new ArrayList<>();
    private SettingPersonAdapter settingPersonAdapter;
    @OnClick({R.id.rl_xd,R.id.rl_bf,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_xd:
                tv_num.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv_num1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                break;
            case R.id.rl_bf:
                tv_num.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv_num1.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv1.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                view2.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                break;
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
        dropOutingList.add(new DropOuting());
        dropOutingList.add(new DropOuting());
        dropOutingList.add(new DropOuting());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        settingPersonAdapter=new SettingPersonAdapter(R.layout.setting_person_item_layout,dropOutingList);
        settingPersonAdapter.setOnItemChildClickListener(this);
        recyclerView_list.setAdapter(settingPersonAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.setting_person_layout;
    }

    @Override
    public void onRefresh() {
        stop();
    }
    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,SelectPersonActivity.class);
        startActivity(intent);

    }
}
