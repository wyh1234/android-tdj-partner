package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
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
import android.text.Html;
import android.util.ArrayMap;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.model.V3HomeData;
import com.tdjpartner.ui.activity.ApprovalActivity;
import com.tdjpartner.ui.activity.CommonFollowUpActivity;
import com.tdjpartner.ui.activity.DropOutingActivity;
import com.tdjpartner.ui.activity.NetSupportActivity;
import com.tdjpartner.ui.activity.StatisticsActivity;
import com.tdjpartner.ui.activity.StatisticsListActivity;
import com.tdjpartner.ui.activity.TeamMemberActivity;
import com.tdjpartner.ui.activity.V3TeamMemberActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by LFM on 2021/4/20.
 */
public class NetIndexFragment extends NetworkFragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_team)
    TextView tv_heard;
    @BindView(R.id.day_listView)
    ListView day_listView;
    @BindView(R.id.member_list)
    ListView month_listView;
    @BindView(R.id.keyPoint_rv)
    RecyclerView keyPoint_rv;

    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_month_sink)
    TextView tv_month_sink;
    @BindView(R.id.tv_sink)
    TextView tv_day_sink;

    @BindView(R.id.ranking_vp)
    ViewPager ranking_vp;
    List<String> titles;

    boolean isDay;//时间类型标记
    int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户级别

    private ListViewAdapter<V3HomeData> netDayAdapter, netMonthAdapter;
    private BaseQuickAdapter<V3HomeData.PartnerApproachDataBean, BaseViewHolder> keyPointAdapter;
    private List<NewHomeData.RegisterTimesTopListBean> registerlist = new ArrayList<>();
    private List<NewHomeData.OrdersTimesTopList> orderList = new ArrayList<>();

    @OnClick({R.id.tv_day, R.id.tv_month, R.id.tv_sink, R.id.tv_month_sink, R.id.tv_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_team:
                Intent intent = new Intent(getContext(), V3TeamMemberActivity.class);
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

        if (view.getId() == R.id.tv_sink) {
            Intent intent = new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("isDay", true);
            startActivity(intent);
        }
        if (view.getId() == R.id.tv_month_sink) {
            Intent intent = new Intent(getContext(), StatisticsListActivity.class);
            intent.putExtra("isDay", false);
            startActivity(intent);
        }

        switch (view.getId()) {
            case R.id.ll_day_register:
                startStatisticsActivity(true, 0);
                break;
            case R.id.ll_day_open:
                startStatisticsActivity(true, 1);
                break;
            case R.id.ll_day_active:
                startStatisticsActivity(true, 2);
                break;
            case R.id.ll_day_call:
                startStatisticsActivity(true, 3);
                break;
        }

        switch (view.getId()) {
            case R.id.ll_month_register:
                startStatisticsActivity(false, 0);
                break;
            case R.id.ll_month_open:
                startStatisticsActivity(false, 1);
                break;
            case R.id.ll_month_active:
                startStatisticsActivity(false, 2);
                break;
            case R.id.ll_month_call:
                startStatisticsActivity(false, 3);
                break;
        }
    }

    private void startStatisticsActivity(boolean isDay, int position) {
        Intent intent = new Intent(getContext(), StatisticsActivity.class);
        intent.putExtra("isDay", isDay);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.net_index_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("map is " + getArgs());

        if (grade == 3) tv_heard.setVisibility(View.GONE);

        //初始化刷新布局
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (grade == 3) {
            ViewGroup.LayoutParams layoutParams = swipeRefreshLayout.getLayoutParams();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350f, getResources().getDisplayMetrics());
            swipeRefreshLayout.setLayoutParams(layoutParams);
        }

        //初始化顶部UI
        tv_username.setText("你好，" + UserUtils.getInstance().getLoginBean().getMakerName() + "!");
        tv_time.setText(GeneralUtils.getCurrDay() + "\t\t" + GeneralUtils.getWeekDay(System.currentTimeMillis()));


        //初始化日月统计
        netDayAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(grade == 3 ? this : null)
                .setResource(grade == 3 ? R.layout.net_day_preview_db_item : R.layout.net_day_preview_item)
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_active, R.id.ll_day_call)
                .setInitView((data, convertView) -> {
                    System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);

                    ((TextView) convertView.findViewById(R.id.dayRegisterTimes)).setText("" + data.getTodayData().dayRegisterTimes);
                    ((TextView) convertView.findViewById(R.id.firstOrderNum)).setText("" + data.getTodayData().firstOrderNum);
                    ((TextView) convertView.findViewById(R.id.activeNum)).setText("" + data.getTodayData().activeNum);
                    ((TextView) convertView.findViewById(R.id.callNum)).setText("" + data.getTodayData().callNum);
                    ((TextView) convertView.findViewById(R.id.todayAmount)).setText("" + GeneralUtils.round2(data.getTodayData().todayAmount));
                    ((TextView) convertView.findViewById(R.id.averageAmount)).setText("" + GeneralUtils.round2(data.getTodayData().averageAmount));
                    ((TextView) convertView.findViewById(R.id.afterSaleAmount)).setText("" + GeneralUtils.round2(data.getTodayData().afterSaleAmount));

                    int n = data.getTodayData().yesterdayActiveNum;
                    TextView textView;
                    if (grade == 3) {//DB
                        textView = convertView.findViewById(R.id.dayRegister);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.firstOrder);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.active);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.call);
                        textView.setText(textView.getText() + ">");

                        ((TextView) convertView.findViewById(R.id.yesterdayActiveNum)).setText(Html.fromHtml(n == 0 ? n + "" : n > 0 ? "+" + n + "<font color='red'>↑</font>" : n + "<font color='green'>↓</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

                    } else {
                        ((TextView) convertView.findViewById(R.id.yesterdayActiveNum)).setText(Html.fromHtml(n == 0 ? n + "" : n < 0 ? n + "<font color='green'>↓</font>" : "+" + n + "<font color='red'>↑</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                        ((TextView) convertView.findViewById(R.id.todayAfterSaleTimes)).setText("" + data.getTodayData().todayAfterSaleTimes);
                    }

                })
                .build(getContext());
        day_listView.setAdapter(netDayAdapter);
        day_listView.setNestedScrollingEnabled(true);


        netMonthAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(grade == 3 ? this : null)
                .setResource(grade == 3 ? R.layout.net_month_preview_db_item : R.layout.net_month_preview_item)
                .addChildId(R.id.ll_month_register, R.id.ll_month_open, R.id.ll_month_active, R.id.ll_month_call)
                .setInitView((data, convertView) -> {
                    System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);

                    ((TextView) convertView.findViewById(R.id.monthRegisterNum)).setText("" + data.getMonthData().monthRegisterNum);
                    ((TextView) convertView.findViewById(R.id.monthFirstOrderNum)).setText("" + data.getMonthData().monthFirstOrderNum);
                    ((TextView) convertView.findViewById(R.id.monthActiveNum)).setText("" + data.getMonthData().monthActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthAvgActiveNum)).setText("" + data.getMonthData().monthAvgActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthCallNum)).setText("" + data.getMonthData().monthCallNum);
                    ((TextView) convertView.findViewById(R.id.monthAmount)).setText("" + GeneralUtils.round2(data.getMonthData().monthAmount));
                    ((TextView) convertView.findViewById(R.id.monthAverageAmount)).setText("" + GeneralUtils.round2(data.getMonthData().monthAverageAmount));
                    ((TextView) convertView.findViewById(R.id.monthAfterSaleAmount)).setText("" + GeneralUtils.round2(data.getMonthData().monthAfterSaleAmount));
                    String  n = GeneralUtils.round2(data.getMonthData().addMonthAmount);
                    ((TextView) convertView.findViewById(R.id.addMonthAmount)).setText(Html.fromHtml("<font color='red'>" + n + "</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

                    if (grade == 3) {
                        TextView textView;
                        textView = convertView.findViewById(R.id.monthRegister);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.monthFirstOrder);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.monthActive);
                        textView.setText(textView.getText() + ">");
                        textView = convertView.findViewById(R.id.monthCall);
                        textView.setText(textView.getText() + ">");

                    } else {
                        ((TextView) convertView.findViewById(R.id.monthAfterSaleTimes)).setText("" + data.getMonthData().monthAfterSaleTimes);
                    }

                })
                .build(getContext());
        month_listView.setAdapter(netMonthAdapter);
        month_listView.setNestedScrollingEnabled(true);


        //重点关注
        keyPointAdapter = new BaseQuickAdapter<V3HomeData.PartnerApproachDataBean, BaseViewHolder>(R.layout.key_point_item) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, V3HomeData.PartnerApproachDataBean data) {
                ImageLoad.loadImageViewLoding(data.getMenuPic(), baseViewHolder.getView(R.id.image));
                baseViewHolder.addOnClickListener(R.id.ll_keyPoint);
                baseViewHolder.setText(R.id.title, "" + data.getTitle());

                if (!data.getSubscriptNum().isEmpty() && !data.getSubscriptNum().equals("0")) {
                    baseViewHolder.getView(R.id.count).setVisibility(View.VISIBLE);
                    baseViewHolder.setText(R.id.count, data.getSubscriptNum());
                } else {
                    baseViewHolder.getView(R.id.count).setVisibility(View.GONE);
                }

                ViewGroup.LayoutParams layoutParams = baseViewHolder.itemView.getLayoutParams();
                layoutParams.width = keyPoint_rv.getWidth() / keyPointAdapter.getData().size();
                baseViewHolder.itemView.setLayoutParams(layoutParams);
            }
        };
        keyPointAdapter.setOnItemChildClickListener(this);
        keyPoint_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        keyPoint_rv.setAdapter(keyPointAdapter);


        //排行榜
        tv_day.setOnClickListener(this);
        tv_month.setOnClickListener(this);
        isDay = (boolean) getArgs().get("isDay");
        MediatorLiveData<Integer> liveData = new MediatorLiveData<>();
        liveData.observe(this, this::updateLayout);
        ranking_vp.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public android.support.v4.app.Fragment getItem(int i) {
                Map<String, Object> map = new HashMap<>();
                map.put("type", i + 1);
                map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
                map.put("userType", UserUtils.getInstance().getLoginBean().getType());
                map.put("timeType", isDay ? "day" : "month");

                Bundle bundle = new Bundle();
                bundle.putSerializable("args", (Serializable) map);

                NetRankingFragment fragment = new NetRankingFragment();
                fragment.setLiveData(liveData);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                if (isDay) {
                    titles = Arrays.asList("GMV", "注册数", "新开");
                } else {
                    titles = Arrays.asList("月活", "月均日活", "月GMV");
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
        swipeRefreshLayout.setRefreshing(true);
        getVMWithFragment().loadingWithNewLiveData(V3HomeData.class, getArgs())
                .observe(this, v3HomeData -> {
                    stop();
                    if (!ListUtils.isEmpty(registerlist)) {
                        registerlist.clear();
                    }
                    if (!ListUtils.isEmpty(orderList)) {
                        orderList.clear();
                    }

                    //日统计
                    tv_day_sink.setText(v3HomeData.getTodayData().gradeNextName.isEmpty() ? "" : v3HomeData.getTodayData().gradeNextName + " >");
                    netDayAdapter.clear();
                    netDayAdapter.add(v3HomeData);

                    //月统计
                    tv_month_sink.setText(v3HomeData.getMonthData().gradeNextName.isEmpty() ? "" : v3HomeData.getMonthData().gradeNextName + " >");
                    netMonthAdapter.clear();
                    netMonthAdapter.add(v3HomeData);

                    //重点关注
                    keyPointAdapter.setNewData(v3HomeData.getPartnerApproachData());
                    keyPointAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("NetIndexFragment.onResume");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(true);
        Map<String, Object> map = new ArrayMap<>(5);
        map.put("api", "customer_refreshInfo");
        map.put("site", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("entityId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("loginUserId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("flag", 1);
        map.put("type", "partner");

        getVMWithFragment().loadingWithNewLiveData(UserInfo.class, map)
                .observe(this, userInfo -> {
                    System.out.println("userInfo = " + userInfo);
                    if (userInfo != null) {
                        UserUtils.getInstance().update(userInfo);
                        tv_username.setText("你好，" + UserUtils.getInstance().getLoginBean().getMakerName() + "!");
                        grade = UserUtils.getInstance().getLoginBean().getGrade();

                        //刷新数据
                        Map<String, Object> hashMap = new HashMap<>();
                        hashMap.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
                        hashMap.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
                        hashMap.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
                        hashMap.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());

                        getVMWithFragment().loading(V3HomeData.class, hashMap);
                        ranking_vp.getAdapter().notifyDataSetChanged();
                    }
                });
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
        if (grade == 3) {
            switch (((V3HomeData.PartnerApproachDataBean) baseQuickAdapter.getItem(i)).getSort()) {
                case 1:
                    getActivity().startActivity(new Intent(getContext(), DropOutingActivity.class));
                    break;
                case 2:
                    getActivity().startActivity(new Intent(getContext(), CommonFollowUpActivity.class));
                    break;
                case 3:
                    getActivity().startActivity(new Intent(getContext(), NetSupportActivity.class));
                    break;
            }
        } else {
            switch (((V3HomeData.PartnerApproachDataBean) baseQuickAdapter.getItem(i)).getSort()) {
                case 1:
                    if (grade != 3) GeneralUtils.showToastshort("暂未开放此功能！");
                    break;
                case 2:
                    getActivity().startActivity(new Intent(getContext(), ApprovalActivity.class));
                    break;
            }
        }
    }

    public void updateLayout(Integer integer) {
        System.out.println("integer = " + integer);
        ViewGroup.LayoutParams layoutParams = ranking_vp.getLayoutParams();
        layoutParams.height = Math.min(GeneralUtils.dipToPx(getContext(), 350), integer + 150);
        ranking_vp.setLayoutParams(layoutParams);
    }
}
