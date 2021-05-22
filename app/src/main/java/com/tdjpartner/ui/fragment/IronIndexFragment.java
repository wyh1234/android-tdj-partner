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
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.V3HomeData;
import com.tdjpartner.ui.activity.ApprovalActivity;
import com.tdjpartner.ui.activity.CommonFollowUpActivity;
import com.tdjpartner.ui.activity.DropOutingActivity;
import com.tdjpartner.ui.activity.IronStatisticsActivity;
import com.tdjpartner.ui.activity.StatisticsListActivity;
import com.tdjpartner.ui.activity.TeamMemberActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/3.
 */
public class IronIndexFragment extends NetworkFragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.day_listView)
    ListView day_listView;
    @BindView(R.id.member_list)
    ListView month_listView;
    @BindView(R.id.keyPoint_rv)
    RecyclerView keyPoint_rv;

    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_team)
    TextView tv_team;
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
    @BindView(R.id.tv_day_sink)
    TextView tv_day_sink;
    @BindView(R.id.ranking_vp)
    ViewPager ranking_vp;
    @BindView(R.id.wtab)
    WTabLayout wtab;

    boolean isDay;//时间类型标记
    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型
    int site = UserUtils.getInstance().getLoginBean().getSite();//用户类型
    int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户类型

    private ListViewAdapter<V3HomeData> ironDayAdapter, ironMonthAdapter;
    private BaseQuickAdapter<V3HomeData.PartnerApproachDataBean, BaseViewHolder> keyPointAdapter;
    private List<NewHomeData.RegisterTimesTopListBean> registerlist = new ArrayList<>();
    private List<NewHomeData.OrdersTimesTopList> orderList = new ArrayList<>();
    List<String> titles;

    @OnClick({R.id.tv_team, R.id.tv_day, R.id.tv_month, R.id.tv_day_sink, R.id.tv_month_sink})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_team:
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
                ranking_vp.getAdapter().notifyDataSetChanged();
                break;

            case R.id.tv_day:
                view.setBackgroundResource(R.drawable.bg_orange_right_semi_4);
                ((TextView) view).setTextColor(Color.WHITE);
                tv_month.setBackgroundResource(R.drawable.bg_grey_left_semi_4);
                tv_month.setTextColor(Color.BLACK);
                isDay = true;
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

        if (grade == 3) {
            switch (view.getId()) {
                case R.id.ll_day_register:
                    startIronStatisticsActivity(true, 0);
                    break;
                case R.id.ll_day_open:
                    startIronStatisticsActivity(true, 1);
                    break;
                case R.id.ll_day_vegetables:
                    startIronStatisticsActivity(true, 2);
                    break;
            }

            switch (view.getId()) {
                case R.id.ll_month_register:
                    startIronStatisticsActivity(false, 0);
                    break;
                case R.id.ll_month_open:
                    startIronStatisticsActivity(false, 1);
                    break;
                case R.id.ll_month_vegetables:
                    startIronStatisticsActivity(false, 2);
                    break;
            }
        }
    }

    private void startIronStatisticsActivity(boolean isDay, int position) {
        Intent intent = new Intent(getContext(), IronStatisticsActivity.class);
        intent.putExtra("isDay", isDay);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);


        if (grade == 3) tv_team.setVisibility(View.GONE);

        //初始化顶部UI
        tv_username.setText("你好," + UserUtils.getInstance().getLoginBean().getRealname() + "!");
        tv_time.setText(GeneralUtils.getCurrDay() + "\t\t" + GeneralUtils.getWeekDay(System.currentTimeMillis()));
        tv_city.setText(tv_city.getText() + "武汉");


        //初始日月统计
        ironDayAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(this)
                .setResource(R.layout.iron_day_preview_item)
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_vegetables, R.id.ll_day_gmv, R.id.ll_day_price)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.getTodayData().dayRegisterTimes + "");
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.getTodayData().firstOrderNum + "");
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.getTodayData().categoryNum + "");
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(data.getTodayData().todayAmount + "");
                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.getTodayData().averageAmount + "" + "");

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
                .build(getContext());
        day_listView.setAdapter(ironDayAdapter);
        day_listView.setNestedScrollingEnabled(true);

        ironMonthAdapter = new ListViewAdapter.Builder<V3HomeData>()
                .setOnClickListener(this)
                .setResource(R.layout.iron_month_preview_item)
                .addChildId(R.id.ll_month_register, R.id.ll_month_open, R.id.ll_month_vegetables, R.id.ll_month_gmv)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.getMonthData().monthRegisterNum + "");
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.getMonthData().monthFirstOrderNum + "");
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.getMonthData().categoryNum + "");
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(data.getMonthData().monthAmount + "");


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
                .build(getContext());
        month_listView.setAdapter(ironMonthAdapter);
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
            }
        };
        keyPointAdapter.setOnItemChildClickListener(this);
        keyPoint_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        keyPoint_rv.setAdapter(keyPointAdapter);


        //排行榜
        tv_day.setOnClickListener(this);
        tv_month.setOnClickListener(this);
        isDay = (boolean) getArgs().get("isDay");
        System.out.println("isDay = " + isDay);
        ranking_vp.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public android.support.v4.app.Fragment getItem(int i) {
                Map<String, Object> map = new HashMap<>();
                map.put("type", i + 1);
                map.put("websiteId", site);
                map.put("userType", userType);
                map.put("timeType", isDay ? "day" : "month");

                Bundle bundle = new Bundle();
                bundle.putSerializable("args", (Serializable) map);

                android.support.v4.app.Fragment fragment = new IronRankingFragment();
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {

                titles = Arrays.asList(isDay ? "日总GMV" : "月总GMV", "注册总数", "新开总数");

                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                System.out.println("position = " + position);

                return titles.get(position);
            }
        });
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
                    ironDayAdapter.clear();
                    ironDayAdapter.add(v3HomeData);
                    ironDayAdapter.notifyDataSetChanged();

                    //月统计
                    tv_month_sink.setText(v3HomeData.getMonthData().gradeNextName.isEmpty() ? "" : v3HomeData.getMonthData().gradeNextName + " >");
                    ironMonthAdapter.clear();
                    ironMonthAdapter.add(v3HomeData);
                    ironMonthAdapter.notifyDataSetChanged();


                    //重点关注
                    keyPointAdapter.setNewData(v3HomeData.getPartnerApproachData());
                    keyPointAdapter.notifyDataSetChanged();
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_index_fragment;
    }

    @Override
    public void onRefresh() {
        Map<String, Object> map = new HashMap<>();

        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
        map.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());

        getVMWithFragment().loading(V3HomeData.class, map);
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
            }
        } else {
            switch (((V3HomeData.PartnerApproachDataBean) baseQuickAdapter.getItem(i)).getSort()) {
                case 2:
                    getActivity().startActivity(new Intent(getContext(), ApprovalActivity.class));
                    break;
            }
        }
    }
}
