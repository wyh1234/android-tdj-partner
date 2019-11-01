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
import com.tdjpartner.model.BankList;
import com.tdjpartner.model.Filterinfo;
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.WithdrawDetalisPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawDetalisActivity extends BaseActivity<WithdrawDetalisPresenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private List<WithdrawDetalis.WithdrawDetalisData> withdrawDetalisList=new ArrayList<>();
    private WithdrawDetalisAdapter withdrawDetalisAdapter;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    private Filterinfo filterinfo;

    public Filterinfo getFilterinfo() {
        return filterinfo;
    }

    public void setFilterinfo(Filterinfo filterinfo) {
        this.filterinfo = filterinfo;
    }

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
    protected WithdrawDetalisPresenter loadPresenter() {
        return new WithdrawDetalisPresenter();
    }

    @Override
    protected void initData() {

    }

    @Subscribe
    public void onEvent(Filterinfo filterinfo) {
        setFilterinfo(filterinfo);
        refreshLayout.autoRefresh();

    }
    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("提现明细");
        tv_right.setText("筛选");
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        withdrawDetalisAdapter=new WithdrawDetalisAdapter(R.layout.withdraw_details_item,withdrawDetalisList,this);
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
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("pn",pn);
        map.put("ps",10);
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());

        map.put("appStatus",getFilterinfo()==null?"":getFilterinfo().getAppStatus());
        map.put("createStartTime",getFilterinfo()==null?"":getFilterinfo().getCreateStartTime());
        map.put("createEndTime",getFilterinfo()==null?"":getFilterinfo().getCreateEndTime());
        mPresenter.findCashWithdrawalFlowList(map);

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

    public void getWithdrawDetailsSuccess(WithdrawDetalis  withdrawDetalis) {

        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(withdrawDetalisList)) {
                withdrawDetalisList.clear();
            }
        }
        stop();
        if (ListUtils.isEmpty(withdrawDetalisList)) {
            if (ListUtils.isEmpty(withdrawDetalis.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(withdrawDetalis.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
            }else {
                GeneralUtils.showToastshort("暂无数据");
            }
            return;
        }
        withdrawDetalisList.addAll(withdrawDetalis.getObj());
        withdrawDetalisAdapter.setNewData(withdrawDetalisList);
    }
    public void getpushMessage_item_failed() {
        stop();
        if (ListUtils.isEmpty(withdrawDetalisList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
}
