package com.tdjpartner.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.BaiFangHistoryAdapter;
import com.tdjpartner.base.Fragment;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.mvp.presenter.BaiFangHistoryPresenter;
import com.tdjpartner.ui.activity.BaiFangHistoryActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class BaiFangHistoryFragment extends Fragment<BaiFangHistoryPresenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private List<BaiFangHistory.ObjBean> baiFangHistoryList=new ArrayList<>();
    private BaiFangHistoryAdapter baiFangHistoryAdapter;
    private BaiFangHistoryActivity baiFangHistoryActivity;
    public static BaiFangHistoryFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        BaiFangHistoryFragment f = new BaiFangHistoryFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baiFangHistoryActivity=(BaiFangHistoryActivity)context;
    }

    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        baiFangHistoryAdapter=new BaiFangHistoryAdapter(R.layout.baifang_history_item,baiFangHistoryList);
        recyclerView_list.setAdapter(baiFangHistoryAdapter);
    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");
        LogUtils.i(index);
        refreshLayout.autoRefresh();
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected BaiFangHistoryPresenter loadPresenter() {
        return new BaiFangHistoryPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.baifang_history_fragment_layout;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("buyId",baiFangHistoryActivity.buyId);
        if (index==0){
            map.put("type","today");
        }else if (index==1){
            map.put("type","yesterday");
        }else if (index==2){
            map.put("type","seven");
        }else if (index==3){
            map.put("type","month");
        }else if (index==4){
            map.put("type","year");
        }

        map.put("pn",pn);
        map.put("ps",10);
        
        mPresenter.call_list(map);

    }

    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
            if (!ListUtils.isEmpty(baiFangHistoryList)) {
                baiFangHistoryList.clear();
                baiFangHistoryAdapter.notifyDataSetChanged();
            }

        }
        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }


    public void call_list_Success(BaiFangHistory baiFangHistory) {
        stop();
        if (ListUtils.isEmpty(baiFangHistoryList)) {
            if (ListUtils.isEmpty(baiFangHistory.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(baiFangHistory.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
            }else {
                GeneralUtils.showToastshort("暂无数据");
            }
            return;
        }
        baiFangHistoryList.addAll(baiFangHistory.getObj());
        baiFangHistoryAdapter.setNewData(baiFangHistoryList);

    }

    public void call_list_Failed() {
        stop();
        if (ListUtils.isEmpty(baiFangHistoryList)) {
            mStateView.showEmpty();//显示重试的布局
        }
    }
}
