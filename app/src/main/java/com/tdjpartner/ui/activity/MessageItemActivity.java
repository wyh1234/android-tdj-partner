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
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.MessageItemPresenter;
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

public class MessageItemActivity extends BaseActivity<MessageItemPresenter> implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    public int pageNo = 1;//翻页计数器
    private List<PartnerMessageItemInfo.ObjBean> objBeansList=new ArrayList<>();
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
    protected MessageItemPresenter loadPresenter() {
        return new MessageItemPresenter();
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
        partnerMessageItemAdapter=new PartnerMessageItemAdapter(R.layout.message_list_item_layout,objBeansList);
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
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("pn",pn);
        map.put("ps",10);
        map.put("type",Integer.parseInt(getIntent().getStringExtra("type")));
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.pushMessage_item(map);
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
        intent.putExtra("imageurl",objBeansList.get(i).getDetailImgUrl());
        intent.putExtra("Content",objBeansList.get(i).getContent());
        startActivity(intent);
    }

    public void getpushMessage_item(PartnerMessageItemInfo partnerMessageItemInfo) {
        stop();
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(objBeansList)) {
                objBeansList.clear();
            }
        }

           if (ListUtils.isEmpty(objBeansList)) {
            if (ListUtils.isEmpty(partnerMessageItemInfo.getObj())) {
                //获取不到数据,显示空布局
               mStateView.showEmpty();
                return;
            }
                mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(partnerMessageItemInfo.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
                return;
            }else {
                GeneralUtils.showToastshort("暂无数据");
                return;
            }
        }

        objBeansList.addAll(partnerMessageItemInfo.getObj());
        partnerMessageItemAdapter.setNewData(objBeansList);

    }

    public void getpushMessage_item_failed() {
        stop();
        if (ListUtils.isEmpty(objBeansList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
}
