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
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.GoodsAndStore;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.mvp.presenter.GoodsAndStorePresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.GoodsAndStoreActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class GoodsAndStoreFragment extends BaseFrgment<GoodsAndStorePresenter> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    public int pageNo = 1;//翻页计数器
    private int index=0;
    private MessageListAdapter messageListAdapter;
    private List<Message> goodsAndStoreArrayList=new ArrayList<>();
    private GoodsAndStoreActivity goodsAndStoreActivity;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        goodsAndStoreActivity=(GoodsAndStoreActivity)context;
    }

    @Override
    protected GoodsAndStorePresenter loadPresenter() {
        return new GoodsAndStorePresenter();
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
        getData(pageNo);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("pn",pn);
        map.put("ps",10);
        map.put("site", UserUtils.getInstance().getLoginBean().getSite());
//        map.put("personId", goodsAndStoreActivity.customerId);
        map.put("personId", 45);

        if (index==0){
            map.put("type", 2);
        }else {
            map.put("type", 1);
        }
        map.put("userType", 0);
        if (index==0){
            mPresenter.collect_products(map);
        }else {
            mPresenter.collect_stores(map);
        }


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

    public void collect_products_Success(StoreInfo storeInfo,GoodsInfo goodsInfo) {

        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(goodsAndStoreArrayList)) {
                goodsAndStoreArrayList.clear();
            }
        }
        stop();
        if (index==0){
            if (ListUtils.isEmpty(goodsAndStoreArrayList)) {
                if (ListUtils.isEmpty(goodsInfo.getObj())) {
                    //获取不到数据,显示空布局
                    mStateView.showEmpty();
                    return;
                }
                mStateView.showContent();//显示内容
            }

            if (ListUtils.isEmpty(goodsInfo.getObj())) {
                //已经获取数据
                if (pageNo!=1){
                    GeneralUtils.showToastshort("数据加载完毕");
                }else {
                    GeneralUtils.showToastshort("暂无数据");
                }
                return;
            }
            goodsAndStoreArrayList.addAll(goodsInfo.getObj());

        }else {
            if (ListUtils.isEmpty(goodsAndStoreArrayList)) {
                if (ListUtils.isEmpty(storeInfo.getObj())) {
                    //获取不到数据,显示空布局
                    mStateView.showEmpty();
                    return;
                }
                mStateView.showContent();//显示内容
            }

            if (ListUtils.isEmpty(storeInfo.getObj())) {
                //已经获取数据
                if (pageNo!=1){
                    GeneralUtils.showToastshort("数据加载完毕");
                }else {
                    GeneralUtils.showToastshort("暂无数据");
                }
                return;
            }
            goodsAndStoreArrayList.addAll(storeInfo.getObj());
        }

        messageListAdapter.setNewData(goodsAndStoreArrayList);

    }


    public void collect_products_Failed() {
        stop();
        if (ListUtils.isEmpty(goodsAndStoreArrayList)) {
            mStateView.showEmpty();//显示重试的布局
        }
    }

}
