package com.tdjpartner.ui.activity.statistics;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.adapter.FragmentStatisticsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StatisticsListActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.search_text)
    TextView search_text;
    @BindView(R.id.rl_seach)
    RelativeLayout rl_seach;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    private FragmentStatisticsAdapter adatper;
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
        tv_title.setText(getIntent().getStringExtra("title"));
        if (getIntent().getStringExtra("title").equals("今日统计")){
            titles.add("全部75");
            titles.add("未下单数55");
            titles.add("已下单数10");
        }else if (getIntent().getStringExtra("title").equals("月统计")){
            titles.add("注册数");
            titles.add("平均日活量");
            titles.add("已下单金额");
        }else if (getIntent().getStringExtra("title").equals("所有统计")){
            titles.add("总注册数");
            titles.add("下单客户数量");
            titles.add("未下单客户数量");
            tv_name.setVisibility(View.GONE);
            rl_seach.setPadding(54,0,0,0);
        }
        wtab.setxTabDisplayNum(titles.size());
         adatper = new FragmentStatisticsAdapter(this.getSupportFragmentManager(), titles,getIntent().getStringExtra("title"));
        viewPager.setAdapter(adatper);
        viewPager.setCurrentItem(Integer.parseInt(getIntent().getStringExtra("pos")));
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.statistics_list_layout;
    }
}
