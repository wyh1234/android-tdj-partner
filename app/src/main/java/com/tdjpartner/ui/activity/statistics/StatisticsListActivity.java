package com.tdjpartner.ui.activity.statistics;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StatisticsListActivity extends BaseActivity {
    @BindView(R.id.tv_title)
     TextView tv_title;
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.search_text)
    EditText search_text;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    public SeachTag seachTag=new SeachTag();
    public String title;
    public List<String> titles = new ArrayList<>();
    @OnClick({R.id.btn_back,R.id.tv_name,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
                if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString())){
                    GeneralUtils.showToastshort("请输入门店名称或者手机号");

                }else {
                    seachTag.setTag(search_text.getText().toString());
                    EventBus.getDefault().post(seachTag);
                }
                break;
            case R.id.tv_name:
                pvTime.show();
                break;
        }
    }
    public FragmentStatisticsAdapter adatper;
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);

        selectedDate= Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-6),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (getIntent().getStringExtra("title").equals("今日统计")) {

                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(8,10)+"日");
                    seachTag.setDayDate(GeneralUtils.getTimeFilter(date));
                    EventBus.getDefault().post(seachTag);
                }else {
                    seachTag .setMonthTime(GeneralUtils.getTimeFilter(date));
                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(5,7)+"月");
                    EventBus.getDefault().post(seachTag);

                }
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        title=getIntent().getStringExtra("title");
        tv_title.setText(title);
//        if (getIntent().getStringExtra("title").equals("今日统计")){
        titles.add("全部");
        titles.add("未下单数");
        titles.add("已下单数");
//        }
   /*     else if (getIntent().getStringExtra("title").equals("月统计")){
            titles.add("注册数");
            titles.add("平均日活量");
            titles.add("已下单金额");
        }else if (getIntent().getStringExtra("title").equals("所有统计")){
            titles.add("总注册数");
            titles.add("下单客户数量");
            titles.add("未下单客户数量");
            tv_name.setVisibility(View.GONE);
            rl_seach.setPadding(54,0,0,0);
        }*/
        wtab.setxTabDisplayNum(titles.size());
        adatper = new FragmentStatisticsAdapter(this.getSupportFragmentManager(), titles, getIntent().getStringExtra("title"));
        viewPager.setAdapter(adatper);
//        viewPager.setCurrentItem(Integer.parseInt(getIntent().getStringExtra("pos")));
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);

        seachTag.setDayDate(GeneralUtils.isNullOrZeroLenght(getIntent().getStringExtra("dayDate")) ? "" : getIntent().getStringExtra("dayDate"));
        seachTag.setMonthTime(GeneralUtils.isNullOrZeroLenght(getIntent().getStringExtra("monthTime")) ? "" : getIntent().getStringExtra("monthTime"));
        seachTag.setUserId(Integer.parseInt(getIntent().getStringExtra("userId")));
        if (getIntent().getStringExtra("title").equals("今日统计")) {
            tv_name.setText(getIntent().getStringExtra("dayDate").substring(8,10)+"日");
        }else if (getIntent().getStringExtra("title").equals("月统计")){
            tv_name.setText(getIntent().getStringExtra("monthTime").substring(5,7)+"月");
        }else {
            tv_name.setPadding(15,0,15,0);
            tv_name.setVisibility(View.INVISIBLE);
//            tv_name.setText((Calendar.getInstance().get(Calendar.YEAR)+"-"+
//                    (Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))).substring(5,7)+"月");

        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.statistics_list_layout;
    }
}
