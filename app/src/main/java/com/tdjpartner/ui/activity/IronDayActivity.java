package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamMemberHorizontalAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.NewMyTeam;
import com.tdjpartner.mvp.presenter.TeamMemberPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;

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
 * Created by LFM on 2021/3/12.
 */
public class IronDayActivity extends BaseActivity<TeamMemberPresenter> implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.recyclerView_list)
//    RecyclerView recyclerView_list;
//    @BindView(R.id.recyclerView_horizontal)
//    RecyclerView recyclerView_horizontal;

//    @BindView(R.id.btn_back)
//    ImageView btn_back;
//    @BindView(R.id.rl_seach)
//    RelativeLayout rl_seach;
//    @BindView(R.id.tv_list_type)
//    TextView tv_list_type;
//    @BindView(R.id.search_text)
//    EditText search_text;

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


    IronAdapter ironListAdapter;
    private Calendar selectedDate, endDate, startDate;

    private TimePickerView pvTime;
    private int userId;

    private TeamMemberAdapter teamMemberAdapter;
    private List<NewMyTeam> data = new ArrayList<>();
    private List<String> horizontal_data = new ArrayList<>();
    private List<String> horizontal_data_temp = new ArrayList<>();
    private Map<String, List<NewMyTeam>> myTeamMap = new HashMap<>();
    private TeamMemberHorizontalAdapter teamMemberHorizontalAdapter;


    @OnClick({R.id.tv_time, R.id.tv_day_sink})
    public void onClick(View view) {
        System.out.println("~~~~~~~~~view = " + view);
        switch (view.getId()) {
            case R.id.tv_time:
                setTime(true);
                break;
        }

        if (view.getId() == R.id.tv_day_sink ||
                view.getId() == R.id.ll_day_register ||
                view.getId() == R.id.ll_day_open ||
                view.getId() == R.id.ll_day_vegetables) {
            System.out.println(view);
        }


    }

    public void setTime(boolean hasDay) {
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(GeneralUtils.getTimes(date));
                getData();
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
    protected TeamMemberPresenter loadPresenter() {
        return new TeamMemberPresenter();
    }

    @Override
    protected void initData() {
        System.out.println("~~" + getClass().getSimpleName() + ".initData~~");

//        refreshLayout.setRefreshing(true);
//        onRefresh();


        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
//        endDate = Calendar.getInstance();
//        endDate.set(2029, 11, 28);
//        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));

        getData();
    }

    @Override
    protected void initView() {
//        Eyes.translucentStatusBar(this,true);
//        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
//        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL, false);
//        recyclerView_list.setLayoutManager(layout);
//        teamMemberAdapter=new TeamMemberAdapter(R.layout.team_member_item,data);
//        recyclerView_list.setAdapter(teamMemberAdapter);
//        teamMemberAdapter.setOnItemClickListener(this);
//        teamMemberAdapter.setOnItemChildClickListener(this);
//        LinearLayoutManager layout_horizontal = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.HORIZONTAL, false);
//
//
//        horizontal_data.add("战区");
//        recyclerView_horizontal.setLayoutManager(layout_horizontal);
//        teamMemberHorizontalAdapter=new TeamMemberHorizontalAdapter(R.layout.horizontal_team_item,horizontal_data);
//        recyclerView_horizontal.setAdapter(teamMemberHorizontalAdapter);
//        teamMemberHorizontalAdapter.setOnItemClickListener(this);
//  /*      search_text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                refreshLayout.setRefreshing(true);
//                onRefresh();
//
//            }
//        });*/


        tv_title.setText("M2" + tv_title.getText());

        ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));

        ((TextView) other_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));




        ironListAdapter = new IronAdapter.Builder()
                .addChildId(R.id.ll_day_register, R.id.ll_day_open, R.id.ll_day_vegetables, R.id.ll_day_gmv, R.id.ll_day_price, R.id.tv_day_sink)
                .setOnClickListener(this)
                .setResource(R.layout.iron_day_list_member_layout)
                .setInitView((data, convertView) -> {
                    ((TextView)convertView.findViewById(R.id.registerNum)).setText(data.get(0));
                    ((TextView)convertView.findViewById(R.id.openNum)).setText(data.get(1));
                    ((TextView)convertView.findViewById(R.id.vegetablesNum)).setText(data.get(2));
                    ((TextView)convertView.findViewById(R.id.gmvNum)).setText(data.get(3));
                    ((TextView)convertView.findViewById(R.id.priceNum)).setText(data.get(3));
                    ((TextView)convertView.findViewById(R.id.tv_name)).setText(data.get(4));
                })
                .build(this);

        member_list.setAdapter(ironListAdapter);
        member_list.setNestedScrollingEnabled(true);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_day_list_layout;
    }

    @Override
    public void onRefresh() {
        getData();
    }

    public void getData() {
//        if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())){
//            recyclerView_horizontal.setVisibility(View.VISIBLE);
//        }else {
//            recyclerView_horizontal.setVisibility(View.GONE);
//        }
//        Map<String,Object> map=new HashMap<>();
//        map.put("userId", Integer.parseInt(getIntent().getStringExtra("userId")));
//        map.put("websiteId",UserUtils.getInstance().getLoginBean().getSite());
//        map.put("keyword", GeneralUtils.isNullOrZeroLenght(search_text.getText().toString().trim())?"":search_text.getText().toString().trim());
//
//        mPresenter.memberList(map);





        ironListAdapter.clear();
        for (int i = 0; i < 5; i++) {
            ironListAdapter.add(Arrays.asList("" + random.nextInt(999),
                    "" + random.nextInt(999),
                    "" + random.nextInt(999),
                    "" + random.nextInt(999),
                    "经理：Lee" + random.nextInt(999)));
        }
        ironListAdapter.notifyDataSetChanged();


        ((TextView) ll_header_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) ll_header_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));

        ((TextView) other_include.findViewById(R.id.registerNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.openNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.vegetablesNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.gmvNum)).setText("" + random.nextInt(1000));
        ((TextView) other_include.findViewById(R.id.priceNum)).setText("" + random.nextInt(1000));


    }

    public void stop() {
//        if (refreshLayout != null && refreshLayout.isRefreshing()) {
//            refreshLayout.setRefreshing(false);
//        }
    }


    public void myTeamPartnerList_Success(List<NewMyTeam> myTeam) {

//        if (refreshLayout.isRefreshing()){
//            if (!ListUtils.isEmpty(data)) {
//                data.clear();
//            }
//            if (!ListUtils.isEmpty(horizontal_data)){
//                horizontal_data.clear();
//                horizontal_data.add("战区");
//            }
//            if (!ListUtils.isEmpty(horizontal_data_temp)){
//                horizontal_data_temp.clear();
//            }
//            if (myTeamMap.size()>0){
//                myTeamMap.clear();
//            }
//                if (ListUtils.isEmpty(myTeam)) {
//                    mStateView.showEmpty();
//                }else {
//                    mStateView.showContent();//显示内容
//                }
//
//            }
//        stop();
//        data.addAll(myTeam);
//        teamMemberAdapter.setNewData(data);
//        teamMemberHorizontalAdapter.setNewData(horizontal_data);

    }

//    public View getStateViewRoot() {
//        return recyclerView_list;
//    }

    public void myTeamPartnerList_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            mStateView.showEmpty();//显示重试的布局
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter instanceof TeamMemberAdapter) {
            List<NewMyTeam> myTeam = baseQuickAdapter.getData();
            if (myTeam.get(i).getPartnerId() != 0) {
                if (myTeam.get(i).getGrade() == 3) {
                    Intent intent = new Intent(this, HomePageActivity.class);
                    intent.putExtra("userId", myTeam.get(i).getPartnerId() + "");
                    startActivity(intent);
                } else {
                    Intent show = new Intent(this, MenberHomepageActivity.class);
                    show.putExtra("userId", myTeam.get(i).getPartnerId());
                    startActivity(show);
                }
            }

        } else {
            System.out.println("i = " + i);
            System.out.println(horizontal_data.size() + "|" + horizontal_data);
            if (i != horizontal_data.size() - 1) {
                if (horizontal_data_temp.size() > 0) {
                    horizontal_data_temp.clear();
                }
                for (int k = 0; k <= i; k++) {
                    horizontal_data_temp.add(horizontal_data.get(k));
                }
                horizontal_data.clear();
                horizontal_data.addAll(horizontal_data_temp);
                teamMemberHorizontalAdapter.setNewData(horizontal_data);
                if (i == 0) {
                    teamMemberAdapter.setNewData(data);

                } else {
                    teamMemberAdapter.setNewData(myTeamMap.get(horizontal_data.get(i)));
                }


            } else {
                teamMemberAdapter.setNewData(data);
            }
        }


    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {


//        if (baseQuickAdapter instanceof TeamMemberAdapter){
//            List<NewMyTeam> myTeam= baseQuickAdapter.getData();
//            if (myTeam.get(i).getChildPartnerTree()!=null){
//                teamMemberAdapter.setNewData(myTeam.get(i).getChildPartnerTree());
//                horizontal_data.add(myTeam.get(i).getGradeName());
//                myTeamMap.put(myTeam.get(i).getGradeName(),myTeam.get(i).getChildPartnerTree());
//                teamMemberHorizontalAdapter.setNewData(horizontal_data);
//            }
//
//            if(recyclerView_horizontal.getVisibility()==View.GONE){
//                if (myTeam.get(i).getGrade()==3){
//                    Intent intent=new Intent(this,MenberHomepageActivity.class);
//                    intent.putExtra("userId",myTeam.get(i).getPartnerId());
//                    LogUtils.e(myTeam.get(i).getPartnerId());
//                    startActivity(intent);
//                }else {
//                    Intent show=new Intent(this, HomePageActivity.class);
//                    LogUtils.e(myTeam.get(i).getPartnerId());
//                    show.putExtra("userId",myTeam.get(i).getPartnerId()+"");
//                    startActivity(show);
//                }
//
//            }
//        }

    }

}
