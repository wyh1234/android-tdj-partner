package com.tdjpartner.ui.fragment;;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MyFragmentAdapter;
import com.tdjpartner.adapter.home.RankingAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.MyFragmentBottom;
import com.tdjpartner.model.RankingData;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.SettingActivity;
import com.tdjpartner.utils.ViewUrils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFragment extends BaseFrgment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private MyFragmentAdapter  myFragmentAdapter;
    private List<MyFragmentBottom> list =new ArrayList();
    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        list.add(new MyFragmentBottom("新增拜访",false));
        list.add(new MyFragmentBottom("实名认证",true));
        list.add(new MyFragmentBottom("去赚钱",false));
        list.add(new MyFragmentBottom("设置",false));
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myFragmentAdapter = new MyFragmentAdapter(R.layout.my_fragment_item,list);
        myFragmentAdapter.setOnItemClickListener(this);
        rv_recyclerView.setAdapter(myFragmentAdapter);
        View view1 = ViewUrils.getFragmentView(rv_recyclerView, R.layout.myfragment_head);
        myFragmentAdapter.addHeaderView(view1);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getContentId() {
        return R.layout.my_fragment;
    }

    @Override
    public void onRefresh() {
        stop();
    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LogUtils.e(i);
        if (i==3){
            Intent intent=new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        }

    }
}
