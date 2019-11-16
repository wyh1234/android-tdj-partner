package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.tdjpartner.mvp.presenter.InvitationHistoryPrensenter;
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

public class InvitationHistoryActivity extends BaseActivity<InvitationHistoryPrensenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    public int pageNo = 1;//翻页计数器
    private List<InvitationHistory.ObjBean> invitationHistoryList=new ArrayList<>();
    private InvitationHistoryAdapter invitationHistoryAdapter;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected InvitationHistoryPrensenter loadPresenter() {
        return new InvitationHistoryPrensenter();
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
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("ps", 30);
        map.put("pn", pn);
        mPresenter.myCustomerList(map);


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


    public void myCustomerListSuccess(InvitationHistory invitationHistory) {
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(invitationHistoryList)) {
                invitationHistoryList.clear();
            }
        }
        stop();
        if (ListUtils.isEmpty(invitationHistoryList)) {
            if (ListUtils.isEmpty(invitationHistory.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(invitationHistory.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
                return;
            }else {
                GeneralUtils.showToastshort("暂无数据");
                return;
            }
        }

        invitationHistoryList.addAll(invitationHistory.getObj());
        invitationHistoryAdapter.setNewData(invitationHistoryList);

    }
    public void myCustomerListfailed() {
        stop();
        if (ListUtils.isEmpty(invitationHistoryList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
}
