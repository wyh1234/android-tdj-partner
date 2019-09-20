package com.tdjpartner;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.tdjpartner.adapter.MainTabAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.fragment.ClientFragment;
import com.tdjpartner.ui.fragment.HomepageFragment;
import com.tdjpartner.ui.fragment.MyFragment;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.bottombar.BottomBarItem;
import com.tdjpartner.widget.bottombar.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainTabActivity extends BaseActivity {
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;
    private List<BaseFrgment> mFragmentList;

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        mFragmentList = new ArrayList<>(5);
        mFragmentList.add(new HomepageFragment());
        mFragmentList.add(new ClientFragment());
        mFragmentList.add(new MyFragment());
        mVpContent.setAdapter(new MainTabAdapter(mFragmentList,getSupportFragmentManager()));
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {
                Log.i("MainActivity", "position: " + currentPosition);
                setStatusBarColor(currentPosition);
                if (currentPosition == 0) {
                }

            }
        });

//        mBottomBarLayout.setUnread(0, 20);//设置第一个页签的未读数为20

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(MainTabActivity.this,true);
    }
    private void setStatusBarColor(int position) {
            //如果是我的页面，状态栏设置为透明状态栏

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
