package com.tdjpartner.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamPreviewAdapter;
import com.tdjpartner.adapter.TeamPreviewAllAdapter;
import com.tdjpartner.adapter.TeamPreviewMothAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.presenter.MenberHomepageActivityPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MenberHomepageActivity extends BaseActivity<MenberHomepageActivityPresenter> implements SwipeRefreshLayout.OnRefreshListener
        , View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    private RecyclerView month_recyclerView, all_recyclerView;
    private TeamPreviewAdapter teamPreviewAdapter;
    private TeamPreviewMothAdapter teamPreviewAdapter1;
    private TeamPreviewAllAdapter teamPreviewAdapter2;
    private TimePickerView pvTime;
    private String startTime = "";
    private List<TeamOverView> data = new ArrayList<>();//今日
    private List<TeamOverView> data1 = new ArrayList<>();//当月
    private List<TeamOverView> data2 = new ArrayList<>();//所有

    private Calendar selectedDate, endDate, startDate;

    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
             finish();
                break;
        }
    }




    public void teamOverView_day() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",getIntent().getIntExtra("userId",0));
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_day(map);//今日；
    }

    public void teamOverView_month() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",getIntent().getIntExtra("userId",0));
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_month(map);//今月；
    }

    public void teamOverView_all() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",getIntent().getIntExtra("userId",0));
        mPresenter.teamOverView_all(map);//今月；
        LogUtils.e(map);
    }

    @Override
    protected MenberHomepageActivityPresenter loadPresenter() {
        return new MenberHomepageActivityPresenter();
    }

    @Override
    protected void initData() {


        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));



        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }


        @Override
        protected void initView () {
            Eyes.translucentStatusBar(this,true);
            toolbar.setBackgroundResource(R.mipmap.home_bg);
            tv_title.setText("个人数据");

            swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
            swipeRefreshLayout.setOnRefreshListener(this);


            rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            CustomLinearLayout layout1 = new CustomLinearLayout(getContext(),
                    CustomLinearLayout.VERTICAL, false);
            layout1.setScrollEnabled(false);
            CustomLinearLayout layout2 = new CustomLinearLayout(getContext(),
                    CustomLinearLayout.VERTICAL, false);
            layout2.setScrollEnabled(false);
            teamPreviewAdapter = new TeamPreviewAdapter(R.layout.teampreview_item_layout, data);
            rv_recyclerView.setAdapter(teamPreviewAdapter);
            teamPreviewAdapter.setTiltle("日统计");
            View footView = ViewUrils.getFragmentView(rv_recyclerView, R.layout.homepage_new_foot_layout);
            teamPreviewAdapter.addFooterView(footView);


            month_recyclerView = footView.findViewById(R.id.recyclerView_month_list);
            all_recyclerView = footView.findViewById(R.id.recyclerView_all_list);


            month_recyclerView.setLayoutManager(layout1);
            all_recyclerView.setLayoutManager(layout2);


            teamPreviewAdapter1 = new TeamPreviewMothAdapter(R.layout.teampreview_item_layout, data1);
            month_recyclerView.setAdapter(teamPreviewAdapter1);
            teamPreviewAdapter1.setTiltle("月统计");

            teamPreviewAdapter2 = new TeamPreviewAllAdapter(R.layout.teampreview_item_layout, data2);
            all_recyclerView.setAdapter(teamPreviewAdapter2);
            teamPreviewAdapter2.setTiltle("总统计");



        }

    @Override
    protected int getLayoutId() {
            return R.layout.menber_homepage_actyivity_layout;
    }


    @Override
    public void onRefresh() {
        teamOverView_day();
        teamOverView_month();
        teamOverView_all();
    }


    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }



    public void teamOverView_day_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data)) {
            data.clear();
        }


        data.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)) {
            data.get(0).setDate(startTime.substring(5, 10).replace("-", "月") + "日");
            LogUtils.e(startTime.substring(5, 10).replace("-", "月") + "日");
        }

        teamPreviewAdapter.notifyDataSetChanged();
        stop();

    }

    public void teamOverView_month_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data1)) {
            data1.clear();
        }
        data1.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)) {
            data1.get(0).setDate(startTime.substring(5, 7) + "月");
            LogUtils.e(startTime.substring(5, 7) + "月");
        }
        teamPreviewAdapter1.setNewData(data1);
        stop();
    }

    public void teamOverView_all_Success(TeamOverView teamOverView) {
        if (!ListUtils.isEmpty(data2)) {
            data2.clear();
        }
        data2.add(teamOverView);
        teamPreviewAdapter2.setNewData(data2);
        stop();
    }

    public void homeData_failed() {
        stop();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId() == R.id.rl_right) {
            if (baseQuickAdapter instanceof TeamPreviewAdapter) {
                setTime(1);
            } else if (baseQuickAdapter instanceof TeamPreviewMothAdapter) {
                setTime(2);
            }
        }
    }

    public void setTime(int type) {
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startTime = GeneralUtils.getTimeFilter(date);
                if (type == 1) {
                    teamOverView_day();

                } else {
                    teamOverView_month();
                }

            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, type == 1, false, false, false})
                .setLabel("年", "月", type == 1 ? "日" : "", "", "", "")
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
}