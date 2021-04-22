package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.adapter.NetAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.NetHomeData;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.ui.activity.IronDayActivity;
import com.tdjpartner.ui.activity.StatisticsListActivity;
import com.tdjpartner.ui.activity.IronMonthActivity;
import com.tdjpartner.ui.activity.TeamMemberActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/4/20.
 */
public class NetIndexFragment extends NetworkFragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.day_listView)
    ListView day_listView;
    @BindView(R.id.member_list)
    ListView month_listView;
    @BindView(R.id.keyPoint_rv)
    RecyclerView keyPoint_rv;

    @BindView(R.id.ll_team)
    LinearLayout rl_team;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_heard)
    TextView tv_heard;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_month_sink)
    TextView tv_month_sink;
    @BindView(R.id.tv_day_sink)
    TextView tv_day_sink;

    @BindView(R.id.ranking_vp)
    ViewPager ranking_vp;
    List<String> titles;

    boolean isDay;//时间类型标记
    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型
    int site = UserUtils.getInstance().getLoginBean().getSite();//用户类型

    private NetAdapter<NetHomeData> netDayAdapter, netMonthAdapter;
    private BaseQuickAdapter<NetHomeData.PartnerApproachDataBean, BaseViewHolder> keyPointAdapter;
    private List<NewHomeData.RegisterTimesTopListBean> registerlist = new ArrayList<>();
    private List<NewHomeData.OrdersTimesTopList> orderList = new ArrayList<>();

    @OnClick({R.id.ll_team, R.id.tv_day, R.id.tv_month, R.id.tv_day_sink, R.id.tv_month_sink})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_team:
                Intent intent = new Intent(getContext(), TeamMemberActivity.class);
                intent.putExtra("userId", UserUtils.getInstance().getLoginBean().getEntityId() + "");
                startActivity(intent);
                break;

            case R.id.tv_month:
                view.setBackgroundResource(R.drawable.bg_orange_left_semi_4);
                ((TextView) view).setTextColor(Color.WHITE);
                tv_day.setBackgroundResource(R.drawable.bg_grey_right_semi_4);
                tv_day.setTextColor(Color.BLACK);
                isDay = false;
                titles = Arrays.asList("月日活", "月均日活", "月GMV");
                ranking_vp.getAdapter().notifyDataSetChanged();
                break;

            case R.id.tv_day:
                view.setBackgroundResource(R.drawable.bg_orange_right_semi_4);
                ((TextView) view).setTextColor(Color.WHITE);
                tv_month.setBackgroundResource(R.drawable.bg_grey_left_semi_4);
                tv_month.setTextColor(Color.BLACK);
                isDay = true;
                titles = Arrays.asList("GMV", "注册数", "新开");
                ranking_vp.getAdapter().notifyDataSetChanged();
                break;
        }

        if (view.getId() == R.id.tv_day_sink) {
            Intent intent = new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("isDay", true);
            startActivity(intent);
        }
        if (view.getId() == R.id.tv_month_sink) {
            Intent intent = new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("isDay", false);
            startActivity(intent);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.net_index_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (UserUtils.getInstance().getLoginBean().getGrade() != 3) {
            rl_team.setVisibility(View.VISIBLE);
        } else {
//            rl_team.setVisibility(View.GONE);
        }

        //初始化刷新布局
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        //初始化顶部UI
        tv_username.setText("你好," + UserUtils.getInstance().getLoginBean().getRealname() + "!");
        tv_time.setText(GeneralUtils.getCurrDay() + "\t\t" + GeneralUtils.getWeekDay(System.currentTimeMillis()));
        tv_heard.setText(tv_heard.getText() + "武汉");


        //初始化日月统计
        netDayAdapter = new NetAdapter.Builder<NetHomeData>()
                .setOnClickListener(this)
                .setResource(R.layout.net_day_preview_item)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.dayRegisterTimes)).setText("" + data.getTodayData().dayRegisterTimes);
                    ((TextView) convertView.findViewById(R.id.firstOrderNum)).setText("" + data.getTodayData().firstOrderNum);
                    ((TextView) convertView.findViewById(R.id.activeNum)).setText("" + data.getTodayData().activeNum);
                    ((TextView) convertView.findViewById(R.id.yesterdayActiveNum)).setText("" + data.getTodayData().yesterdayActiveNum);
                    ((TextView) convertView.findViewById(R.id.callNum)).setText("" + data.getTodayData().callNum);
                    ((TextView) convertView.findViewById(R.id.todayAmount)).setText("" + data.getTodayData().todayAmount);
                    ((TextView) convertView.findViewById(R.id.averageAmount)).setText("" + data.getTodayData().averageAmount);
                    ((TextView) convertView.findViewById(R.id.todayAfterSaleTimes)).setText("" + data.getTodayData().todayAfterSaleTimes);
                    ((TextView) convertView.findViewById(R.id.afterSaleAmount)).setText("" + data.getTodayData().afterSaleAmount);
                })
                .build(getContext());
        day_listView.setAdapter(netDayAdapter);
        day_listView.setNestedScrollingEnabled(true);


        netMonthAdapter = new NetAdapter.Builder<NetHomeData>()
                .setOnClickListener(this)
                .setResource(R.layout.net_month_preview_item)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.monthRegisterNum)).setText("" + data.getMonthData().monthRegisterNum);
                    ((TextView) convertView.findViewById(R.id.monthFirstOrderNum)).setText("" + data.getMonthData().monthFirstOrderNum);
                    ((TextView) convertView.findViewById(R.id.monthActiveNum)).setText("" + data.getMonthData().monthActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthAvgActiveNum)).setText("" + data.getMonthData().monthAvgActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthCallNum)).setText("" + data.getMonthData().monthCallNum);
                    ((TextView) convertView.findViewById(R.id.monthAmount)).setText("" + data.getMonthData().monthAmount);
                    ((TextView) convertView.findViewById(R.id.addMonthAmount)).setText("" + data.getMonthData().addMonthAmount);
                    ((TextView) convertView.findViewById(R.id.monthAverageAmount)).setText("" + data.getMonthData().monthAverageAmount);
                    ((TextView) convertView.findViewById(R.id.monthAfterSaleTimes)).setText("" + data.getMonthData().monthAfterSaleTimes);
                    ((TextView) convertView.findViewById(R.id.monthAfterSaleAmount)).setText("" + data.getMonthData().monthAfterSaleAmount);
                })
                .build(getContext());
        month_listView.setAdapter(netMonthAdapter);
        month_listView.setNestedScrollingEnabled(true);


        //重点关注
        keyPointAdapter = new BaseQuickAdapter<NetHomeData.PartnerApproachDataBean, BaseViewHolder>(R.layout.key_point_item) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, NetHomeData.PartnerApproachDataBean data) {
                ImageLoad.loadImageViewLoding(data.getMenuPic(), baseViewHolder.getView(R.id.image));

                baseViewHolder.setText(R.id.title, "" + data.getTitle());

                if (!data.getSubscriptNum().isEmpty()) {
                    baseViewHolder.getView(R.id.count).setVisibility(View.VISIBLE);
                    baseViewHolder.setText(R.id.count, data.getSubscriptNum());
                } else {
                    baseViewHolder.getView(R.id.count).setVisibility(View.GONE);
                }
            }
        };
        keyPointAdapter.setOnItemChildClickListener(this);
        keyPoint_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        keyPoint_rv.setAdapter(keyPointAdapter);


        //排行榜
        tv_day.setOnClickListener(this);
        tv_month.setOnClickListener(this);
        ranking_vp.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public android.support.v4.app.Fragment getItem(int i) {
                android.support.v4.app.Fragment fragment = new RankingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type", i + 1);
                bundle.putInt("websiteId", site);
                bundle.putInt("userType", userType);
                bundle.putBoolean("isDay", isDay);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                if (titles == null) {
                    if (userType == 1) {
                        titles = Arrays.asList("月日活", "月均日活", "月GMV");
                    } else {
                        titles = Arrays.asList("月总GMV", "注册总数", "新开总数");
                    }
                }
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
        wtab.setupWithViewPager(ranking_vp);


        //加载数据
        getVMWithFragment().loadingWithNewLiveData(NetHomeData.class, getArgs())
                .observe(this, netHomeData -> {
                    stop();
                    if (!ListUtils.isEmpty(registerlist)) {
                        registerlist.clear();
                    }
                    if (!ListUtils.isEmpty(orderList)) {
                        orderList.clear();
                    }

                    //日统计
                    tv_day_sink.setText(netHomeData.getTodayData().gradeNextName.isEmpty() ? "" : netHomeData.getTodayData().gradeNextName + " >");
                    netDayAdapter.clear();
                    netDayAdapter.add(netHomeData);
                    netDayAdapter.notifyDataSetChanged();


                    //月统计
                    tv_month_sink.setText(netHomeData.getMonthData().gradeNextName.isEmpty() ? "" : netHomeData.getMonthData().gradeNextName + " >");
                    netMonthAdapter.clear();
                    netMonthAdapter.add(netHomeData);
                    netMonthAdapter.notifyDataSetChanged();


                    //重点关注
                    keyPointAdapter.setNewData(netHomeData.getPartnerApproachData());
                    keyPointAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onRefresh() {
        Map<String, Object> map = new HashMap<>();

        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        System.out.println("userId is " + UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
        map.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());

//        map.put("userId", 258869);
//        map.put("monthTime", "2021-04");
//        map.put("dayDate", "2021-04-09");
//        map.put("websiteId", 3);

        getVMWithFragment().loading(NetHomeData.class, map);
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        System.out.println("~~" + getClass().getSimpleName() + ".onItemChildClick~~");
        System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);
        switch (view.getId()) {

            case R.id.tv_day:
                getActivity().startActivity(new Intent(getContext(), IronDayActivity.class));
                break;

            case R.id.tv_month:
                getActivity().startActivity(new Intent(getContext(), IronMonthActivity.class));
                break;
        }
    }
}
