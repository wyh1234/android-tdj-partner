package com.tdjpartner.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.mvp.presenter.IPresenter;

import org.greenrobot.eventbus.Subscribe;
import java.util.List;

public class ClientFragment  extends BaseFrgment {
    private ClientMapFragment clientMapFragment;
    private ClientNewFragment clientNewFragment;
    private  String mapdata = "map";
    private  String listdata = "list";
    @Override
    protected void initView(View view) {

    }

    @Override
    protected void onFragmentFirstVisible() {//第一次可见，不会加载onUserVisible()
        super.onFragmentFirstVisible();
        checkClientFragment(new ClientFragmentType(listdata));

    }

    public List<Fragment> getChildFrament() {
        return getChildFragmentManager().getFragments();
    }


    @Override
    public void onUserVisible() {//再次可见，不会加载initData
        super.onUserVisible();
        LogUtils.e(111);

    }

    public void checkClientFragment(ClientFragmentType clientFragmentType) {
        FragmentTransaction fa = getChildFragmentManager().beginTransaction();
        List<Fragment> list = getChildFrament();
        if (clientFragmentType.getType().equals("list")){
            if (list.size() == 0) {
                if (clientNewFragment == null) clientNewFragment = new ClientNewFragment();
                fa.add(R.id.fm_fragment, clientNewFragment, listdata);
                fa.commit();
            } else {
                if (list.get(0) instanceof ClientNewFragment) {
                    if (clientNewFragment == null) clientNewFragment = new ClientNewFragment();
                    fa.replace(R.id.fm_fragment, clientNewFragment, listdata);
                    fa.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fa.commitNowAllowingStateLoss();
                } else {
                    if (clientNewFragment == null) clientNewFragment = new ClientNewFragment();
                    fa.add(R.id.fm_fragment, clientNewFragment, listdata);
                    fa.commit();

                }
            }

        } else {
                if (list.get(0) instanceof ClientMapFragment) {
                    if (clientMapFragment == null) clientMapFragment = new ClientMapFragment();
                    fa.replace(R.id.fm_fragment, clientMapFragment, mapdata);
                    fa.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fa.commitNowAllowingStateLoss();
                }else {
                    clientMapFragment = new ClientMapFragment();
                    fa.add(R.id.fm_fragment, clientMapFragment, mapdata);
                    fa.commit();
                }

            }

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getContentId() {
        return R.layout.sp_client_fragment;
    }
}
