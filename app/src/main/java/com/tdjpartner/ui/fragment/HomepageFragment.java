package com.tdjpartner.ui.fragment;;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.adapter.HomepageAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.home.AllDataAdapter;
import com.tdjpartner.adapter.home.AttentionDataAdapter;
import com.tdjpartner.adapter.home.MonthDataAdapter;
import com.tdjpartner.adapter.home.RankingAdapter;
import com.tdjpartner.adapter.home.TodyDataAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.AttentionData;
import com.tdjpartner.model.RankingData;
import com.tdjpartner.model.StatisticalData;
import com.tdjpartner.mvp.presenter.HomepageFragmentPresenter;
import com.tdjpartner.ui.activity.DropOutingActivity;
import com.tdjpartner.ui.activity.TeamPreviewActivity;
import com.tdjpartner.ui.activity.statistics.StatisticsListActivity;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomepageFragment extends BaseFrgment<HomepageFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener
    ,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    @BindView(R.id.rl_team)
    RelativeLayout rl_team;
    private RecyclerView today_recyclerView,month_recyclerView,all_recyclerView,attention_recyclerView,register_recyclerView,order_recyclerView;
    private TextView tv_bottom;
    private List<StatisticalData> todayList = new ArrayList<>();
    private List<StatisticalData> monthList = new ArrayList<>();
    private List<StatisticalData> addList = new ArrayList<>();
    private List<AttentionData> attentionDataList = new ArrayList<>();
    private List<RankingData> rankingDataList = new ArrayList<>();
    private TodyDataAdapter todyDataAdapter;
    private HomepageAdapter homepageAdapter;
    private MonthDataAdapter monthDataAdapter;
    private AllDataAdapter allDataAdapter;
    private  AttentionDataAdapter attentionDataAdapter;
    private RankingAdapter rankingAdapter;
    @OnClick({R.id.rl_team})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_team:
                Intent intent=new Intent(getContext(), TeamPreviewActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {
     /*   rv_recyclerView.setHasFixedSize(true);
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MessageListAdapter adapter = new MessageListAdapter(mMessages);
        rv_recyclerView.setAdapter(adapter);*/
        todayList.add(new StatisticalData());
        todayList.add(new StatisticalData());
        todayList.add(new StatisticalData());
        todayList.add(new StatisticalData());
        todayList.add(new StatisticalData());

        monthList.add(new StatisticalData());
        monthList.add(new StatisticalData());
        monthList.add(new StatisticalData());
        monthList.add(new StatisticalData());
        monthList.add(new StatisticalData());
        monthList.add(new StatisticalData());

        addList.add(new StatisticalData());
        addList.add(new StatisticalData());
        addList.add(new StatisticalData());
        addList.add(new StatisticalData());
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homepageAdapter = new HomepageAdapter(R.layout.homepage_item_layout);
        rv_recyclerView.setAdapter(homepageAdapter);

        View footView = ViewUrils.getFragmentView(rv_recyclerView, R.layout.homepage_foot_layout);
        homepageAdapter.addFooterView(footView);
        today_recyclerView=  footView.findViewById(R.id.today_recyclerView);
        month_recyclerView=  footView.findViewById(R.id.month_recyclerView);
        all_recyclerView=  footView.findViewById(R.id.all_recyclerView);
        attention_recyclerView=  footView.findViewById(R.id.attention_recyclerView);
        register_recyclerView=  footView.findViewById(R.id.register_recyclerView);
        order_recyclerView=  footView.findViewById(R.id.order_recyclerView);
        tv_bottom=  footView.findViewById(R.id.tv_bottom);
        tv_bottom.setText("我是123456778442222222222222222222222222222222222222222222222222");
        tv_bottom.setSelected(true);
        //今日统计；
        ScrollLinearLayoutManager layoutManager=   new ScrollLinearLayoutManager(getActivity(), 5);
        today_recyclerView.setLayoutManager(layoutManager);
        todyDataAdapter = new TodyDataAdapter(R.layout.tody_data_item,todayList);
        todyDataAdapter.setOnItemClickListener(this);
        today_recyclerView.setAdapter(todyDataAdapter);

        //当月统计；
        ScrollLinearLayoutManager layoutManager1=   new ScrollLinearLayoutManager(getActivity(), 3);
        month_recyclerView.setLayoutManager(layoutManager1);
        monthDataAdapter = new MonthDataAdapter(R.layout.month_data_item,monthList);
        monthDataAdapter.setOnItemClickListener(this);
        month_recyclerView.setAdapter(monthDataAdapter);

        //所有统计；
        ScrollLinearLayoutManager layoutManager2=   new ScrollLinearLayoutManager(getActivity(), 4);
        all_recyclerView.setLayoutManager(layoutManager2);
        allDataAdapter = new AllDataAdapter(R.layout.tody_data_item,addList);
        allDataAdapter.setOnItemClickListener(this);
        all_recyclerView.setAdapter(allDataAdapter);
        //关注；
        attentionDataList.add(new AttentionData());
        attentionDataList.add(new AttentionData());
        attentionDataList.add(new AttentionData());
        attentionDataList.add(new AttentionData());
        ScrollLinearLayoutManager layoutManager3=   new ScrollLinearLayoutManager(getActivity(), 4);
        attention_recyclerView.setLayoutManager(layoutManager3);
        attentionDataAdapter = new AttentionDataAdapter(R.layout.attention_data_item,attentionDataList);
        attention_recyclerView.setAdapter(attentionDataAdapter);
        attentionDataAdapter.setOnItemClickListener(this);
        //新注册；
        rankingDataList.add(new RankingData());
        rankingDataList.add(new RankingData());
        rankingDataList.add(new RankingData());
        register_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankingAdapter = new RankingAdapter(R.layout.ranking_item_layout,rankingDataList);
        register_recyclerView.setAdapter(rankingAdapter);
        //新下单；
        rankingDataList.add(new RankingData());
        rankingDataList.add(new RankingData());
        rankingDataList.add(new RankingData());
        order_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankingAdapter = new RankingAdapter(R.layout.ranking_item_layout,rankingDataList);
        order_recyclerView.setAdapter(rankingAdapter);


    }

    @Override
    protected HomepageFragmentPresenter loadPresenter() {
        return new HomepageFragmentPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.homepage_fragment;
    }

    @Override
    public void onRefresh() {
        stop();
    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int pos) {
        if (baseQuickAdapter instanceof TodyDataAdapter){
            if (pos!=4){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("title","今日统计");
                intent.putExtra("pos",""+pos);
                startActivity(intent);
            }
        }else if (baseQuickAdapter instanceof MonthDataAdapter){
            if (pos==0||pos==1||pos==2){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("title","当月统计");
                intent.putExtra("pos",""+pos);
                startActivity(intent);
            }

        }else if (baseQuickAdapter instanceof AllDataAdapter){
            if (pos==0||pos==1||pos==2){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("title","所有统计");
                intent.putExtra("pos",""+pos);
                startActivity(intent);
            }

        }else if (baseQuickAdapter instanceof AttentionDataAdapter){
            if (pos==0){
                Intent intent=new Intent(getContext(), DropOutingActivity.class);
                startActivity(intent);
            }

        }

    }
}
