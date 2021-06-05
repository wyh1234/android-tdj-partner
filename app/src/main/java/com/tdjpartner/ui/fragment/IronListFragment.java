package com.tdjpartner.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
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

/**
 * Created by LFM on 2021/4/12.
 */
public class IronListFragment extends NetworkFragment implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.member_list)
    RecyclerView member_list;
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

    @OnClick({R.id.tv_time, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                setTime(isDay);
                break;
            case R.id.btn_back:
                getFragmentManager().popBackStack();
                if (getFragmentManager().getBackStackEntryCount() == 0) getActivity().finish();
                break;
        }

        if (view.getId() == R.id.ll_day_register ||
                view.getId() == R.id.ll_day_open ||
                view.getId() == R.id.ll_day_vegetables) {
            System.out.println(view);
        }
    }

    public void setTime(boolean hasDay) {
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(isDay ? GeneralUtils.getTimes(date) : GeneralUtils.getTime(date));
                IronListFragment.this.date = date;
                refresh();
            }
        }).setType(new boolean[]{true, true, hasDay, false, false, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", hasDay ? "日" : null, null, null, null)
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
    protected int getLayoutId() {
        isDay = getArguments().getBoolean("isDay", true);
        return R.layout.iron_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithActivity().loadingWithNewLiveData(DayAndMonthData.class, getArgs())
                .observe(this, dayAndMonthData -> {
                    tv_title.setText(dayAndMonthData.headGrade);
                    //头部统计
                    ((TextView) getView().findViewById(R.id.registerNum)).setText("" + dayAndMonthData.teamView.registerNum);
                    ((TextView) getView().findViewById(R.id.openNum)).setText("" + dayAndMonthData.teamView.firstOrderNum);
                    ((TextView) getView().findViewById(R.id.vegetablesNum)).setText("" + dayAndMonthData.teamView.categoryNum);
                    ((TextView) getView().findViewById(R.id.gmvNum)).setText("" + dayAndMonthData.teamView.amount);
                    if (isDay)
                        ((TextView) getView().findViewById(R.id.priceNum)).setText("" + dayAndMonthData.teamView.averageAmount);

                    List<DayAndMonthData.TeamView> list = new ArrayList<>();
                    if (dayAndMonthData.teamViewList != null) list.addAll(dayAndMonthData.teamViewList);
                    if (dayAndMonthData.othersTeamView != null) list.add(dayAndMonthData.othersTeamView);
                    adapter.setNewData(list);
                    dismissLoading();
                });
        showLoading();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(isDay ? R.layout.iron_day_preview_item : R.layout.iron_month_preview_item);
        viewStub.inflate();

        tv_time.setText(isDay ? GeneralUtils.getTimes(new Date()) : GeneralUtils.getTime(new Date()));

        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));


        //头部统计
        ((TextView) view.findViewById(R.id.registerNum)).setText("N");
        ((TextView) view.findViewById(R.id.openNum)).setText("N");
        ((TextView) view.findViewById(R.id.vegetablesNum)).setText("N");
        ((TextView) view.findViewById(R.id.gmvNum)).setText("N");
        if (isDay) ((TextView) view.findViewById(R.id.priceNum)).setText("N");


        //列表
        adapter = new BaseQuickAdapter<DayAndMonthData.TeamView, BaseViewHolder>(isDay ? R.layout.iron_day_list_member_layout : R.layout.iron_month_list_member_layout) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, DayAndMonthData.TeamView teamView) {
                System.out.println("~~" + getClass().getSimpleName() + ".convert~~");
                System.out.println("baseViewHolder = " + baseViewHolder + ", teamView= " + teamView);

                baseViewHolder.addOnClickListener(R.id.tv_sink);
                if (isDay) baseViewHolder.setText(R.id.priceNum, "" + teamView.averageAmount);
                baseViewHolder.setText(R.id.registerNum, "" + teamView.registerNum)
                        .setText(R.id.openNum, "" + teamView.firstOrderNum)
                        .setText(R.id.vegetablesNum, "" + teamView.categoryNum)
                        .setText(R.id.gmvNum, "" + teamView.amount)
                        .setText(R.id.tv_name, TextUtils.isEmpty(teamView.gradeChineseName) ? "其他" : ("" + teamView.gradeChineseName + "：" + teamView.nickName))
                        .setText(R.id.tv_sink, TextUtils.isEmpty(teamView.gradeName) ? "" : teamView.gradeName + (teamView.gradeName.equals("BD") ? "" : " >"));
            }
        };

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
        IronListFragment fragment = new IronListFragment();
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
            case R.id.tv_sink:
                teamView = (DayAndMonthData.TeamView) baseQuickAdapter.getItem(i);
                if (TextUtils.isEmpty(teamView.gradeName)) return;
                leak(teamView.partnerId, teamView.grade, true);
                break;
        }
    }
}