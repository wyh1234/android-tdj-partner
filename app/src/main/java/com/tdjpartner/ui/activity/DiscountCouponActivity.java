package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentDiscountCouponAdapter;
import com.tdjpartner.adapter.FragmentGoodsAndStoreAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DiscountCouponActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private FragmentDiscountCouponAdapter fragmentGoodsAndStoreAdapter;
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
        tv_title.setText("使用优惠券");
        List<String> titles = new ArrayList<>();
        titles.add("未使用（0）");
        titles.add("使用记录（0）");
        titles.add("已过期（15）");
        wtab.setxTabDisplayNum(titles.size());
        fragmentGoodsAndStoreAdapter = new FragmentDiscountCouponAdapter(this.getSupportFragmentManager(), titles);
        viewPager.setAdapter(fragmentGoodsAndStoreAdapter);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.discount_coupon_layout;
    }
}
