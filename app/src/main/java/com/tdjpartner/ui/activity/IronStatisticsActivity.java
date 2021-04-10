package com.tdjpartner.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.HomeDataDetails;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IronDayStatisticsPresenter;
import com.tdjpartner.ui.fragment.IronDayListDetailFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronStatisticsActivity extends BaseActivity<IronDayStatisticsPresenter> {
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
    @BindView(R.id.ll_seach)
    LinearLayout ll_seach;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;

    boolean isDay;//时间类型标记
    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型


    private Calendar selectedDate, endDate, startDate;
    private Date date;
    private TimePickerView pvTime;
    public SeachTag seachTag = new SeachTag();
    public String title;
    public List<String> titles;

    @OnClick({R.id.btn_back, R.id.tv_time, R.id.tv_list_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
                if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString())) {
                    GeneralUtils.showToastshort("请输入门店名称或者手机号");

                } else {
                    seachTag.setTag(search_text.getText().toString());
                    EventBus.getDefault().post(seachTag);
                }
                break;
            case R.id.tv_time:
                pvTime.show(tv_time);
                break;
        }
    }

    public FragmentStatisticsAdapter adatper;

    @Override
    protected IronDayStatisticsPresenter loadPresenter() {
        return new IronDayStatisticsPresenter();
    }

    @Override
    protected void initData() {
        System.out.println("~~" + getClass().getSimpleName() + ".initData~~");
        refresh(new Date());
    }

    private void refresh(Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("userId", 25716);
        if (isDay) {
            map.put("dayDate", GeneralUtils.getTimeFilter(date));
        } else {
            map.put("monthTime", GeneralUtils.getMonthFilter(date));
        }
        map.put("keyword", "");
        map.put("userType", userType);
        map.put("pn", 1);
        map.put("ps", 999);

        mPresenter.getData(map);
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);

        isDay = getIntent().getBooleanExtra("isDay", false);

        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                IronStatisticsActivity.this.date = date;
                ((TextView) v).setText(isDay ? GeneralUtils.getTimes(date) : GeneralUtils.getTime(date));
                refresh(date);


//                if (getIntent().getStringExtra("title").equals("今日统计")) {
//
//                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(8,10)+"日");
//                    seachTag.setDayDate(GeneralUtils.getTimeFilter(date));
//                    EventBus.getDefault().post(seachTag);
//                }else {
//                    seachTag .setMonthTime(GeneralUtils.getTimeFilter(date));
//                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(5,7)+"月");
//                    EventBus.getDefault().post(seachTag);
//
//                }
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", isDay ? "日" : "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        tv_time.setText(isDay ? GeneralUtils.getCurrDay() : GeneralUtils.getCurrMonth());


//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
//        titles.add("全部");
//        titles.add("未下单");
//        titles.add("已下单");
//        titles.add("注册");
//        titles.add("拜访");
//        wtab.setxTabDisplayNum(titles.size());


        WTabLayout wtab = view.findViewById(R.id.wtab);


        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//            List<String> titles;

            @Override
            public Fragment getItem(int i) {
                System.out.println("~~" + getClass().getSimpleName() + ".getItem~~");
                Fragment fragment = new IronDayListDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public int getCount() {
                System.out.println("~~" + getClass().getSimpleName() + ".getCount~~");
                if (titles == null) {
                    if (userType == 1) {
                        titles = Arrays.asList("注册数" + 0,
                                "新开数" + 0,
                                "新鲜蔬菜" + 0);
                    } else {
                        titles = Arrays.asList("注册总数" + 0,
                                "新开总数" + 0,
                                "日活数" + 0,
                                "拜访总数" + 0);
                    }
                }
//                if (titles == null) {
//                    if (userType == 1) {
//                        titles = Arrays.asList("月日活", "月均日活", "月GMV");
//                    } else {
//                        titles = Arrays.asList("月总GMV", "注册总数", "新开总数");
//                    }
//                }
//                titles = Arrays.asList("注册数");

                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                System.out.println("~~" + getClass().getSimpleName() + ".getPageTitle~~");
                System.out.println("position = " + position);
                return titles.get(position);
            }
        });

//        wtab.setxTabDisplayNum(4);
        wtab.setupWithViewPager(viewPager);


//        adatper = new FragmentStatisticsAdapter(this.getSupportFragmentManager(), titles, getIntent().getStringExtra("title"));
//        viewPager.setAdapter(adatper);
//        viewPager.setCurrentItem(Integer.parseInt(getIntent().getStringExtra("pos")));
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
//        wtab.setupWithViewPager(viewPager);
//
//        seachTag.setDayDate(GeneralUtils.isNullOrZeroLenght(getIntent().getStringExtra("dayDate")) ? "" : getIntent().getStringExtra("dayDate"));
//        seachTag.setMonthTime(GeneralUtils.isNullOrZeroLenght(getIntent().getStringExtra("monthTime")) ? "" : getIntent().getStringExtra("monthTime"));
//        seachTag.setUserId(Integer.parseInt(getIntent().getStringExtra("userId")));
//        if (getIntent().getStringExtra("title").equals("今日统计")) {
//            tv_name.setText(getIntent().getStringExtra("dayDate").substring(8, 10) + "日");
//        } else if (getIntent().getStringExtra("title").equals("月统计")) {
//            tv_name.setText(getIntent().getStringExtra("monthTime").substring(5, 7) + "月");
//        } else {
//            tv_name.setPadding(15, 0, 15, 0);
//            tv_name.setVisibility(View.INVISIBLE);
//
//        }
    }


    public void success(IronStatisticsDetails ironStatisticsDetails) {
        System.out.println("~~" + getClass().getSimpleName() + ".success~~");
        System.out.println("ironStatisticsDetails = " + ironStatisticsDetails);
        System.out.println("------------------------"+ironStatisticsDetails.getCallNum());

        if (userType == 2) {
            titles = Arrays.asList("注册数" + (isDay ? ironStatisticsDetails.getDayRegisterTimes() : ironStatisticsDetails.getMonthRegisterNum()),
                    "新开数" + ironStatisticsDetails.getFirstOrderNum(),
                    "新鲜蔬菜" + ironStatisticsDetails.getCategoryNum());
        } else {
            titles = Arrays.asList("注册总数" + (isDay ? ironStatisticsDetails.getDayRegisterTimes() : ironStatisticsDetails.getMonthRegisterNum()),
                    "新开总数" + ironStatisticsDetails.getFirstOrderNum(),
                    "日活数" + ironStatisticsDetails.getActiveNum(),
                    "拜访总数" + ironStatisticsDetails.getCallNum());
        }
        viewPager.getAdapter().notifyDataSetChanged();

    }

    public void failure() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_statistics_activity;
    }
}
