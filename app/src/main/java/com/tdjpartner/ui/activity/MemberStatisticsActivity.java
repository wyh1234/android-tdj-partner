package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.V3HomeData;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class MemberStatisticsActivity extends NetworkActivity {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.day_listView)
    ListView day_listView;
    @BindView(R.id.tv_day_sink)
    TextView tv_day_sink;
    @BindView(R.id.tv_month_sink)
    TextView tv_month_sink;
    @BindView(R.id.member_list)
    ListView month_listView;


    private ListViewAdapter<V3HomeData> dayAdapter, monthAdapter;
    private List<NewHomeData.RegisterTimesTopListBean> registerlist = new ArrayList<>();
    private List<NewHomeData.OrdersTimesTopList> orderList = new ArrayList<>();

    int userId = UserUtils.getInstance().getLoginBean().getType();//用户类型;
    int grade = UserUtils.getInstance().getLoginBean().getGrade();
    int type = UserUtils.getInstance().getLoginBean().getType();//用户类型;

    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    public void onNetClick(View view) {

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

    public void onIronClick(View view) {

        switch (view.getId()) {
            case R.id.ll_day_register:
                startStatisticsActivity(true, 0);
                break;
            case R.id.ll_day_open:
                startStatisticsActivity(true, 1);
                break;
            case R.id.ll_day_vegetables:
                startStatisticsActivity(true, 2);
                break;
        }

        switch (view.getId()) {
            case R.id.ll_month_register:
                startStatisticsActivity(false, 0);
                break;
            case R.id.ll_month_open:
                startStatisticsActivity(false, 1);
                break;
            case R.id.ll_month_vegetables:
                startStatisticsActivity(false, 2);
                break;
        }
    }

    @Override
    protected void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);

        String name = getIntent().getStringExtra("nickName");
        tv_title.setText(Html.fromHtml("个人数据统计" + (TextUtils.isEmpty(name) ? "" : "<samll>（ " + name + " ）</small>"), FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);


        if (type == 1) {
            netInit();
        } else {
            ironInit();
        }
    }

    private void netInit() {
        dayAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(grade == 3 ? this::onNetClick : null)
                .setResource(grade == 3 ? R.layout.net_day_preview_db_item : R.layout.net_day_preview_item)
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_active, R.id.ll_day_call)
                .setInitView((data, convertView) -> {
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
                .build(this);
        day_listView.setAdapter(dayAdapter);


        monthAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(grade == 3 ? this::onNetClick : null)
                .setResource(grade == 3 ? R.layout.net_month_preview_db_item : R.layout.net_month_preview_item)
                .addChildId(R.id.ll_month_register, R.id.ll_month_open, R.id.ll_month_active, R.id.ll_month_call)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.monthRegisterNum)).setText("" + data.getMonthData().monthRegisterNum);
                    ((TextView) convertView.findViewById(R.id.monthFirstOrderNum)).setText("" + data.getMonthData().monthFirstOrderNum);
                    ((TextView) convertView.findViewById(R.id.monthActiveNum)).setText("" + data.getMonthData().monthActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthAvgActiveNum)).setText("" + data.getMonthData().monthAvgActiveNum);
                    ((TextView) convertView.findViewById(R.id.monthCallNum)).setText("" + data.getMonthData().monthCallNum);
                    ((TextView) convertView.findViewById(R.id.monthAmount)).setText(GeneralUtils.round2(data.getMonthData().monthAmount));
                    ((TextView) convertView.findViewById(R.id.monthAverageAmount)).setText(GeneralUtils.round2(data.getMonthData().monthAverageAmount));
                    ((TextView) convertView.findViewById(R.id.monthAfterSaleAmount)).setText(GeneralUtils.round2(data.getMonthData().monthAfterSaleAmount));
                    BigDecimal n = data.getMonthData().addMonthAmount;
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
                .build(this);
        month_listView.setAdapter(monthAdapter);
    }

    private void ironInit() {
        dayAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setResource(R.layout.iron_day_preview_item)
                .setOnClickListener(grade == 3 ? this::onIronClick : null)
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_vegetables, R.id.ll_day_gmv, R.id.ll_day_price)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.getTodayData().dayRegisterTimes + "");
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.getTodayData().firstOrderNum + "");
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.getTodayData().categoryNum + "");
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(GeneralUtils.round2(data.getTodayData().todayAmount));
                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(GeneralUtils.round2(data.getTodayData().averageAmount));

                    if (grade == 3) {
                        TextView textView;
                        textView = convertView.findViewById(R.id.register);
                        textView.setText(textView.getText() + " >");
                        textView = convertView.findViewById(R.id.open);
                        textView.setText(textView.getText() + " >");
                        textView = convertView.findViewById(R.id.vegetables);
                        textView.setText(textView.getText() + " >");
                    }
                })
                .build(this);
        day_listView.setAdapter(dayAdapter);

        monthAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setResource(R.layout.iron_month_preview_item)
                .setOnClickListener(grade == 3 ? this::onIronClick : null)
                .addChildId(R.id.ll_month_register, R.id.ll_month_open, R.id.ll_month_vegetables, R.id.ll_month_gmv)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.getMonthData().monthRegisterNum + "");
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.getMonthData().monthFirstOrderNum + "");
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.getMonthData().categoryNum + "");
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(GeneralUtils.round2(data.getMonthData().monthAmount));


                    if (grade == 3) {
                        TextView textView;
                        textView = convertView.findViewById(R.id.register);
                        textView.setText(textView.getText() + " >");
                        textView = convertView.findViewById(R.id.open);
                        textView.setText(textView.getText() + " >");
                        textView = convertView.findViewById(R.id.vegetables);
                        textView.setText(textView.getText() + " >");
                    }
                })
                .build(this);
        month_listView.setAdapter(monthAdapter);
    }

    @Override
    protected void initData() {

        //加载数据
        swipeRefreshLayout.setRefreshing(true);
        getVM().loadingWithNewLiveData(V3HomeData.class, getArgs())
                .observe(this, v3HomeData -> {
                    stop();
                    if (!ListUtils.isEmpty(registerlist)) {
                        registerlist.clear();
                    }
                    if (!ListUtils.isEmpty(orderList)) {
                        orderList.clear();
                    }

                    //日统计
//                    tv_day_sink.setText(v3HomeData.getTodayData().gradeNextName.isEmpty() ? "" : v3HomeData.getTodayData().gradeNextName + " >");
                    tv_day_sink.setVisibility(View.GONE);
                    dayAdapter.clear();
                    dayAdapter.add(v3HomeData);

                    //月统计
//                    tv_month_sink.setText(v3HomeData.getMonthData().gradeNextName.isEmpty() ? "" : v3HomeData.getMonthData().gradeNextName + " >");
                    tv_month_sink.setVisibility(View.GONE);
                    monthAdapter.clear();
                    monthAdapter.add(v3HomeData);
                });

    }

    public void onRefresh() {
        getVM().loading(V3HomeData.class, getArgs());
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private Map<String, Object> getArgs() {
        Map<String, Object> map = new ArrayMap<>();
        int n = getIntent().getIntExtra("userId", -1);
        if (n == -1) {
            GeneralUtils.showToastshort("用户不存在！");
            return map;
        }
        map.put("userId", n);
        map.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
        map.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        return map;
    }

    @Override
    protected int getLayoutId() {
        grade = getIntent().getIntExtra("grade", grade);
        userId = getIntent().getIntExtra("userId", userId);
        return R.layout.team_statistics_layout;
    }

    private void startStatisticsActivity(boolean isDay, int position) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("isDay", isDay);
        intent.putExtra("position", position);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
