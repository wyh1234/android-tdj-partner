package com.tdjpartner.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.adapter.HomeDataDetailsAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.HomeDataDetails;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.StatisticsFragmentPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.ui.activity.statistics.StatisticsListActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;



public class StatisticsFragment extends BaseFrgment<StatisticsFragmentPresenter>  implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    public int pageNo = 1;//翻页计数器
    private BaseQuickAdapter baseQuickAdapter;
    private List<HomeDataDetails.ObjBean.ListBean> data=new ArrayList<>();
    private String title;
    private StatisticsListActivity statisticsListActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        statisticsListActivity=(StatisticsListActivity)context;
        registerEventBus(this);
    }

    public static StatisticsFragment newInstance(int str, String title) {
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
        baseQuickAdapter=new HomeDataDetailsAdapter(R.layout.client_item,data);
        recyclerView_list.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnLoadMoreListener(this,recyclerView_list);
        baseQuickAdapter.setOnItemClickListener(this);


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
    protected StatisticsFragmentPresenter loadPresenter() {
        return new StatisticsFragmentPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.statistics_fragment;
    }


    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",statisticsListActivity.seachTag.getUserId());
     /*   if (title.equals("今日统计")){
            map.put("monthTime","");
            map.put("dayDate",statisticsListActivity.dayDate);
        }else if (title.equals("月统计")){
            map.put("monthTime",statisticsListActivity.monthTime);
            map.put("dayDate","");
        }else {*/
            map.put("monthTime",statisticsListActivity.seachTag.getMonthTime());
            map.put("dayDate",statisticsListActivity.seachTag.getDayDate());
//        }
          map.put("userType",index);
        map.put("pn",pn);
        map.put("ps",10);
        map.put("keyword",GeneralUtils.isNullOrZeroLenght(statisticsListActivity.seachTag.getTag())?"":statisticsListActivity.seachTag.getTag());
        mPresenter.homeDataDetails(map);



    }

    @Subscribe
    public void eventCode(SeachTag seachTag) {
        refreshLayout.setRefreshing(true);
        onRefresh();

    }
    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("onDetach");
        unregisterEventBus(this);
    }
    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }
    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }

    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);

        }
        if (baseQuickAdapter.isLoadMoreEnable()){
            baseQuickAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.e(pageNo);
        refreshLayout.setRefreshing(false);
         getData(++pageNo);
    }

    public void homeDataDetails_Success(HomeDataDetails homeDataDetails) {
        statisticsListActivity.titles.clear();
        statisticsListActivity.titles.add("全部");
        statisticsListActivity.titles.add("未下单"+(homeDataDetails.getObj().getNotOrderCustomerNum()==0?"":homeDataDetails.getObj().getNotOrderCustomerNum()));
        statisticsListActivity.titles.add("已下单"+(homeDataDetails.getObj().getOrderCustomerNum()==0?"":homeDataDetails.getObj().getOrderCustomerNum()));
        statisticsListActivity.adatper.notifyDataSetChanged();

        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }
        stop();


        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(homeDataDetails.getObj().getList())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }
        if (ListUtils.isEmpty(homeDataDetails.getObj().getList())) {
            //已经获取数据
            if (pageNo!=1){
                baseQuickAdapter.loadMoreEnd();
            }
            return;

        }
        data.addAll(homeDataDetails.getObj().getList());
        baseQuickAdapter.setNewData(data);
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置

    }

    public void homeDataDetails_Failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(getContext(), ClientDetailsActivity.class);
        intent.putExtra("customerId",data.get(i).getCustomerId()+"");
        startActivity(intent);
    }
}
