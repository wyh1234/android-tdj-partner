package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.GoodsAndStore;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsAndStoreFragment extends BaseFrgment implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private MessageListAdapter messageListAdapter;
    private List<Message> goodsAndStoreArrayList=new ArrayList<>();
    public static GoodsAndStoreFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        GoodsAndStoreFragment f = new GoodsAndStoreFragment();
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
          messageListAdapter=new MessageListAdapter(goodsAndStoreArrayList);
            recyclerView_list.setAdapter(messageListAdapter);


    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");
        refreshLayout.autoRefresh();
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
        return R.layout.goodsandstore_fragment;
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

    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(goodsAndStoreArrayList)) {
                goodsAndStoreArrayList.clear();
            }
        }
        if (index==0){
            goodsAndStoreArrayList.add(new GoodsInfo());
            goodsAndStoreArrayList.add(new GoodsInfo());
        }else {
            goodsAndStoreArrayList.add(new StoreInfo());
            goodsAndStoreArrayList.add(new StoreInfo());
            goodsAndStoreArrayList.add(new GoodsInfo());
            goodsAndStoreArrayList.add(new StoreInfo());
        }

        messageListAdapter.setNewData(goodsAndStoreArrayList);
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
}
