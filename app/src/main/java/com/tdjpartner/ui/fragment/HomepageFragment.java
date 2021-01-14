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
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamPreviewAdapter;
import com.tdjpartner.adapter.TeamPreviewAllAdapter;
import com.tdjpartner.adapter.TeamPreviewMothAdapter;
import com.tdjpartner.adapter.home.HomeOrderTimesAdapter;
import com.tdjpartner.adapter.home.HomeRegisterTimesAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.HomeFilter;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.presenter.HomepageFragmentPresenter;
import com.tdjpartner.ui.activity.TeamMemberActivity;
import com.tdjpartner.ui.activity.TeamPreviewActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.widget.CustomLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class HomepageFragment extends BaseFrgment<HomepageFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener
    , View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
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
    @BindView(R.id.tv_heard)
    TextView tv_heard;
    private RecyclerView  month_recyclerView, all_recyclerView, register_recyclerView, order_recyclerView;
    private TeamPreviewAdapter teamPreviewAdapter;
    private TeamPreviewMothAdapter teamPreviewAdapter1;
    private TeamPreviewAllAdapter teamPreviewAdapter2;
    private List<HomeData.RegisterTimesTopBean> registerlist = new ArrayList<>();
    private List<HomeData.OrdersTimesTopBean> orderList = new ArrayList<>();
    private HomeOrderTimesAdapter homeOrderTimesAdapter;
    private HomeRegisterTimesAdapter homeRegisterTimesAdapter;
    private TimePickerView pvTime;
    private  HomeFilter homeFilter=new HomeFilter();
    private String startTime="";
    private List<TeamOverView> data=new ArrayList<>();//今日
    private List<TeamOverView> data1=new ArrayList<>();//当月
    private List<TeamOverView> data2=new ArrayList<>();//所有

    private Calendar selectedDate, endDate, startDate;
    @OnClick({R.id.rl_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_team:
                Intent intent = new Intent(getContext(), TeamMemberActivity.class);
                intent.putExtra("userId", UserUtils.getInstance().getLoginBean().getEntityId() + "");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);



        if (UserUtils.getInstance().getLoginBean().getGrade()!=3){
            rl_team.setVisibility(View.VISIBLE);
        }else {
            rl_team.setVisibility(View.GONE);
        }


        selectedDate= Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-6),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    protected void loadData() {

        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomLinearLayout layout1 = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout1.setScrollEnabled(false);
        CustomLinearLayout layout2 = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout2.setScrollEnabled(false);
        teamPreviewAdapter=new TeamPreviewAdapter(R.layout.teampreview_item_layout,data);
        rv_recyclerView.setAdapter(teamPreviewAdapter);
        View footView = ViewUrils.getFragmentView(rv_recyclerView, R.layout.homepage_new_foot_layout);
        teamPreviewAdapter.addFooterView(footView);


        month_recyclerView=  footView.findViewById(R.id.recyclerView_month_list);
        all_recyclerView=  footView.findViewById(R.id.recyclerView_all_list);



        month_recyclerView.setLayoutManager(layout1);
        all_recyclerView.setLayoutManager(layout2);





        teamPreviewAdapter1=new TeamPreviewMothAdapter(R.layout.teampreview_item_layout,data1);
        month_recyclerView.setAdapter(teamPreviewAdapter1);

        teamPreviewAdapter2=new TeamPreviewAllAdapter(R.layout.teampreview_item_layout,data2);
        all_recyclerView.setAdapter(teamPreviewAdapter2);


        register_recyclerView=  footView.findViewById(R.id.register_recyclerView);
        order_recyclerView=  footView.findViewById(R.id.order_recyclerView);

        tv_username.setText("你好,"+UserUtils.getInstance().getLoginBean().getRealname()+"!");
        tv_time.setText( GeneralUtils.getCurrDay()+"\t\t"+GeneralUtils.getWeekDay(System.currentTimeMillis()));
        tv_heard.setText("当前成员");
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


    public void teamOverView_day(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_day(map);//今日；
    }
    public void teamOverView_month(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_month(map);//今月；
    }
    public void teamOverView_all(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.teamOverView_all(map);//今月；
        LogUtils.e(map);
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
        teamOverView_day();
        teamOverView_month();
        teamOverView_all();
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



    public void homeData_Success(HomeData homeData) {
        stop();
            if (!ListUtils.isEmpty(registerlist)) {
                registerlist.clear();
            }
            if (!ListUtils.isEmpty(orderList)) {
                orderList.clear();
            }


        registerlist.addAll(homeData.getRegisterTimesTop());
        homeRegisterTimesAdapter.setNewData(registerlist);

        orderList.addAll(homeData.getOrdersTimesTop());
        homeOrderTimesAdapter.setNewData(orderList);




    }

    public void teamOverView_day_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data)) {
            data.clear();
        }


        data.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)){
            data.get(0).setDate(startTime.substring(5,10).replace("-","月")+"日");
            LogUtils.e(startTime.substring(5,10).replace("-","月")+"日");
        }

        teamPreviewAdapter.notifyDataSetChanged();


    }
    public void teamOverView_month_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data1)) {
            data1.clear();
        }
        data1.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)){
            data1.get(0).setDate(startTime.substring(5,7)+"月");
            LogUtils.e(startTime.substring(5,7)+"月");
        }
        teamPreviewAdapter1.setNewData(data1);
    }
    public void teamOverView_all_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data2)) {
            data2.clear();
        }
        data2.add(teamOverView);
        teamPreviewAdapter2.setNewData(data2);
    }

    public void homeData_failed() {
        stop();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()==R.id.rl_right){
            if (baseQuickAdapter instanceof TeamPreviewAdapter){
                setTime(1);
            }else if (baseQuickAdapter instanceof TeamPreviewMothAdapter){
                setTime(2);
            }
        }
    }

    public void setTime(int type){
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startTime=GeneralUtils.getTimeFilter(date);
                if (type==1){
                    teamOverView_day();

                }else {
                    teamOverView_month();
                }

            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, type==1, false, false, false})
                .setLabel("年", "月", type==1?"日":"", "", "", "")
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

}
