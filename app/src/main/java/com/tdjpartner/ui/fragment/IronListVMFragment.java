package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamMemberHorizontalAdapter;
import com.tdjpartner.base.VMFragment;
import com.tdjpartner.model.IronDayAndMonthData;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.model.NewMyTeam;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.viewmodel.AfterSalesViewModel;
import com.tdjpartner.viewmodel.DayAndMonthViewModel;
import com.tdjpartner.viewmodel.StatisticsDetailsViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/4/12.
 */
public class IronListVMFragment extends VMFragment<IronDayAndMonthData, DayAndMonthViewModel> implements View.OnClickListener {

    Random random = new Random();

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.member_list)
    ListView member_list;
    @BindView(R.id.ll_header_include)
    LinearLayout ll_header_include;
    @BindView(R.id.other_include)
    LinearLayout other_include;
    @BindView(R.id.tv_day_sink)
    TextView tv_day_sink;
    @BindView(R.id.btn_back)
    ImageView btn_back;


    IronAdapter ironAdapter;
    boolean isDay;
    String tag;
    private Calendar selectedDate, endDate, startDate;

    private TimePickerView pvTime;
    private Date date;
    private int userId;

    private TeamMemberAdapter teamMemberAdapter;
    private List<NewMyTeam> data = new ArrayList<>();
    private List<String> horizontal_data = new ArrayList<>();
    private List<String> horizontal_data_temp = new ArrayList<>();
    private Map<String, List<NewMyTeam>> myTeamMap = new HashMap<>();
    private TeamMemberHorizontalAdapter teamMemberHorizontalAdapter;


    @OnClick({R.id.tv_time, R.id.tv_day_sink, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                setTime(isDay);
                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }

        if (view.getId() == R.id.tv_day_sink) {
            System.out.println(view);

            leak(true, ((TextView)view).getText().toString());


//            IronListVMFragment ironListVMFragment = new IronListVMFragment();
//
//            getFragmentManager().beginTransaction()
//                    .add()
//                    .addToBackStack("")
//                    .commit();
        }

        if (view.getId() == R.id.ll_day_register ||
                view.getId() == R.id.ll_day_open ||
                view.getId() == R.id.ll_day_vegetables) {
            System.out.println(view);
        }

    }

    private void leak(boolean isNext, String tag) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) getArges(new Date(), isDay, isNext));
        IronListVMFragment fragment = new IronListVMFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fl, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void setTime(boolean hasDay) {
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(GeneralUtils.getTimes(date));
                IronListVMFragment.this.date = date;
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
    protected DayAndMonthViewModel setVM() {
        return ViewModelProviders.of(this).get(DayAndMonthViewModel.class);
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
        ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));


        //其他
        ((TextView) other_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));


        //列表
        ironAdapter = new IronAdapter.Builder()
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_vegetables, R.id.ll_day_gmv, R.id.ll_day_price, R.id.tv_day_sink)
                .setOnClickListener(this)
                .setResource(R.layout.iron_day_list_member_layout)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.get(1));
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.get(2));
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(data.get(3));
                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.get(4));
                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.get(5) + "：" + data.get(6));
                    ((TextView) convertView.findViewById(R.id.tv_day_sink)).setText(data.get(7).equals("BD") ? data.get(7) : data.get(7) + " >");
                })
                .build(getContext());

        member_list.setAdapter(ironAdapter);
        member_list.setNestedScrollingEnabled(true);
    }

    @Override
    public void onDestroyView() {
        System.out.println("~~" + getClass().getSimpleName() + ".onDestroyView~~");
        System.out.println("getBackStackEntryCount is " + getFragmentManager().getBackStackEntryCount());
        System.out.println("Id -1 is " + getFragmentManager().findFragmentById(getFragmentManager().getBackStackEntryCount()-1));
        System.out.println("getBackStackEntryCount is " + getFragmentManager().findFragmentByTag("M2"));

        super.onDestroyView();
    }

    @Override
    protected void updateView(IronDayAndMonthData ironDayAndMonthData) {
        System.out.println("~~" + getClass().getSimpleName() + ".loadedData~~");
        System.out.println("ironDayAndMonthData = " + ironDayAndMonthData);

        tv_title.setText("M2" + tv_title.getText());


        //头部统计
        ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("" + ironDayAndMonthData.registerNum);
        ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("" + ironDayAndMonthData.firstOrderNum);
        ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("" + ironDayAndMonthData.categoryNum);
        ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("" + ironDayAndMonthData.amount);
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + ironDayAndMonthData.averageAmount);


        ironDayAndMonthData.registerNum = 5;
        ironDayAndMonthData.firstOrderNum = 5;
        ironDayAndMonthData.categoryNum = 5;
        ironDayAndMonthData.amount = 5;
        ironDayAndMonthData.averageAmount = 5;

        System.out.println("~~~~~~~~~~~~~~~~~" + ironDayAndMonthData.teamViewList);
        List<IronDayAndMonthData.TeamView> tl = new ArrayList<>();
        IronDayAndMonthData.TeamView teamView1 = new IronDayAndMonthData.TeamView();
        teamView1.registerNum = 51;
        teamView1.firstOrderNum = 52;
        teamView1.categoryNum = 53;
        teamView1.amount = 54;
        teamView1.averageAmount = 55;
        teamView1.gradeName = "56";
        teamView1.nickName = "67";
        teamView1.gradeChineseName = "M2";
        tl.add(teamView1);
        ironDayAndMonthData.teamViewList = tl;

        ironAdapter.clear();
        for (IronDayAndMonthData.TeamView teamView : ironDayAndMonthData.teamViewList) {
            ironAdapter.add(Arrays.asList("" + teamView.registerNum,
                    "" + teamView.firstOrderNum,
                    "" + teamView.categoryNum,
                    "" + teamView.amount,
                    "" + teamView.averageAmount,
                    "" + teamView.gradeName,
                    "" + teamView.nickName,
                    "" + teamView.gradeChineseName
            ));
        }
    }

    private void refresh() {
        Map<String, Object> map = getArgs();
        map.put("type", "next");
        if (isDay) {
            map.put("dayDate", GeneralUtils.getTimeFilter(date));
            map.put("timeType", "day");
        } else {
            map.put("monthTime", GeneralUtils.getMonthFilter(date));
            map.put("timeType", "month");
        }

        ViewModelProviders.of(this)
                .get(DayAndMonthViewModel.class)
                .loading(map);
    }


    private Map<String, Object> getArges(Date date, boolean isDay, boolean isNext) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("type", isNext ? "next" : "up");
        if (isDay) {
            map.put("startTime", GeneralUtils.getTimeFilter(date));
            map.put("timeType", "day");
        } else {
            map.put("startTime", GeneralUtils.getMonthFilter(date));
            map.put("timeType", "month");
        }
        return map;
    }
}