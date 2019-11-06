package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentDiscountCouponAdapter;
import com.tdjpartner.adapter.FragmentGoodsAndStoreAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.CouponsStatistics;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.mvp.presenter.DiscountCouponActivityPresenter;
import com.tdjpartner.mvp.presenter.DiscountCouponPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DiscountCouponActivity extends BaseActivity<DiscountCouponActivityPresenter> {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    public String customerId;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    private FragmentDiscountCouponAdapter fragmentGoodsAndStoreAdapter;
    @Override
    protected DiscountCouponActivityPresenter loadPresenter() {
        return new DiscountCouponActivityPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("使用优惠券");
        customerId=getIntent().getStringExtra("buyId");
        Map<String,Object> map=new HashMap<>();
        map.put("userId", customerId);
        map.put("userType", 0);
        map.put("site", UserUtils.getInstance().getLoginBean().getSite());
        mPresenter.coupons_statistics(map);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.discount_coupon_layout;
    }


    public void coupons_statistics_Success(CouponsStatistics couponsStatistics) {
        List<String> titles = new ArrayList<>();
        titles.add("未使用（"+couponsStatistics.getUnused()+")");
        titles.add("使用记录（"+couponsStatistics.getUsed()+")");
        titles.add("已过期（"+couponsStatistics.getExpired()+")");
        wtab.setxTabDisplayNum(titles.size());
        fragmentGoodsAndStoreAdapter = new FragmentDiscountCouponAdapter(this.getSupportFragmentManager(), titles);
        viewPager.setAdapter(fragmentGoodsAndStoreAdapter);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }
}
