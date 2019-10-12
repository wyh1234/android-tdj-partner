package com.tdjpartner.ui.activity;

import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;

public class RealNameAuthenticationActivity extends BaseActivity {
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
        tv_title.setText("实名认证");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.realname_authentication_layout;
    }
}
