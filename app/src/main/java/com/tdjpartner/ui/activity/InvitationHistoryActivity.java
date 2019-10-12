package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.InvitationHistoryAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InvitationHistoryActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    public int pageNo = 1;//翻页计数器
    private List<InvitationHistory> invitationHistoryList=new ArrayList<>();
    private InvitationHistoryAdapter invitationHistoryAdapter;
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
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        invitationHistoryAdapter=new InvitationHistoryAdapter(R.layout.invitation_history_item,invitationHistoryList);
        recyclerView_list.setAdapter(invitationHistoryAdapter);
        tv_title.setText("邀请记录");
        refreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.invitation_history_layout;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        getData(1);
    }
    protected  void getData(int pn){
        get_client_success();


    }
    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(invitationHistoryList)) {
                invitationHistoryList.clear();
            }
        }
        invitationHistoryList.add(new InvitationHistory());
        invitationHistoryList.add(new InvitationHistory());
        invitationHistoryList.add(new InvitationHistory());

        invitationHistoryAdapter.setNewData(invitationHistoryList);
//        mStateView.showEmpty();
        stop();
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
}
