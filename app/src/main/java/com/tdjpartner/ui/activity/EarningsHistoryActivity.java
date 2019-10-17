package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentEarningsHistoryAdapter;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EarningsHistoryActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    private FragmentEarningsHistoryAdapter fragmentEarningsHistoryAdapter;
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        List<String> titles = new ArrayList<>();
        titles.add("今日");
        titles.add("7天内");
        titles.add("本月");
        titles.add("最近三个月");
        titles.add("所有");
        wtab.setxTabDisplayNum(titles.size());
        fragmentEarningsHistoryAdapter = new FragmentEarningsHistoryAdapter(this.getSupportFragmentManager(), titles);
        viewPager.setAdapter(fragmentEarningsHistoryAdapter);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.earnings_history_layout;
    }


}
