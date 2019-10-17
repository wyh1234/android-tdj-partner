package com.tdjpartner;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.adapter.MainTabAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.model.ImageUploadOk;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.fragment.ClientFragment;
import com.tdjpartner.ui.fragment.HomepageFragment;
import com.tdjpartner.ui.fragment.MyFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.bottombar.BottomBarItem;
import com.tdjpartner.widget.bottombar.BottomBarLayout;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainTabActivity extends BaseActivity {
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;
    private List<BaseFrgment> mFragmentList;
    private MainTabAdapter mainTabAdapter;

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
        mainTabAdapter=new MainTabAdapter(mFragmentList,getSupportFragmentManager());
        mVpContent.setAdapter(mainTabAdapter);
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
        Eyes.translucentStatusBar(MainTabActivity.this,true);
        Eyes.setLightStatusBar(this,true);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Subscribe
    public void checkClientFragment(ClientFragmentType clientFragmentType){
        LogUtils.e(clientFragmentType);
            ((ClientFragment) mainTabAdapter.getBaseFrgment()).checkClientFragment(clientFragmentType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE_GRIDE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            LogUtils.i(Matisse.obtainPathResult(data).get(0));
            EventBus.getDefault().post(new ImageUploadOk(Matisse.obtainPathResult(data).get(0)));
        }

    }
}
