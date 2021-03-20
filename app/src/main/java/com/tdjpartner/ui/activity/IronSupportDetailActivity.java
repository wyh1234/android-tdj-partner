package com.tdjpartner.ui.activity;

import com.bigkoo.pickerview.TimePickerView;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LFM on 2021/3/17.
 */
public class IronSupportDetailActivity extends BaseActivity {
//    @BindView(R.id.tv_title)
//    TextView tv_title;
//    @BindView(R.id.wtab)
//    WTabLayout wtab;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;

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

    }


    @Override
    protected int getLayoutId() {
        return R.layout.iron_support_detail_activity;
    }
}
