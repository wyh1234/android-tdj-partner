package com.tdjpartner.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @BindView(R.id.ll_header_include)
    LinearLayout ll_header_include;
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
                getActivity().finish();
                break;
        }

        if (view.getId() == R.id.ll_day_register ||
                view.getId() == R.id.ll_day_open ||
                view.getId() == R.id.ll_day_vegetables) {
            System.out.println(view);
        }
    }

    public void setTime(boolean hasDay) {
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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
                .setContentTextSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
        pvTime.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithActivity().loadingWithNewLiveData(DayAndMonthData.class, getArgs())
                .observe(this, dayAndMonthData -> {
                    tv_title.setText(dayAndMonthData.teamView.gradeChineseName + (isDay ? "日" : "月") + "统计");
                    //头部统计
                    ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("" + dayAndMonthData.teamView.registerNum);
                    ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("" + dayAndMonthData.teamView.firstOrderNum);
                    ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("" + dayAndMonthData.teamView.categoryNum);
                    ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("" + dayAndMonthData.teamView.amount);
                    ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + dayAndMonthData.teamView.averageAmount);

                    List<DayAndMonthData.TeamView> list = new ArrayList<>(dayAndMonthData.teamViewList);
                    list.add(dayAndMonthData.othersTeamView);
                    adapter.setNewData(list);
//                    adapter.setNewData(ironDayAndMonthData.teamViewList);
//                    adapter.setNewData(Stream.concat(ironDayAndMonthData.teamViewList.stream(), ironDayAndMonthData.othersTeamView.stream()).collect(Collectors.toList()));
//                    adapter.addData();
                    dismissLoading();
                });
        showLoading();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isDay = getArguments().getBoolean("isDay", true);
        tv_time.setText(isDay ? GeneralUtils.getTimes(new Date()) : GeneralUtils.getTime(new Date()));

        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));


        //头部统计
        ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("N");


        //列表
        adapter = new BaseQuickAdapter<DayAndMonthData.TeamView, BaseViewHolder>(R.layout.iron_day_list_member_layout) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, DayAndMonthData.TeamView teamView) {
                System.out.println("~~" + getClass().getSimpleName() + ".convert~~");
                System.out.println("baseViewHolder = " + baseViewHolder + ", teamView= " + teamView);

                baseViewHolder.addOnClickListener(R.id.tv_day_sink);

                baseViewHolder.setText(R.id.registerNum, "" + teamView.registerNum)
                        .setText(R.id.openNum, "" + teamView.firstOrderNum)
                        .setText(R.id.vegetablesNum, "" + teamView.categoryNum)
                        .setText(R.id.gmvNum, "" + teamView.amount)
                        .setText(R.id.priceNum, "" + teamView.averageAmount)
                        .setText(R.id.tv_name, "" + teamView.gradeName + "：" + teamView.nickName)
                        .setText(R.id.tv_day_sink, "" + teamView.gradeChineseName + ((!TextUtils.isEmpty(teamView.gradeChineseName) && teamView.gradeChineseName.equals("BD")) ? "" : " >"));
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


        switch (view.getId()) {
            case R.id.tv_day_sink:
                DayAndMonthData.TeamView teamView = (DayAndMonthData.TeamView) baseQuickAdapter.getItem(i);
                if (teamView.gradeChineseName.equals("BD")) return;
                leak(teamView.partnerId, teamView.grade, true);
                break;
        }
    }


}