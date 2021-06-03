package com.tdjpartner.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MemberStatisticsActivity extends NetworkActivity {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.team_statistics_layout;
    }
}
