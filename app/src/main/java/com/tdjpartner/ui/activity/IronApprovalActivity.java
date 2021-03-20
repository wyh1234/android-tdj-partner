package com.tdjpartner.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.fragment.IronApprovalPendingFragment;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronApprovalActivity extends BaseActivity {
//    @BindView(R.id.tv_title)
//    TextView tv_title;
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

//    @BindView(R.id.btn_back)
//    ImageView btn_back;
//    @BindView(R.id.search_text)
//    EditText search_text;
//    @BindView(R.id.ll_seach)
//    LinearLayout ll_seach;
//    @BindView(R.id.tv_name)
//    TextView tv_name;
//    @BindView(R.id.tv_list_type)
//    TextView tv_list_type;

    private Calendar selectedDate, endDate, startDate;
    private TimePickerView pvTime;
    public SeachTag seachTag = new SeachTag();
    public String title;
    public List<String> titles = new ArrayList<>();

//    @OnClick({R.id.btn_back, R.id.tv_name, R.id.tv_list_type})
//    public void onClick(View view) {
////        switch (view.getId()) {
////            case R.id.btn_back:
////                finish();
////                break;
////            case R.id.tv_list_type:
////                if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString())) {
////                    GeneralUtils.showToastshort("请输入门店名称或者手机号");
////
////                } else {
////                    seachTag.setTag(search_text.getText().toString());
////                    EventBus.getDefault().post(seachTag);
////                }
////                break;
////            case R.id.tv_name:
////                pvTime.show(tv_name);
////                break;
////        }
//    }

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

//        selectedDate = Calendar.getInstance();
//        startDate = Calendar.getInstance();
//        startDate.set(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH) - 6), startDate.get(Calendar.DAY_OF_MONTH));
//        endDate = Calendar.getInstance();
//        endDate.set(endDate.get(Calendar.YEAR), (endDate.get(Calendar.MONTH)), endDate.get(Calendar.DAY_OF_MONTH));
//        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                ((TextView) v).setText(GeneralUtils.getTimes(date));
//
////                if (getIntent().getStringExtra("title").equals("今日统计")) {
////
////                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(8,10)+"日");
////                    seachTag.setDayDate(GeneralUtils.getTimeFilter(date));
////                    EventBus.getDefault().post(seachTag);
////                }else {
////                    seachTag .setMonthTime(GeneralUtils.getTimeFilter(date));
////                    tv_name.setText(GeneralUtils.getTimeFilter(date).substring(5,7)+"月");
////                    EventBus.getDefault().post(seachTag);
////
////                }
//            }
//        }) //年月日时分秒 的显示与否，不设置则默认全部显示
//                .setType(new boolean[]{true, true, true, false, false, false})
//                .setLabel("年", "月", "日", "", "", "")
//                .isCenterLabel(true)
//                .setDividerColor(Color.DKGRAY)
//                .setContentSize(16)
//                .setDate(selectedDate)
//                .setRangDate(startDate, endDate)
//                .setDecorView(null)
//                .build();
//
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

            List<String> titles = Arrays.asList("待审核", "审核通过", "审核驳回");

            @Override
            public Fragment getItem(int i) {
                Fragment fragment = new IronApprovalPendingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                System.out.println("position = " + position);
                return titles.get(position);
            }
        });
        wtab.setupWithViewPager(viewPager);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_activity;
    }
}
