package com.tdjpartner.ui.fragment;;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.adapter.HomepageAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.home.AllDataAdapter;
import com.tdjpartner.adapter.home.AttentionDataAdapter;
import com.tdjpartner.adapter.home.HomeOrderTimesAdapter;
import com.tdjpartner.adapter.home.HomeRegisterTimesAdapter;
import com.tdjpartner.adapter.home.MonthDataAdapter;
import com.tdjpartner.adapter.home.TodyDataAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.HomeFilter;
import com.tdjpartner.model.StatisticalData;
import com.tdjpartner.mvp.presenter.HomepageFragmentPresenter;
import com.tdjpartner.ui.activity.CommonFollowUpActivity;
import com.tdjpartner.ui.activity.DropOutingActivity;
import com.tdjpartner.ui.activity.PartnerCheckActivity;
import com.tdjpartner.ui.activity.SettingPersonActivity;
import com.tdjpartner.ui.activity.TeamPreviewActivity;
import com.tdjpartner.ui.activity.statistics.StatisticsListActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private RecyclerView today_recyclerView, month_recyclerView, all_recyclerView, attention_recyclerView, register_recyclerView, order_recyclerView;
//    private TextView tv_bottom;
    private List<StatisticalData> todayList = new ArrayList<>();
    private List<StatisticalData> monthList = new ArrayList<>();
    private List<StatisticalData> addList = new ArrayList<>();
    private List<HomeData.PartnerApproachDataBean> attentionDataList = new ArrayList<>();
    private List<HomeData.RegisterTimesTopBean> registerlist = new ArrayList<>();
    private List<HomeData.OrdersTimesTopBean> orderList = new ArrayList<>();
    private TodyDataAdapter todyDataAdapter;
    private HomepageAdapter homepageAdapter;
    private MonthDataAdapter monthDataAdapter;
    private AllDataAdapter allDataAdapter;
    private AttentionDataAdapter attentionDataAdapter;
    private HomeOrderTimesAdapter homeOrderTimesAdapter;
    private HomeRegisterTimesAdapter homeRegisterTimesAdapter;
    private RelativeLayout rl_right, rl_rights;
    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    private TextView tv_today, tv_month;
    private boolean f;
    private  HomeFilter homeFilter=new HomeFilter();
    @OnClick({R.id.rl_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_team:
                Intent intent = new Intent(getContext(), TeamPreviewActivity.class);
                intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
                startActivity(intent);
                break;
            case R.id.rl_right:
                f=true;
                setTime(f);
                break;
            case R.id.rl_rights:
                f=false;
                setTime(f);
                break;
        }
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        selectedDate=Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-6),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
//        endDate.set(2029, 11, 28);
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));

        if (UserUtils.getInstance().getLoginBean().getGrade()!=3){
            rl_team.setVisibility(View.VISIBLE);
        }else {
            rl_team.setVisibility(View.GONE);
        }

    }
    public void setTime(boolean type){
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (f){
                    homeFilter.setDayDate(GeneralUtils.getTimeFilter(date));
                    tv_today.setText(GeneralUtils.getTimes(date));

                }else {
                    homeFilter.setMonthTime(GeneralUtils.getTimeFilter(date));
                    tv_month.setText(GeneralUtils.getTime(date));
                }
                swipeRefreshLayout.setRefreshing(true);
                getData(homeFilter);
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, type, false, false, false})
                .setLabel("年", "月",  type?"日":"", "", "", "")
                .isCenterLabel(true)
                .setLineSpacingMultiplier(1.8f)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
        pvTime.show();
    }


    @Override
    protected void loadData() {
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

        tv_today.setText( GeneralUtils.getCurrDay());
        tv_month.setText( GeneralUtils.getCurrMonth());
        tv_username.setText("你好,"+UserUtils.getInstance().getLoginBean().getRealname()+"!");
        tv_time.setText( GeneralUtils.getCurrDay()+"\t\t"+GeneralUtils.getWeekDay(System.currentTimeMillis()));


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
        ScrollLinearLayoutManager layoutManager3=   new ScrollLinearLayoutManager(getActivity(), 4);
        attention_recyclerView.setLayoutManager(layoutManager3);
        attentionDataAdapter = new AttentionDataAdapter(R.layout.attention_data_item,attentionDataList);
        attention_recyclerView.setAdapter(attentionDataAdapter);
        attentionDataAdapter.setOnItemClickListener(this);
        //新注册；
        register_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRegisterTimesAdapter = new HomeRegisterTimesAdapter(R.layout.ranking_item_layout,registerlist);
        register_recyclerView.setAdapter(homeRegisterTimesAdapter);
        //新下单；
        order_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeOrderTimesAdapter = new HomeOrderTimesAdapter(R.layout.ranking_item_layout,orderList);
        order_recyclerView.setAdapter(homeOrderTimesAdapter);

        swipeRefreshLayout.setRefreshing(true);
        onRefresh();


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
        getData(homeFilter);
    }

    public void getData(HomeFilter homeFilter){
        LogUtils.e(homeFilter);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("seatType",1);
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        map.put("monthTime", GeneralUtils.isNullOrZeroLenght(homeFilter.getMonthTime())?GeneralUtils.getCurr()
                :homeFilter.getMonthTime());
        map.put("dayDate", GeneralUtils.isNullOrZeroLenght(homeFilter.getDayDate())?GeneralUtils.getCurr()
                :homeFilter.getDayDate());
        mPresenter.homeData(map);

    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int pos) {
        if (baseQuickAdapter instanceof TodyDataAdapter){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("title","今日统计");
                 intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
                intent.putExtra("dayDate",GeneralUtils.isNullOrZeroLenght(homeFilter.getDayDate())?GeneralUtils.getCurr()
                        :homeFilter.getDayDate());
                startActivity(intent);
//            }
        }else if (baseQuickAdapter instanceof MonthDataAdapter){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("title","月统计");
                 intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
                intent.putExtra("monthTime",GeneralUtils.isNullOrZeroLenght(homeFilter.getMonthTime())?GeneralUtils.getCurr()
                        :homeFilter.getMonthTime());
                startActivity(intent);

        }else if (baseQuickAdapter instanceof AllDataAdapter){
                Intent intent=new Intent(getContext(), StatisticsListActivity.class);
                intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
                intent.putExtra("title","所有统计");
                startActivity(intent);
        }else if (baseQuickAdapter instanceof AttentionDataAdapter){
            if (pos==0){
                if (UserUtils.getInstance().getLoginBean().getGrade()==1||UserUtils.getInstance().getLoginBean().getGrade()==5||UserUtils.getInstance().getLoginBean().getGrade()==4){
                    GeneralUtils.showToastshort("该功能无权操作");

                }else {
                    Intent intent=new Intent(getContext(), DropOutingActivity.class);
                    startActivity(intent);
                }

            }else if (pos==1){
                if (UserUtils.getInstance().getLoginBean().getGrade()==1||UserUtils.getInstance().getLoginBean().getGrade()==5||UserUtils.getInstance().getLoginBean().getGrade()==4){
                    GeneralUtils.showToastshort("该功能无权操作");
                }else {
                    Intent intent=new Intent(getContext(), CommonFollowUpActivity.class);
                    startActivity(intent);
                }

            }else if (pos==2){
                if (UserUtils.getInstance().getLoginBean().getGrade()==3){
                    GeneralUtils.showToastshort("该功能无权操作");
                }else {
                    Intent intent=new Intent(getContext(), PartnerCheckActivity.class);
                    startActivity(intent);

                }

            }else {
                if (UserUtils.getInstance().getLoginBean().getGrade()==3||UserUtils.getInstance().getLoginBean().getGrade()==2){
                    GeneralUtils.showToastshort("该功能无权操作");
                }else {
                    Intent intent=new Intent(getContext(), SettingPersonActivity.class);

                    startActivity(intent);

                }

            }

        }

    }

    public void homeData_Success(HomeData homeData) {
        stop();
            if (!ListUtils.isEmpty(todayList)) {
                todayList.clear();
            }
            if (!ListUtils.isEmpty(monthList)) {
                monthList.clear();
            }
            if (!ListUtils.isEmpty(addList)) {
                addList.clear();
            }
            if (!ListUtils.isEmpty(attentionDataList)) {
                attentionDataList.clear();
            }
            if (!ListUtils.isEmpty(registerlist)) {
                registerlist.clear();
            }
            if (!ListUtils.isEmpty(orderList)) {
                orderList.clear();
            }

        StatisticalData statisticalData=new StatisticalData("客户总数",homeData.getAllData().getCountCustomer().toString());
        StatisticalData statisticalData1=new StatisticalData("下单客户数",homeData.getAllData().getOrderCustomer().toString());
        StatisticalData statisticalData2=new StatisticalData("未下单数",homeData.getAllData().getNotOrderCustomer().toString());
        StatisticalData statisticalData3=new StatisticalData("待审核数",homeData.getAllData().getNoVerifyNum().toString());
        addList.add(statisticalData);
        addList.add(statisticalData1);
        addList.add(statisticalData2);
        addList.add(statisticalData3);
        allDataAdapter.setNewData(addList);


        StatisticalData statisticalTodayData=new StatisticalData("注册数",homeData.getTodayData().getDayRegisterTimes().toString());
        StatisticalData statisticalTodayData1=new StatisticalData("新下单数",homeData.getTodayData().getFirstOrderNum().toString());
        StatisticalData statisticalTodayData2=new StatisticalData("日活数",homeData.getTodayData().getActiveNum().toString());
        StatisticalData statisticalTodayData3=new StatisticalData("下单金额",homeData.getTodayData().getTodayAmount().toString());
        StatisticalData statisticalTodayData4=new StatisticalData("拜访数",homeData.getTodayData().getCallNum().toString());
        todayList.add(statisticalTodayData);
        todayList.add(statisticalTodayData1);
        todayList.add(statisticalTodayData2);
        todayList.add(statisticalTodayData3);
        todayList.add(statisticalTodayData4);
        todyDataAdapter.setNewData(todayList);

        StatisticalData statisticalMonthData=new StatisticalData("注册总数",homeData.getMonthData().getMonthRegisterNum().toString());
        StatisticalData statisticalMonthData1=new StatisticalData("月平均日活量",homeData.getMonthData().getMonthAvgActiveNum().toString());
        StatisticalData statisticalMonthData2=new StatisticalData("下单金额",homeData.getMonthData().getMonthAmount().toString());
        StatisticalData statisticalMonthData3=new StatisticalData("新增平均日活",homeData.getMonthData().getLastMonthAvgActiveNum().toString());
        StatisticalData statisticalMonthData4=new StatisticalData("新增下单数",homeData.getMonthData().getMonthFirstOrderNum().toString());
        StatisticalData statisticalMonthData5=new StatisticalData("总拜访数",homeData.getMonthData().getMonthCallNum().toString());
        monthList.add(statisticalMonthData);
        monthList.add(statisticalMonthData1);
        monthList.add(statisticalMonthData2);
        monthList.add(statisticalMonthData3);
        monthList.add(statisticalMonthData4);
        monthList.add(statisticalMonthData5);
        monthDataAdapter.setNewData(monthList);


        attentionDataList.addAll(homeData.getPartnerApproachData());
        attentionDataAdapter.setNewData(attentionDataList);

        registerlist.addAll(homeData.getRegisterTimesTop());
        homeRegisterTimesAdapter.setNewData(registerlist);

        orderList.addAll(homeData.getOrdersTimesTop());
        homeOrderTimesAdapter.setNewData(orderList);




    }

    public void homeData_failed() {
        stop();
    }
}
