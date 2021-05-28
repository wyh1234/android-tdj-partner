package com.tdjpartner.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.ui.fragment.NetSupportFragment;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/15.
 */
public class NetSupportActivity extends NetworkActivity {

    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    public SeachTag seachTag = new SeachTag();
    public String title;
    public List<String> titles = new ArrayList<>();

    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.net_support_activity;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            List<String> titles = Arrays.asList("指派", "全部");

            @Override
            public Fragment getItem(int i) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
                map.put("tab", 0);
                map.put("title", i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("args", (Serializable) map);

                Fragment fragment = new NetSupportFragment();
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
    protected void initData() {
    }
}
