package com.tdjpartner.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.IronDayAndMonthData;
import com.tdjpartner.utils.GeneralUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(isDay?GeneralUtils.getTimes(date):GeneralUtils.getTime(date));
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
        return R.layout.iron_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithActivity().loadingWithNewLiveData(IronDayAndMonthData.class, getArgs())
                .observe(this, ironDayAndMonthData -> {
                    tv_title.setText(ironDayAndMonthData.teamView.gradeChineseName + (isDay ? "日" : "月") + "统计");
                    //头部统计
                    ((TextView) ll_header_include.findViewById(R.id.callNum)).setText("" + ironDayAndMonthData.teamView.registerNum);
                    ((TextView) ll_header_include.findViewById(R.id.firstOrderNum)).setText("" + ironDayAndMonthData.teamView.firstOrderNum);
                    ((TextView) ll_header_include.findViewById(R.id.activeNum)).setText("" + ironDayAndMonthData.teamView.categoryNum);
                    ((TextView) ll_header_include.findViewById(R.id.yesterdayActiveNum)).setText("" + ironDayAndMonthData.teamView.amount);
                    ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + ironDayAndMonthData.teamView.averageAmount);

                    adapter.setNewData(ironDayAndMonthData.teamView.teamViewList);
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
        ((TextView) ll_header_include.findViewById(R.id.callNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.firstOrderNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.activeNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.yesterdayActiveNum)).setText("N");
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("N");


        //列表
        adapter = new BaseQuickAdapter<IronDayAndMonthData.TeamView, BaseViewHolder>(R.layout.iron_day_list_member_layout) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, IronDayAndMonthData.TeamView teamView) {
                System.out.println("~~" + getClass().getSimpleName() + ".convert~~");
                System.out.println("baseViewHolder = " + baseViewHolder + ", teamView= " + teamView);

                baseViewHolder.addOnClickListener(R.id.tv_day_sink);

                baseViewHolder.setText(R.id.callNum, "" + teamView.registerNum)
                        .setText(R.id.firstOrderNum, "" + teamView.firstOrderNum)
                        .setText(R.id.activeNum, "" + teamView.categoryNum)
                        .setText(R.id.yesterdayActiveNum, "" + teamView.amount)
                        .setText(R.id.priceNum, "" + teamView.averageAmount)
                        .setText(R.id.tv_name, "" + teamView.gradeName + "：" + teamView.nickName)
                        .setText(R.id.tv_day_sink, "" + teamView.gradeChineseName + (teamView.gradeChineseName.equals("BD") ? "" : " >"));
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

        getVMWithActivity().loading(IronDayAndMonthData.class, getArgs());
    }

    private Map<String, Object> makeArges(int userId, Date date, boolean isDay, boolean isNext) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
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

    private void leak(int userId, boolean isNext) {
        Bundle bundle = new Bundle();
        Map<String, Object> map = makeArges(userId, new Date(), isDay, isNext);
        bundle.putSerializable("args", (Serializable) map);
        bundle.putBoolean("isDay", isDay);
        IronListFragment fragment = new IronListFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fl, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        System.out.println("~~" + getClass().getSimpleName() + ".onItemClick~~");
        System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);


        switch (view.getId()) {
            case R.id.tv_day_sink:
                IronDayAndMonthData.TeamView teamView = (IronDayAndMonthData.TeamView) baseQuickAdapter.getItem(i);
                if (teamView.gradeChineseName.equals("BD")) return;
                leak(teamView.parentId, true);
                break;
        }
    }


}