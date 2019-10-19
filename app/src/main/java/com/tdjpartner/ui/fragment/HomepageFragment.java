package com.tdjpartner.ui.fragment;;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
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
import com.tdjpartner.ui.activity.CommonFollowUpActivity;
import com.tdjpartner.ui.activity.DropOutingActivity;
import com.tdjpartner.ui.activity.PartnerCheckActivity;
import com.tdjpartner.ui.activity.SettingPersonActivity;
import com.tdjpartner.ui.activity.TeamPreviewActivity;
import com.tdjpartner.ui.activity.statistics.StatisticsListActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomepageFragment extends BaseFrgment<HomepageFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener
    ,BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    @BindView(R.id.rl_team)
    RelativeLayout rl_team;
    private RecyclerView today_recyclerView, month_recyclerView, all_recyclerView, attention_recyclerView, register_recyclerView, order_recyclerView;
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
    private AttentionDataAdapter attentionDataAdapter;
    private RankingAdapter rankingAdapter;
    private RelativeLayout rl_right, rl_rights;
    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    private TextView tv_today, tv_month;
    private boolean f;

    @OnClick({R.id.rl_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_team:
                Intent intent = new Intent(getContext(), TeamPreviewActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_right:
                f=true;
                pvTime.show();
                break;
            case R.id.rl_rights:
                f=false;
                pvTime.show();
                break;
        }
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        selectedDate=Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(Calendar.getInstance().get(Calendar.YEAR), (Calendar.getInstance().get(Calendar.MONTH)-1),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(Calendar.getInstance().get(Calendar.YEAR),  (Calendar.getInstance().get(Calendar.MONTH)),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (f){
                    tv_today.setText(GeneralUtils.getTimes(date));

                }else {
                    tv_month.setText(GeneralUtils.getTime(date));
                }
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }


    @Override
    protected void loadData() {
     /*   rv_recyclerView.setHasFixedSize(true);
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MessageListAdapter adapter = new MessageListAdapter(mMessages);
        rv_recyclerView.setAdapter(adapter);*/


        todayList.add(new StatisticalData("注册数"));
        todayList.add(new StatisticalData("新下单数"));
        todayList.add(new StatisticalData("日活数"));
        todayList.add(new StatisticalData("下单金额"));
        todayList.add(new StatisticalData("拜访数"));

        monthList.add(new StatisticalData("注册总数"));
        monthList.add(new StatisticalData("月平均日活量"));
        monthList.add(new StatisticalData("下单金额"));
        monthList.add(new StatisticalData("新增平均日活"));
        monthList.add(new StatisticalData("新增下单数"));
        monthList.add(new StatisticalData("下单金额"));

        addList.add(new StatisticalData("注册总数"));
        addList.add(new StatisticalData("下单客户数"));
        addList.add(new StatisticalData("未下单数"));
        addList.add(new StatisticalData("待审核数"));
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homepageAdapter = new HomepageAdapter(R.layout.homepage_item_layout);
        rv_recyclerView.setAdapter(homepageAdapter);

        View footView = ViewUrils.getFragmentView(rv_recyclerView, R.layout.homepage_foot_layout);

        homepageAdapter.addFooterView(footView);
        rl_rights=footView.findViewById(R.id.rl_rights);
        tv_today=footView.findViewById(R.id.tv_today);
        tv_month=footView.findViewById(R.id.tv_month);
        rl_rights.setOnClickListener(this);
         rl_right=footView.findViewById(R.id.rl_right);
        rl_right.setOnClickListener(this);
        today_recyclerView=  footView.findViewById(R.id.today_recyclerView);
        month_recyclerView=  footView.findViewById(R.id.month_recyclerView);
        all_recyclerView=  footView.findViewById(R.id.all_recyclerView);
        attention_recyclerView=  footView.findViewById(R.id.attention_recyclerView);
        register_recyclerView=  footView.findViewById(R.id.register_recyclerView);
        order_recyclerView=  footView.findViewById(R.id.order_recyclerView);
        tv_bottom=  footView.findViewById(R.id.tv_bottom);
        tv_bottom.setText("我是123456778442222222222222222222222222222222222222222222222222");
        tv_bottom.setSelected(true);
        tv_today.setText( (Calendar.getInstance().get(Calendar.MONTH)+1)+"月"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))+"日");
        tv_month.setText((Calendar.getInstance().get(Calendar.MONTH)+1)+"月");



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
        attentionDataList.add(new AttentionData("即将掉落"));
        attentionDataList.add(new AttentionData("公海跟进"));
        attentionDataList.add(new AttentionData("创客审核"));
        attentionDataList.add(new AttentionData("设置专员"));
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
            }else if (pos==1){
                Intent intent=new Intent(getContext(), CommonFollowUpActivity.class);
                startActivity(intent);
            }else if (pos==2){
                Intent intent=new Intent(getContext(), PartnerCheckActivity.class);
                startActivity(intent);

            }else {
                Intent intent=new Intent(getContext(), SettingPersonActivity.class);
                startActivity(intent);

            }

        }

    }
}
