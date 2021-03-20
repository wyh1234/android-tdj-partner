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
import com.tdjpartner.model.AfterSaleInfo;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IronAfterSalesPresenter;
import com.tdjpartner.ui.fragment.IronSupportFragment;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronSupportActivity extends BaseActivity {
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
    protected IronAfterSalesPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {}

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);

        WTabLayout wtab = view.findViewById(R.id.wtab);


        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            List<String> titles = Arrays.asList("指派", "全部");

            @Override
            public Fragment getItem(int i) {
                Fragment fragment = new IronSupportFragment();
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
        return R.layout.iron_support_activity;
    }

}
