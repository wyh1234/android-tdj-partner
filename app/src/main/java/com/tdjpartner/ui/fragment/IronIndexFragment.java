package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.IronHomeData;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.mvp.presenter.IronIndexFragmentPresenter;
import com.tdjpartner.ui.activity.IronDayActivity;
import com.tdjpartner.ui.activity.IronListActivity;
import com.tdjpartner.ui.activity.IronStatisticsActivity;
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
 * Created by LFM on 2021/3/3.
 */
public class IronIndexFragment extends BaseFrgment<IronIndexFragmentPresenter>
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.image_drop)
    ImageView image_drop;
    @BindView(R.id.image_sea)
    ImageView image_sea;

    @BindView(R.id.day_listView)
    ListView day_listView;
    @BindView(R.id.member_list)
    ListView month_listView;

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
    @BindView(R.id.count_drop)
    TextView count_drop;
    @BindView(R.id.count_sea)
    TextView count_sea;
    @BindView(R.id.ranking_vp)
    ViewPager ranking_vp;

    boolean isDay;//时间类型标记
    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型

    private IronAdapter ironDayAdapter, ironMonthAdapter;
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
            Intent intent = new Intent(getContext(), IronListActivity.class);
            intent.putExtra("isDay", true);
            startActivity(intent);
        }
        if (view.getId() == R.id.tv_month_sink) {
            Intent intent = new Intent(getContext(), IronListActivity.class);
            intent.putExtra("isDay", false);
            startActivity(intent);
        }

        if (view.getId() == R.id.ll_day_register ||
                view.getId() == R.id.ll_day_open ||
                view.getId() == R.id.ll_day_vegetables) {
            System.out.println(view);
            Intent intent = new Intent(getContext(), IronStatisticsActivity.class);
            intent.putExtra("isDay", true);
            startActivity(intent);
        }

        if (view.getId() == R.id.ll_month_register ||
                view.getId() == R.id.ll_month_open ||
                view.getId() == R.id.ll_month_vegetables) {
            System.out.println(view);
            Intent intent = new Intent(getContext(), IronStatisticsActivity.class);
            intent.putExtra("isDay", false);
            startActivity(intent);
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


        //排行榜
        tv_day.setOnClickListener(this);
        tv_month.setOnClickListener(this);
        ranking_vp.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            List<String> titles;

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public Fragment getItem(int i) {
                Fragment fragment = new RankingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type", i);
                bundle.putInt("websiteId", 3);
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
    }


    @Override
    protected void loadData() {

        //初始化顶部UI
        tv_username.setText("你好," + UserUtils.getInstance().getLoginBean().getRealname() + "!");
        tv_time.setText(GeneralUtils.getCurrDay() + "\t\t" + GeneralUtils.getWeekDay(System.currentTimeMillis()));
        tv_heard.setText(tv_heard.getText() + "武汉");


        ironDayAdapter = new IronAdapter.Builder()
                .setOnClickListener(this)
                .setResource(R.layout.day_preview_item)
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_vegetables, R.id.ll_day_gmv, R.id.ll_day_price)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.get(1));
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.get(2));
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(data.get(3));
                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.get(4));
                })
                .build(getContext());
        day_listView.setAdapter(ironDayAdapter);
        day_listView.setNestedScrollingEnabled(true);

        ironMonthAdapter = new IronAdapter.Builder()
                .setOnClickListener(this)
                .setResource(R.layout.month_preview_item)
                .addChildId(R.id.ll_month_register, R.id.ll_month_open, R.id.ll_month_vegetables, R.id.ll_month_gmv)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.registerNum)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.openNum)).setText(data.get(1));
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.get(2));
                    ((TextView) convertView.findViewById(R.id.gmvNum)).setText(data.get(3));
                })
                .build(getContext());
        month_listView.setAdapter(ironMonthAdapter);
        month_listView.setNestedScrollingEnabled(true);

        //-----------刷新加载数据----------------
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();

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
        getData();
    }

    public void getData() {
        Map<String, Object> map = new HashMap<>();

        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        System.out.println("userId is " + UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
        map.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());

//        map.put("userId", 258869);
        map.put("monthTime", "2021-04");
        map.put("dayDate", "2021-04-09");
        map.put("websiteId", 3);
        mPresenter.homeData(map);
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    public void homeData_Success(IronHomeData homeData) {
        stop();
        if (!ListUtils.isEmpty(registerlist)) {
            registerlist.clear();
        }
        if (!ListUtils.isEmpty(orderList)) {
            orderList.clear();
        }

        tv_day_sink.setText(homeData.getTodayData().gradeNextName.isEmpty()?"" : homeData.getTodayData().gradeNextName + " >");
        ironDayAdapter.clear();
        ironDayAdapter.add(Arrays.asList("" + homeData.getTodayData().dayRegisterTimes,
                "" + homeData.getTodayData().firstOrderNum,
                "" + homeData.getTodayData().categoryNum,
                "" + homeData.getTodayData().todayAmount,
                "" + homeData.getTodayData().averageAmount));
        ironDayAdapter.notifyDataSetChanged();


        tv_month_sink.setText(homeData.getMonthData().gradeNextName.isEmpty()?"" : homeData.getMonthData().gradeNextName + " >");
        ironMonthAdapter.clear();
        ironMonthAdapter.add(Arrays.asList("" + homeData.getMonthData().monthRegisterNum,
                "" + homeData.getMonthData().monthRegisterNum,
                "" + homeData.getMonthData().categoryNum,
                "" + homeData.getMonthData().addMonthAmount));
        ironMonthAdapter.notifyDataSetChanged();


        //重点关注
        ImageLoad.loadImageView(getContext(), homeData.getPartnerApproachData().get(0).getMenuPic(), image_drop);
        if (!homeData.getPartnerApproachData().get(0).getSubscriptNum().isEmpty()) {
            count_drop.setVisibility(View.VISIBLE);
            count_drop.setText(homeData.getPartnerApproachData().get(0).getSubscriptNum());
        } else {
            count_drop.setVisibility(View.GONE);
        }

        //公海跟进
        ImageLoad.loadImageView(getContext(), homeData.getPartnerApproachData().get(1).getMenuPic(), image_sea);
        if (!homeData.getPartnerApproachData().get(1).getSubscriptNum().isEmpty()) {
            count_sea.setVisibility(View.VISIBLE);
            count_sea.setText(homeData.getPartnerApproachData().get(1).getSubscriptNum());
        } else {
            count_sea.setVisibility(View.GONE);
        }
    }

    public void homeData_failed() {
        stop();
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
