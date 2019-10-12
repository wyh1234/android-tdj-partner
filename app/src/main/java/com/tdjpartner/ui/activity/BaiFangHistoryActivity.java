package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentBaiFangHistoryAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BaiFangHistoryActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tv_title;
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
    private FragmentBaiFangHistoryAdapter fragmentGoodsAndStoreAdapter;
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
        tv_title.setText("拜访记录");
        List<String> titles = new ArrayList<>();
        titles.add("今天");
        titles.add("昨天");
        titles.add("七天内");
        titles.add("一月内");
        titles.add("一年内");
        wtab.setxTabDisplayNum(titles.size());
        fragmentGoodsAndStoreAdapter = new FragmentBaiFangHistoryAdapter(this.getSupportFragmentManager(), titles);
        viewPager.setAdapter(fragmentGoodsAndStoreAdapter);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.baifang_history_layout;
    }
}
