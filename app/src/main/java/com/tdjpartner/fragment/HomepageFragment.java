package com.tdjpartner.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.mvp.presenter.HomepageFragmentPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;

import butterknife.BindView;

public class HomepageFragment extends BaseFrgment<HomepageFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected HomepageFragmentPresenter loadPresenter() {
        return new HomepageFragmentPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.homepage_fragment;
    }

    @Override
    public void onRefresh() {

    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }



}
