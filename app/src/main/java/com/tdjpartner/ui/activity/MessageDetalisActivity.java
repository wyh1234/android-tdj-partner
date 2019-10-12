package com.tdjpartner.ui.activity;

import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;

public class MessageDetalisActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
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
        tv_title.setText("详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_details_layout;
    }
}
