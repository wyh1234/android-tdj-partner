package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.InvitationHistoryAdapter;
import com.tdjpartner.adapter.PartnerMessageAdapter;
import com.tdjpartner.adapter.PartnerMessageItemAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageItemActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    public int pageNo = 1;//翻页计数器
    private List<PartnerMessageInfo> partnerMessageInfoList=new ArrayList<>();
    private PartnerMessageItemAdapter partnerMessageItemAdapter;

    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
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
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        partnerMessageItemAdapter=new PartnerMessageItemAdapter(R.layout.message_list_item_layout,partnerMessageInfoList);
        recyclerView_list.setAdapter(partnerMessageItemAdapter);
        partnerMessageItemAdapter.setOnItemClickListener(this);
        tv_title.setText("消息");
        refreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_item_list_layout;
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
            if (!ListUtils.isEmpty(partnerMessageInfoList)) {
                partnerMessageInfoList.clear();
            }
        }
        partnerMessageInfoList.add(new PartnerMessageInfo());
        partnerMessageInfoList.add(new PartnerMessageInfo());
        partnerMessageInfoList.add(new PartnerMessageInfo());

        partnerMessageItemAdapter.setNewData(partnerMessageInfoList);
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

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,MessageDetalisActivity.class);
        startActivity(intent);
    }
}
