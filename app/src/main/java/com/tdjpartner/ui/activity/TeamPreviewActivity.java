package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamPreviewAdapter;
import com.tdjpartner.adapter.TeamPreviewAllAdapter;
import com.tdjpartner.adapter.TeamPreviewMothAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.presenter.TeamPreviewPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.TeamMemberPopuWindow;
import com.tdjpartner.utils.popuwindow.TeamPreviewPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamPreviewActivity extends BaseActivity<TeamPreviewPresenter> implements  BaseQuickAdapter.OnItemChildClickListener,TeamPreviewPopuWindow.TeamPreviewPopuWindowListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.recyclerView_list1)
    RecyclerView recyclerView_list1;
    @BindView(R.id.recyclerView_list2)
    RecyclerView recyclerView_list2;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.title)
    RelativeLayout title;
    private TeamPreviewAdapter teamPreviewAdapter;
    private TeamPreviewMothAdapter teamPreviewAdapter1;
    private TeamPreviewAllAdapter teamPreviewAdapter2;
    private List<TeamOverView> data=new ArrayList<>();//今日
    private List<TeamOverView> data1=new ArrayList<>();//当月
    private List<TeamOverView> data2=new ArrayList<>();//所有
    private List<MyTeam.ObjBean> mtTeamData=new ArrayList<>();
    private TeamPreviewPopuWindow teamPreviewPopuWindow;
    private String startTime="";
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OnClick({R.id.btn_back,R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_right:
                if (teamPreviewPopuWindow!=null){
                    if (teamPreviewPopuWindow.isShowing()){
                        return;
                    }
                    teamPreviewPopuWindow.showPopupWindow(title);
                }else {
                    teamPreviewPopuWindow = new TeamPreviewPopuWindow(this,mtTeamData);
                    teamPreviewPopuWindow.setDismissWhenTouchOutside(false);
                    teamPreviewPopuWindow.setInterceptTouchEvent(false);
                    teamPreviewPopuWindow.showPopupWindow(title);
                    teamPreviewPopuWindow.setPopupWindowFullScreen(true);//铺满
                    teamPreviewPopuWindow.setTeamPreviewPopuWindowListener(this);
                }
                break;
        }
    }
    @Override
    protected TeamPreviewPresenter loadPresenter() {
        return new TeamPreviewPresenter();
    }

    @Override
    protected void initData() {
        teamOverView_day();
        teamOverView_month();
        teamOverView_all();
        myTeamPartnerList();
    }

    public void teamOverView_day(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", getUserId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_day(map);//今日；
    }
    public void teamOverView_month(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",getUserId());
        map.put("startTime", startTime);
        map.put("flag", "all");
        mPresenter.teamOverView_month(map);//今月；
    }
    public void teamOverView_all(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", getUserId());
        mPresenter.teamOverView_all(map);//今月；
    }
    public void myTeamPartnerList(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", getUserId());
        map.put("isManage", "yes");

        mPresenter.myTeamPartnerList(map);//今月；
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);


        CustomLinearLayout layout = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout.setScrollEnabled(false);
        CustomLinearLayout layout1 = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout1.setScrollEnabled(false);
        CustomLinearLayout layout2 = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout2.setScrollEnabled(false);

        recyclerView_list.setLayoutManager(layout);
        recyclerView_list1.setLayoutManager(layout1);
        recyclerView_list2.setLayoutManager(layout2);
        toolbar.setBackgroundResource(R.mipmap.home_bg);
        tv_title.setText("团队概况");

        teamPreviewAdapter=new TeamPreviewAdapter(R.layout.teampreview_item_layout,data);
        recyclerView_list.setAdapter(teamPreviewAdapter);
        teamPreviewAdapter.setOnItemChildClickListener(this);


        teamPreviewAdapter1=new TeamPreviewMothAdapter(R.layout.teampreview_item_layout,data1);
        recyclerView_list1.setAdapter(teamPreviewAdapter1);
        teamPreviewAdapter1.setOnItemChildClickListener(this);

        teamPreviewAdapter2=new TeamPreviewAllAdapter(R.layout.teampreview_item_layout,data2);
        recyclerView_list2.setAdapter(teamPreviewAdapter2);
        teamPreviewAdapter2.setOnItemChildClickListener(this);

        selectedDate= Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-6),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));
        setUserId(Integer.parseInt(getIntent().getStringExtra("userId")));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.teampreview_layout;
    }

/*    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }*/

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()!=R.id.rl_right){
            Intent intent=new Intent(this,TeamMemberActivity.class);
            intent.putExtra("userId",getUserId()+"");
            if (baseQuickAdapter instanceof TeamPreviewAdapter){
                intent.putExtra("timeType","day");
                if (GeneralUtils.isNullOrZeroLenght(startTime)){
                    intent.putExtra("startTime", Calendar.getInstance().get(Calendar.YEAR)+
                            "-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                }else {
                    intent.putExtra("startTime",startTime);
                }
            }else if (baseQuickAdapter instanceof TeamPreviewMothAdapter){
                intent.putExtra("timeType","month");
                if (GeneralUtils.isNullOrZeroLenght(startTime)){
                    intent.putExtra("startTime", Calendar.getInstance().get(Calendar.YEAR)+
                            "-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                }else {
                    intent.putExtra("startTime",startTime);
                }
            }else {
                intent.putExtra("timeType","all");
            }
            startActivity(intent) ;
        }else {
           if (baseQuickAdapter instanceof TeamPreviewAdapter){
                setTime(1);
            }else if (baseQuickAdapter instanceof TeamPreviewMothAdapter){
                setTime(2);
            }

        }

    }

    public void teamOverView_day_Success(TeamOverView teamOverView) {
            if (!ListUtils.isEmpty(data)) {
                data.clear();
        }


        data.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)){
            data.get(0).setDate(startTime.substring(5,10).replace("-","月")+"日");
            LogUtils.e(startTime.substring(5,10).replace("-","月")+"日");
        }

        teamPreviewAdapter.notifyDataSetChanged();


    }
    public void teamOverView_month_Success(TeamOverView teamOverView) {
            if (!ListUtils.isEmpty(data1)) {
                data1.clear();
            }
        data1.add(teamOverView);
        if (!GeneralUtils.isNullOrZeroLenght(startTime)){
            data1.get(0).setDate(startTime.substring(5,7)+"月");
            LogUtils.e(startTime.substring(5,7)+"月");
        }
        teamPreviewAdapter1.setNewData(data1);
    }
    public void teamOverView_all_Success(TeamOverView teamOverView) {
            if (!ListUtils.isEmpty(data2)) {
                data2.clear();
            }
        data2.add(teamOverView);
        teamPreviewAdapter2.setNewData(data2);
    }



    public void setTime(int type){
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startTime=GeneralUtils.getTimeFilter(date);
                if (type==1){
                   teamOverView_day();

                }else {
                    teamOverView_month();
                }

            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, type==1, false, false, false})
                .setLabel("年", "月", type==1?"日":"", "", "", "")
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


    public void myTeamPartnerList_Success(MyTeam myTeam) {
        if (myTeam.getObj().size()>0){
           tv_right.setText("所有团队");
            mtTeamData.addAll(myTeam.getObj());
        }



    }


    @Override
    public void onOk(int userId,String str) {
        LogUtils.e(userId);
        tv_right.setText(str);
        setUserId(userId);
        teamOverView_month();
        teamOverView_all();
        teamOverView_day();
        teamPreviewPopuWindow.dismiss();


    }
}
