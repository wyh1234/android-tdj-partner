package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StatisticsFragment extends BaseFrgment  implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    public int pageNo = 1;//翻页计数器
    private ClientListAdapter clientListAdapter;
    private List<ClientInfo> data=new ArrayList<>();
    private String title;
    private Handler handler=new Handler();
    public static StatisticsFragment newInstance(int str,String title) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        args.putString("title",title);
        StatisticsFragment f = new StatisticsFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        clientListAdapter=new ClientListAdapter(R.layout.client_item,data);
        recyclerView_list.setAdapter(clientListAdapter);
        clientListAdapter.setOnLoadMoreListener(this,recyclerView_list);
    }

    @Override
    protected void loadData() {

    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");
        title=getArguments().getString("title");
        LogUtils.e(index);
        LogUtils.e(title);
        refreshLayout.setRefreshing(true);
        onRefresh();
    }


    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getContentId() {
        return R.layout.statistics_fragment;
    }


    protected  void getData(int pn){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                get_client_success();
            }
        }, 3000);//3秒后执行Runnable中的run方法


    }
    public void  get_client_Failed(){
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
    public void get_client_success(){
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }

        data.add(new ClientInfo());
        data.add(new ClientInfo());
        data.add(new ClientInfo());
        data.add(new ClientInfo());
        clientListAdapter.notifyDataSetChanged();
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
    @Override
    public void onRefresh() {
        clientListAdapter.setEnableLoadMore(false);
        LogUtils.e(index);
        pageNo=1;
        getData(1);
    }

    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
            clientListAdapter.setEnableLoadMore(true);
        }
        if (clientListAdapter.isLoadMoreEnable()){
            clientListAdapter.loadMoreComplete();
        }
        LogUtils.e(clientListAdapter.isLoadMoreEnable());
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setRefreshing(false);
        getData(++pageNo);
    }
}
