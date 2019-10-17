package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.BaiFangHistoryAdapter;
import com.tdjpartner.adapter.EarningsHistoryAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.mvp.presenter.EarningsHistoryPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class EarningsHistoryFragment extends BaseFrgment<EarningsHistoryPresenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 0;//翻页计数器
    private int index=0;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_money_f)
    TextView tv_money_f;
    @BindView(R.id.tv_orderMoney)
    TextView tv_orderMoney;
    private List<EarningsHistory.ObjBean.ListBean> earningsHistoryList=new ArrayList<>();
    private EarningsHistoryAdapter earningsHistoryAdapter;
    public static EarningsHistoryFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        EarningsHistoryFragment f = new EarningsHistoryFragment();
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
        earningsHistoryAdapter=new EarningsHistoryAdapter(R.layout.earnings_history_item,earningsHistoryList,getContext());
        recyclerView_list.setAdapter(earningsHistoryAdapter);
    }

    @Override
    protected void loadData() {

    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");
        LogUtils.i(index);
        refreshLayout.autoRefresh();
    }
    @Override
    protected EarningsHistoryPresenter loadPresenter() {
        return new EarningsHistoryPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.earnings_history_fragment_layout;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=0;
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("limit", 10);
        if (index==0){
            map.put("timeType","today");
        }else if (index==1){
            map.put("timeType","seven");
        }else if (index==2){
            map.put("timeType","month");
        }else if (index==3){
            map.put("timeType","tmonth");
        }else if (index==4){
            map.put("timeType", "");
        }

        map.put("offset", pn);
        mPresenter.earning_info(map);

    }

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

    public void earning_info_Success(EarningsHistory earningsHistory) {
        stop();
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(earningsHistoryList)) {
                earningsHistoryList.clear();
            }
        }

        if (ListUtils.isEmpty(earningsHistoryList)) {
            if (ListUtils.isEmpty(earningsHistory.getObj().getList())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(earningsHistory.getObj().getList())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
                return;
            }else {
                GeneralUtils.showToastshort("暂无数据");
                return;
            }
        }
        tv_money.setText(earningsHistory.getObj().getMoney()+"");
        tv_money_f.setText("-"+earningsHistory.getObj().getSubMoney()+"");
        tv_orderMoney.setText("+"+earningsHistory.getObj().getOrderMoney()+"");
        earningsHistoryList.addAll(earningsHistory.getObj().getList());
        earningsHistoryAdapter.setNewData(earningsHistoryList);
    }

    public void earning_info_failed() {
        stop();
        if (ListUtils.isEmpty(earningsHistoryList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }

}
