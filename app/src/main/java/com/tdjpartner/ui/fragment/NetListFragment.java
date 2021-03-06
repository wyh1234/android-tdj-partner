package com.tdjpartner.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.DayAndMonthData;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by LFM on 2021/4/12.
 */
public class NetListFragment extends NetworkFragment implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.member_list)
    RecyclerView member_list;
//    @BindView(R.id.ll_header_include)
//    LinearLayout ll_header_include;
    @BindView(R.id.viewStub)
    ViewStub viewStub;
    @BindView(R.id.btn_back)
    ImageView btn_back;


    private BaseQuickAdapter adapter;
    boolean isDay;
    String tag;
    private Calendar selectedDate, endDate, startDate;

    private TimePickerView pvTime;
    private Date date;

    int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户类型

    @OnClick({R.id.tv_time, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                setTime(isDay);
                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }
    }

    public void setTime(boolean hasDay) {
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(isDay ? GeneralUtils.getTimes(date) : GeneralUtils.getTime(date));
                NetListFragment.this.date = date;
                refresh();
            }
        }).setType(new boolean[]{true, true, hasDay, false, false, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", hasDay ? "日" : null, null, null, null)
                .isCenterLabel(true)
                .setLineSpacingMultiplier(1.8f)
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
        pvTime.show();
    }

    @Override
    protected int getLayoutId() {
        isDay = getArguments().getBoolean("isDay", true);
        return isDay ? R.layout.net_day_list_fragment : R.layout.net_month_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithActivity().loadingWithNewLiveData(DayAndMonthData.class, getArgs())
                .observe(this, dayAndMonthData -> {
                    tv_title.setText(dayAndMonthData.headGrade);
                    //头部统计
                    if (isDay) {
                        ((TextView) getView().findViewById(R.id.dayRegisterTimes)).setText("" + dayAndMonthData.teamView.registerNum);
                        ((TextView) getView().findViewById(R.id.firstOrderNum)).setText("" + dayAndMonthData.teamView.firstOrderNum);
                        ((TextView) getView().findViewById(R.id.activeNum)).setText("" + dayAndMonthData.teamView.activeNum);
                        int n = dayAndMonthData.teamView.yesterdayActiveNum;
                        ((TextView) getView().findViewById(R.id.yesterdayActiveNum)).setText(Html.fromHtml(n == 0 ? n + "" : n > 0 ? "+" + n + "<font color='red'>↑</font>" : n + "<font color='green'>↓</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                        ((TextView) getView().findViewById(R.id.callNum)).setText("" + dayAndMonthData.teamView.callNum);
                        ((TextView) getView().findViewById(R.id.todayAmount)).setText("" + dayAndMonthData.teamView.amount);
                        ((TextView) getView().findViewById(R.id.averageAmount)).setText("" + dayAndMonthData.teamView.averageAmount);
                        ((TextView) getView().findViewById(R.id.todayAfterSaleTimes)).setText("" + dayAndMonthData.teamView.afterSaleTimes);
                        ((TextView) getView().findViewById(R.id.afterSaleAmount)).setText("" + dayAndMonthData.teamView.afterSaleAmount);
                    } else {
                        ((TextView) getView().findViewById(R.id.monthRegisterNum)).setText("" + dayAndMonthData.teamView.registerNum);
                        ((TextView) getView().findViewById(R.id.monthFirstOrderNum)).setText("" + dayAndMonthData.teamView.firstOrderNum);
                        ((TextView) getView().findViewById(R.id.monthActiveNum)).setText("" + dayAndMonthData.teamView.activeNum);
                        ((TextView) getView().findViewById(R.id.monthAvgActiveNum)).setText("" + dayAndMonthData.teamView.monthAvgActiveNum);
                        ((TextView) getView().findViewById(R.id.monthCallNum)).setText("" + dayAndMonthData.teamView.callNum);
                        ((TextView) getView().findViewById(R.id.monthAmount)).setText("" + dayAndMonthData.teamView.amount);
                        ((TextView) getView().findViewById(R.id.monthAverageAmount)).setText("" + dayAndMonthData.teamView.averageAmount);
                        ((TextView) getView().findViewById(R.id.monthAfterSaleTimes)).setText("" + dayAndMonthData.teamView.afterSaleTimes);
                        ((TextView) getView().findViewById(R.id.monthAfterSaleAmount)).setText("" + dayAndMonthData.teamView.afterSaleAmount);
                        float n = dayAndMonthData.teamView.addMonthAmount;
                        ((TextView) getView().findViewById(R.id.addMonthAmount)).setText(Html.fromHtml("<font color='red'>" + n + "</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

                    }
                    List<DayAndMonthData.TeamView> list = new ArrayList<>(dayAndMonthData.teamViewList);
                    if (dayAndMonthData.othersTeamView != null)
                        list.add(dayAndMonthData.othersTeamView);
                    adapter.setNewData(list);
                    dismissLoading();
                });
        showLoading();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(isDay ? grade == 3 ? R.layout.net_day_preview_db_item : R.layout.net_day_preview_item : grade == 3 ? R.layout.net_month_preview_db_item : R.layout.net_month_preview_item);
        viewStub.inflate();

        tv_time.setText(isDay ? GeneralUtils.getTimes(new Date()) : GeneralUtils.getTime(new Date()));

        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));


        if (isDay) {
            //头部统计
            ((TextView) view.findViewById(R.id.dayRegisterTimes)).setText("N");
            ((TextView) view.findViewById(R.id.firstOrderNum)).setText("N");
            ((TextView) view.findViewById(R.id.activeNum)).setText("N");
            ((TextView) view.findViewById(R.id.yesterdayActiveNum)).setText("N");
            ((TextView) view.findViewById(R.id.callNum)).setText("N");
            ((TextView) view.findViewById(R.id.todayAmount)).setText("N");
            ((TextView) view.findViewById(R.id.averageAmount)).setText("N");
            ((TextView) view.findViewById(R.id.todayAfterSaleTimes)).setText("N");
            ((TextView) view.findViewById(R.id.afterSaleAmount)).setText("N");

            //列表
            adapter = new BaseQuickAdapter<DayAndMonthData.TeamView, BaseViewHolder>(R.layout.net_day_list_member_layout) {
                @Override
                protected void convert(BaseViewHolder baseViewHolder, DayAndMonthData.TeamView teamView) {
                    System.out.println("baseViewHolder = " + baseViewHolder + ", teamView = " + teamView);
                    baseViewHolder.addOnClickListener(R.id.tv_day_sink);

                    baseViewHolder.setText(R.id.dayRegisterTimes, "" + teamView.registerNum)
                            .setText(R.id.firstOrderNum, "" + teamView.firstOrderNum)
                            .setText(R.id.activeNum, "" + teamView.activeNum)
                            .setText(R.id.callNum, "" + teamView.callNum)
                            .setText(R.id.todayAmount, "" + teamView.amount)
                            .setText(R.id.averageAmount, "" + teamView.averageAmount)
                            .setText(R.id.todayAfterSaleTimes, "" + teamView.afterSaleTimes)
                            .setText(R.id.afterSaleAmount, "" + teamView.afterSaleAmount)
                            .setText(R.id.tv_name, TextUtils.isEmpty(teamView.gradeChineseName) ? "其他" : ("" + teamView.gradeChineseName + "：" + teamView.nickName))
                            .setText(R.id.tv_day_sink, TextUtils.isEmpty(teamView.gradeName) ? "" : teamView.gradeName + (teamView.gradeName.equals("BD") ? "" : " >"));

                    int n = teamView.yesterdayActiveNum;
                    ((TextView) baseViewHolder.getView(R.id.yesterdayActiveNum)).setText(Html.fromHtml(n == 0 ? n + "" : n > 0 ? "+" + n + "<font color='red'>↑</font>" : n + "<font color='green'>↓</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

                }
            };
        } else {
            //头部统计
            ((TextView) view.findViewById(R.id.monthRegisterNum)).setText("N");
            ((TextView) view.findViewById(R.id.monthFirstOrderNum)).setText("N");
            ((TextView) view.findViewById(R.id.monthActiveNum)).setText("N");
            ((TextView) view.findViewById(R.id.monthAvgActiveNum)).setText("N");
            ((TextView) view.findViewById(R.id.monthCallNum)).setText("N");
            ((TextView) view.findViewById(R.id.monthAmount)).setText("N");
            ((TextView) view.findViewById(R.id.addMonthAmount)).setText("N");
            ((TextView) view.findViewById(R.id.monthAverageAmount)).setText("N");
            ((TextView) view.findViewById(R.id.monthAfterSaleTimes)).setText("N");
            ((TextView) view.findViewById(R.id.monthAfterSaleAmount)).setText("N");

            //列表
            adapter = new BaseQuickAdapter<DayAndMonthData.TeamView, BaseViewHolder>(R.layout.net_month_list_member_layout) {
                @Override
                protected void convert(BaseViewHolder baseViewHolder, DayAndMonthData.TeamView teamView) {

                    baseViewHolder.addOnClickListener(R.id.tv_month_sink);
                    baseViewHolder.setText(R.id.monthRegisterNum, "" + teamView.registerNum)
                            .setText(R.id.monthFirstOrderNum, "" + teamView.firstOrderNum)
                            .setText(R.id.monthActiveNum, "" + teamView.activeNum)
                            .setText(R.id.monthAvgActiveNum, "" + teamView.monthAvgActiveNum)
                            .setText(R.id.monthCallNum, "" + teamView.callNum)
                            .setText(R.id.monthAmount, "" + teamView.amount)
                            .setText(R.id.monthAverageAmount, "" + teamView.averageAmount)
                            .setText(R.id.monthAfterSaleTimes, "" + teamView.afterSaleTimes)
                            .setText(R.id.monthAfterSaleAmount, "" + teamView.afterSaleAmount)
                            .setText(R.id.tv_name, TextUtils.isEmpty(teamView.gradeChineseName) ? "其他" : ("" + teamView.gradeChineseName + "：" + teamView.nickName))
                            .setText(R.id.tv_month_sink, TextUtils.isEmpty(teamView.gradeName) ? "" : teamView.gradeName + (teamView.gradeName.equals("BD") ? "" : " >"));

                    float n = teamView.addMonthAmount;
                    ((TextView) baseViewHolder.getView(R.id.addMonthAmount)).setText(Html.fromHtml("<font color='red'>" + n + "</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

                }
            };
        }

        //列表
        adapter.setOnItemChildClickListener(this);
        member_list.setLayoutManager(new LinearLayoutManager(getContext()));
        member_list.setAdapter(adapter);

    }

    private void refresh() {
        Map<String, Object> map = getArgs();
        System.out.println("map = " + map);

        if (isDay) {
            map.put("startTime", GeneralUtils.getTimeFilter(date));
            map.put("timeType", "day");
        } else {
            map.put("startTime", GeneralUtils.getMonthFilter(date));
            map.put("timeType", "month");
        }

        getVMWithActivity().loading(DayAndMonthData.class, getArgs());
    }

    private Map<String, Object> makeArges(int userId, int grade, Date date, boolean isDay, boolean isNext) {
        Map<String, Object> map = new HashMap<>();
        map.put("loginId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("userId", userId);
        map.put("grade", grade);
        map.put("type", isNext ? "next" : "up");
        if (isDay) {
            map.put("startTime", GeneralUtils.getTimeFilter(date));
            map.put("timeType", "day");
        } else {
            map.put("startTime", GeneralUtils.getMonthFilter(date));
            map.put("timeType", "month");
        }
        System.out.println("map = " + map);
        return map;
    }

    private void leak(int userId, int grade, boolean isNext) {
        Bundle bundle = new Bundle();
        Map<String, Object> map = makeArges(userId, grade, new Date(), isDay, isNext);
        bundle.putSerializable("args", (Serializable) map);
        bundle.putBoolean("isDay", isDay);
        NetListFragment fragment = new NetListFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fl, fragment)
                .addToBackStack(tag += userId + "")
                .commit();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        System.out.println("~~" + getClass().getSimpleName() + ".onItemClick~~");
        System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);

        DayAndMonthData.TeamView teamView;
        switch (view.getId()) {
            case R.id.tv_day_sink:
                teamView = (DayAndMonthData.TeamView) baseQuickAdapter.getItem(i);
                if (TextUtils.isEmpty(teamView.gradeName)) return;
                leak(teamView.partnerId, teamView.grade, true);
                break;
            case R.id.tv_month_sink:
                teamView = (DayAndMonthData.TeamView) baseQuickAdapter.getItem(i);
                if (TextUtils.isEmpty(teamView.gradeName)) return;
                leak(teamView.partnerId, teamView.grade, true);
                break;
        }
    }

}