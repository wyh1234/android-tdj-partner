package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.DayPreviewAdapter;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.adapter.MonthPreviewAdapter;
import com.tdjpartner.adapter.TeamPreviewAdapter;
import com.tdjpartner.adapter.TeamPreviewAllAdapter;
import com.tdjpartner.adapter.TeamPreviewMothAdapter;
import com.tdjpartner.adapter.home.NewHomeOrderTimesAdapter;
import com.tdjpartner.adapter.home.NewHomeRegisterTimesAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.HomeFilter;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.presenter.IronIndexFragmentPresenter;
import com.tdjpartner.ui.activity.TeamMemberActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/3.
 */
public class IronIndexFragment extends BaseFrgment<IronIndexFragmentPresenter>
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.day_recyclerView)
    RecyclerView day_recyclerView;
    @BindView(R.id.month_recyclerView)
    RecyclerView month_recyclerView;
    @BindView(R.id.rl_team)
    RelativeLayout rl_team;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_heard)
    TextView tv_heard;
    private RecyclerView all_recyclerView, register_recyclerView, order_recyclerView;
    private DayPreviewAdapter dayPreviewAdapter;
    private MonthPreviewAdapter monthPreviewAdapter;

    private TeamPreviewMothAdapter teamPreviewMothAdapter;
    private TeamPreviewAllAdapter teamPreviewAllAdapter;
    private List<NewHomeData.RegisterTimesTopListBean> registerlist = new ArrayList<>();
    private List<NewHomeData.OrdersTimesTopList> orderList = new ArrayList<>();
    private NewHomeOrderTimesAdapter homeOrderTimesAdapter;
    private NewHomeRegisterTimesAdapter homeRegisterTimesAdapter;
    private TimePickerView pvTime;
    private HomeFilter homeFilter = new HomeFilter();
    private String startTime = "";
    private List<TeamOverView> data = new ArrayList<>();//今日
    private List<TeamOverView> data1 = new ArrayList<>();//当月
    private List<TeamOverView> data2 = new ArrayList<>();//所有

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


        if (UserUtils.getInstance().getLoginBean().getGrade() != 3) {
            rl_team.setVisibility(View.VISIBLE);
        } else {
//            rl_team.setVisibility(View.GONE);
        }


        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));




        //排行榜
        ViewPager viewPager = view.findViewById(R.id.ranking_vp);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            List<String> titles = Arrays.asList("月日活", "月均日活", "月GMV");

            @Override
            public Fragment getItem(int i) {
                Fragment fragment = new RankingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                System.out.println("position = " + position);
                return titles.get(position);
            }
        });

        WTabLayout wtab = view.findViewById(R.id.wtab);
        wtab.setupWithViewPager(viewPager);

    }


    @Override
    protected void loadData() {

        //初始化顶部UI
        tv_username.setText("你好," + UserUtils.getInstance().getLoginBean().getRealname() + "!");
        tv_time.setText(GeneralUtils.getCurrDay() + "\t\t" + GeneralUtils.getWeekDay(System.currentTimeMillis()));
        tv_heard.setText(tv_heard.getText() + "武汉");

        //初始化日统计RV
        day_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dayPreviewAdapter = new DayPreviewAdapter(R.layout.day_preview_item, data);
        dayPreviewAdapter.setOnItemChildClickListener(this);
        day_recyclerView.setAdapter(dayPreviewAdapter);

        //初始化月统计RV
        month_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        monthPreviewAdapter = new MonthPreviewAdapter(R.layout.month_preview_item, data);
        monthPreviewAdapter.setOnItemChildClickListener(this);
        month_recyclerView.setAdapter(monthPreviewAdapter);



        //---------初始化footView------------------
//        View footView = ViewUrils.getFragmentView(rv_recyclerView, R.layout.homepage_new_foot_layout);
//
//        //初始化月统计RV
//        month_recyclerView = footView.findViewById(R.id.recyclerView_month_list);
//        CustomLinearLayout customLinearLayout = new CustomLinearLayout(getContext(), CustomLinearLayout.VERTICAL, false);
//        customLinearLayout.setScrollEnabled(false);
//        month_recyclerView.setLayoutManager(customLinearLayout);
//        teamPreviewMothAdapter = new TeamPreviewMothAdapter(R.layout.teampreview_item_layout, data1);
//        teamPreviewMothAdapter.setTiltle("月统计");
//        teamPreviewMothAdapter.setOnItemChildClickListener(this);
//        month_recyclerView.setAdapter(teamPreviewMothAdapter);
//
//
//        //初始化总统计RV
//        all_recyclerView = footView.findViewById(R.id.recyclerView_all_list);
//        customLinearLayout = new CustomLinearLayout(getContext(), CustomLinearLayout.VERTICAL, false);
//        customLinearLayout.setScrollEnabled(false);
//        all_recyclerView.setLayoutManager(customLinearLayout);
//        teamPreviewAllAdapter = new TeamPreviewAllAdapter(R.layout.teampreview_item_layout, data2);
//        teamPreviewAllAdapter.setTiltle("总统计");
//        all_recyclerView.setAdapter(teamPreviewAllAdapter);
//
//
//
//        //初始化新注册VR
//        register_recyclerView = footView.findViewById(R.id.register_recyclerView);
//        register_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        homeRegisterTimesAdapter = new NewHomeRegisterTimesAdapter(R.layout.ranking_item_layout, registerlist);
//        register_recyclerView.setAdapter(homeRegisterTimesAdapter);
//
//
//        //初始化新下单VR
//        order_recyclerView = footView.findViewById(R.id.order_recyclerView);
//        order_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        homeOrderTimesAdapter = new NewHomeOrderTimesAdapter(R.layout.ranking_item_layout, orderList);
//        order_recyclerView.setAdapter(homeOrderTimesAdapter);



//        teamPreviewAdapter.addFooterView(footView);





        //-----------刷新加载数据----------------
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();


    }


    public void teamOverView_day() {

        if (startTime.isEmpty()) {
            startTime = GeneralUtils.getTimeFilter(new Date());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_day(map);//今日；
    }

    public void teamOverView_month() {

        if (startTime.isEmpty()) {
            startTime = GeneralUtils.getMonthFilter(new Date());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_month(map);//今月；
    }

    public void teamOverView_all() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.teamOverView_all(map);//今月；
        LogUtils.e(map);
    }

    @Override
    protected IronIndexFragmentPresenter loadPresenter() {
        return new IronIndexFragmentPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.iron_index_fragment;
    }

    @Override
    public void onRefresh() {
//        getData(homeFilter);

        getData();
        teamOverView_day();
        teamOverView_month();
//        teamOverView_all();//团队概览已经不再使用了
    }

    public void getData() {
        LogUtils.e(homeFilter);
        Map<String, Object> map = new HashMap<>();
//        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
//        map.put("seatType",1);
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
//        map.put("monthTime", GeneralUtils.isNullOrZeroLenght(homeFilter.getMonthTime()) ? GeneralUtils.getCurr()
//                : homeFilter.getMonthTime());
//        map.put("dayDate", GeneralUtils.isNullOrZeroLenght(homeFilter.getDayDate()) ? GeneralUtils.getCurr()
//                : homeFilter.getDayDate());
        mPresenter.newhomeData(map);

    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    public void homeData_Success(NewHomeData homeData) {
        stop();
        if (!ListUtils.isEmpty(registerlist)) {
            registerlist.clear();
        }
        if (!ListUtils.isEmpty(orderList)) {
            orderList.clear();
        }


        registerlist.addAll(homeData.getRegisterTimesTopList());
        homeRegisterTimesAdapter.setNewData(registerlist);

        orderList.addAll(homeData.getOrdersTimesTopList());
        homeOrderTimesAdapter.setNewData(orderList);


    }

    public void teamOverView_day_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data)) {
            data.clear();
        }


        data.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)) {
            data.get(0).setDate(startTime.substring(5, 10).replace("-", "月") + "日");
            LogUtils.e(startTime.substring(5, 10).replace("-", "月") + "日");
        }

        dayPreviewAdapter.notifyDataSetChanged();


    }

    public void teamOverView_month_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data1)) {
            data1.clear();
        }
        data1.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)) {
            data1.get(0).setDate(startTime.substring(5, 7) + "月");
            LogUtils.e(startTime.substring(5, 7) + "月");
        }
        monthPreviewAdapter.setNewData(data1);
    }

    public void teamOverView_all_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data2)) {
            data2.clear();
        }
        data2.add(teamOverView);
        teamPreviewAllAdapter.setNewData(data2);
    }

    public void homeData_failed() {
        stop();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId() == R.id.rl_right) {
            if (baseQuickAdapter instanceof TeamPreviewAdapter) {
                setTime(1);
            } else if (baseQuickAdapter instanceof TeamPreviewMothAdapter) {
                setTime(2);
            }
        }
    }

    public void setTime(int type) {
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type == 1) {
                    startTime = GeneralUtils.getTimeFilter(date);
                    teamOverView_day();
                } else {
                    startTime = GeneralUtils.getMonthFilter(date);
                    teamOverView_month();
                }

            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, type == 1, false, false, false})
                .setLabel("年", "月", type == 1 ? "日" : "", "", "", "")
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
