package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClientListFragment extends BaseFrgment  implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    public int pageNo = 1;//翻页计数器
    private ClientListAdapter clientListAdapter;
    private List<ClientInfo> data=new ArrayList<>();
    public static ClientListFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        ClientListFragment f = new ClientListFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    protected void initView(View view) {

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        clientListAdapter=new ClientListAdapter(R.layout.client_item,data);
        recyclerView_list.setAdapter(clientListAdapter);
        clientListAdapter.setOnItemClickListener(this);


    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");

        refreshLayout.autoRefresh();
    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }

    @Override
    protected int getContentId() {
        return R.layout.client_list_fragment;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        LogUtils.e(index);
        pageNo=1;
        getData(1);
    }
    protected  void getData(int pn){
                get_client_success();


    }
    public void  get_client_Failed(){
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }

        data.add(new ClientInfo());
        data.add(new ClientInfo());
        data.add(new ClientInfo());
        data.add(new ClientInfo());
        clientListAdapter.notifyDataSetChanged();
//        mStateView.showEmpty();
        stop();
  /*      if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(body.getData().getObj())) {
                //获取不到数据,显示空布局
               mStateView.showEmpty();
                return;
            }
                mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(body.getData().getObj())) {
            //已经获取数据
            if (pn!=1){
                GeneralUtils.showToastshort("数据加载完毕");
                return;
            }else {
                GeneralUtils.showToastshort("暂无数据");
                return;
            }

        }

        data.addAll(body.getData().getObj());
        clientListAdapter.notifyDataSetChanged();*/
    }

    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        LogUtils.i(refreshLayout.isRefreshing());
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(getContext(), ClientDetailsActivity.class);
        startActivity(intent);
    }
}
