package com.tdjpartner.ui.activity;

import android.content.Intent;
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
import com.tdjpartner.adapter.WithdrawDetalisAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawDetalisActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private List<WithdrawDetalis> withdrawDetalisList=new ArrayList<>();
    private WithdrawDetalisAdapter withdrawDetalisAdapter;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @OnClick({R.id.btn_back,R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_right:
                Intent intent=new Intent(this,WithdrawDetalisFilterActivity.class);
                startActivity(intent);
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
        tv_title.setText("提现明细");
        tv_right.setText("筛选");
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        withdrawDetalisList.add(new WithdrawDetalis());
        withdrawDetalisList.add(new WithdrawDetalis());
        withdrawDetalisList.add(new WithdrawDetalis());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        withdrawDetalisAdapter=new WithdrawDetalisAdapter(R.layout.withdraw_details_item,withdrawDetalisList);
        recyclerView_list.setAdapter(withdrawDetalisAdapter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.withdraw_details_layout;
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
            if (!ListUtils.isEmpty(withdrawDetalisList)) {
                withdrawDetalisList.clear();
            }
        }
        withdrawDetalisList.add(new WithdrawDetalis());
        withdrawDetalisList.add(new WithdrawDetalis());
        withdrawDetalisList.add(new WithdrawDetalis());

        withdrawDetalisAdapter.setNewData(withdrawDetalisList);
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
