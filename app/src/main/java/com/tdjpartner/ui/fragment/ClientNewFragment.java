package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.tdjpartner.MainTabActivity;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClientNewFragment extends BaseFrgment {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;

    @OnClick({R.id.tv_list_type})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.tv_list_type:
                EventBus.getDefault().post(new ClientFragmentType("map"));
                break;
        }
    }
    @Override
    protected void initView(View view) {
    }



    @Override
    protected void loadData() {

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();//第一次加载
        List<String> titles = new ArrayList<>();
        titles.add("我的客户");
        titles.add("公海客户");
        titles.add("他人客户");
        FragmentAdapter adatper = new FragmentAdapter(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(adatper);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }
    @Override
    protected int getContentId() {
        return R.layout.client_fragment;
    }
}
