package com.tdjpartner.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListSeachAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.ClientSeachInfo;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.ClientListSeachPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ClientListSeachFragment extends BaseFrgment<ClientListSeachPresenter> implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    public int pageNo = 1;//翻页计数器
    private ClientListSeachAdapter clientListSeachAdapter;
    private List<ClientSeachInfo.ObjBean> data=new ArrayList<>();
    private SeachTag seachTag;


    public void setSeachTag(SeachTag seachTag) {
        this.seachTag = seachTag;
    }

    public static ClientListSeachFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        ClientListSeachFragment f = new ClientListSeachFragment();
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
        clientListSeachAdapter=new ClientListSeachAdapter(R.layout.client_list_seach_item,data);
        recyclerView_list.setAdapter(clientListSeachAdapter);
        clientListSeachAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("onAttach");
        registerEventBus(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("onDetach");
        unregisterEventBus(this);
    }

    @Subscribe
    public void eventCode(SeachTag seachTag) {
        setSeachTag(seachTag);
        refreshLayout.autoRefresh();

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        index=getArguments().getInt("intent");

        if (seachTag!=null){
            refreshLayout.autoRefresh();
        }else {
            mStateView.showEmpty();//显示重试的布局
        }
    }

    @Override
    protected ClientListSeachPresenter loadPresenter() {
        return new ClientListSeachPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.client_list_seach_fragment;
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
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pageNo);
    }

    private void getData(int pn) {
        if (seachTag!=null){
            Map<String,Object> map=new HashMap<>();
            map.put("userId",21);
            map.put("userType",index+1);
            map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
            map.put("pn", pn);
            map.put("ps", 10);
            map.put("keyword",seachTag.getTag());
            mPresenter.customer_hotelMap(map);

        }else {
            mStateView.showEmpty();//显示重试的布局
            GeneralUtils.showToastshort("请输入门店名称或者手机号");
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        LogUtils.e(index);
        pageNo=1;
        getData(pageNo);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    public void hotelMap_Success(ClientSeachInfo clientInfoList) {
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }
        stop();


        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(clientInfoList.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }
        if (ListUtils.isEmpty(clientInfoList.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                clientListSeachAdapter.loadMoreEnd();
            }
            return;
        }
        data.addAll(clientInfoList.getObj());
        clientListSeachAdapter.setNewData(data);
        clientListSeachAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置

    }

    public void hotelMap_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }
}
