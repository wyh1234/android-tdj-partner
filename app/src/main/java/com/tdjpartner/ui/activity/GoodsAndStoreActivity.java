package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentGoodsAndStoreAdapter;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsAndStoreActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private FragmentGoodsAndStoreAdapter fragmentGoodsAndStoreAdapter;
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
            titles.add("常购商品");
            titles.add("关注店铺");
        wtab.setxTabDisplayNum(titles.size());
        fragmentGoodsAndStoreAdapter = new FragmentGoodsAndStoreAdapter(this.getSupportFragmentManager(), titles);
        viewPager.setAdapter(fragmentGoodsAndStoreAdapter);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.goodsandstore_layout;
    }
}
