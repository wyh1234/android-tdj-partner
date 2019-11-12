package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.HomepageAdapter;
import com.tdjpartner.adapter.home.AllDataAdapter;
import com.tdjpartner.adapter.home.AttentionDataAdapter;
import com.tdjpartner.adapter.home.HomeOrderTimesAdapter;
import com.tdjpartner.adapter.home.HomeRegisterTimesAdapter;
import com.tdjpartner.adapter.home.MonthDataAdapter;
import com.tdjpartner.adapter.home.TodyDataAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.HomeFilter;
import com.tdjpartner.model.StatisticalData;
import com.tdjpartner.mvp.presenter.HomePagePresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.statistics.StatisticsListActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class HomePageActivity extends BaseActivity<HomePagePresenter> implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    private RelativeLayout rl_right, rl_rights;
    private RecyclerView today_recyclerView, month_recyclerView, all_recyclerView;
    private LinearLayout ll,ll_one,ll_two;
    private List<StatisticalData> todayList = new ArrayList<>();
    private List<StatisticalData> monthList = new ArrayList<>();
    private List<StatisticalData> addList = new ArrayList<>();
    private TodyDataAdapter todyDataAdapter;
    private HomepageAdapter homepageAdapter;
    private MonthDataAdapter monthDataAdapter;
    private AllDataAdapter allDataAdapter;
    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    private TextView tv_today, tv_month;
    private boolean f;
    private HomeFilter homeFilter=new HomeFilter();
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
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
    protected HomePagePresenter loadPresenter() {
        return new HomePagePresenter();
    }

    @Override
    protected void initData() {
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
        ll=  footView.findViewById(R.id.ll);
        ll_one=  footView.findViewById(R.id.ll_one);
        ll_two=  footView.findViewById(R.id.ll_two);
        ll.setVisibility(View.GONE);
        ll_one.setVisibility(View.GONE);
        ll_two.setVisibility(View.GONE);
        //今日统计；
        ScrollLinearLayoutManager layoutManager=   new ScrollLinearLayoutManager(this, 5);
        today_recyclerView.setLayoutManager(layoutManager);
        todyDataAdapter = new TodyDataAdapter(R.layout.tody_data_item,todayList);
        todyDataAdapter.setOnItemClickListener(this);
        today_recyclerView.setAdapter(todyDataAdapter);

        //当月统计；
        ScrollLinearLayoutManager layoutManager1=   new ScrollLinearLayoutManager(this, 3);
        month_recyclerView.setLayoutManager(layoutManager1);
        monthDataAdapter = new MonthDataAdapter(R.layout.month_data_item,monthList);
        monthDataAdapter.setOnItemClickListener(this);
        month_recyclerView.setAdapter(monthDataAdapter);

        //所有统计；
        ScrollLinearLayoutManager layoutManager2=   new ScrollLinearLayoutManager(this, 4);
        all_recyclerView.setLayoutManager(layoutManager2);
        allDataAdapter = new AllDataAdapter(R.layout.tody_data_item,addList);
        allDataAdapter.setOnItemClickListener(this);
        all_recyclerView.setAdapter(allDataAdapter);
        tv_today.setText( (Calendar.getInstance().get(Calendar.MONTH)+1)+"月"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))+"日");
        tv_month.setText((Calendar.getInstance().get(Calendar.MONTH)+1)+"月");
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        toolbar.setBackgroundResource(R.mipmap.home_bg);
        tv_title.setText("个人数据");

        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        selectedDate=Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-6),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
//        endDate.set(2029, 11, 28);
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));
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
    protected int getLayoutId() {
        return R.layout.homepage_activity_layout;
    }

    @Override
    public void onRefresh() {
        getData(homeFilter);
    }

    public void getData(HomeFilter homeFilter){
        LogUtils.e(homeFilter);
        Map<String,Object> map=new HashMap<>();
//        UserUtils.getInstance().getLoginBean().getEntityId()
        map.put("userId",Integer.parseInt(getIntent().getStringExtra("userId")));
        map.put("seatType",2);
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        map.put("monthTime", GeneralUtils.isNullOrZeroLenght(homeFilter.getMonthTime())?Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                :homeFilter.getMonthTime());
        map.put("dayDate", GeneralUtils.isNullOrZeroLenght(homeFilter.getDayDate())?Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                :homeFilter.getDayDate());
        mPresenter.homeData(map);

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter instanceof TodyDataAdapter){
            Intent intent=new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("title","今日统计");
            intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
            intent.putExtra("dayDate",GeneralUtils.isNullOrZeroLenght(homeFilter.getDayDate())?Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                    :homeFilter.getDayDate());
            startActivity(intent);
//            }
        }else if (baseQuickAdapter instanceof MonthDataAdapter){
            Intent intent=new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("title","月统计");
            intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
            intent.putExtra("monthTime",GeneralUtils.isNullOrZeroLenght(homeFilter.getMonthTime())?Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                    :homeFilter.getMonthTime());
            startActivity(intent);

        }else if (baseQuickAdapter instanceof AllDataAdapter){
            Intent intent=new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("userId",UserUtils.getInstance().getLoginBean().getEntityId()+"");
            intent.putExtra("title","所有统计");
            startActivity(intent);
        }

    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
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

    }

    public void homeData_failed() {
        stop();
    }
}
