package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.base.Fragment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.ClientListSeachActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.widget.tablayout.WTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ClientNewFragment extends Fragment {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.search_text)
    TextView search_text;
    public RxPermissions rxPermissions;

    @OnClick({R.id.tv_list_type, R.id.search_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_list_type:

                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        if (b) {
                            EventBus.getDefault().post(new ClientFragmentType("map"));
                            LocationUtils.getInstance().startLocalService("MAP");
                        } else {
                            GeneralUtils.showToastshort("请开启位置信息");
                        }

                    }
                });

                break;
            case R.id.search_text:
                Intent intent = new Intent(getContext(), ClientListSeachActivity.class);
                startActivity(intent);
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
        rxPermissions = new RxPermissions(getActivity());
        List<String> titles = new ArrayList<>();
        titles.add("我的客户");
        titles.add("公海客户");
        if (UserUtils.getInstance().getLoginBean().getType() == 1) titles.add("他人客户");
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
