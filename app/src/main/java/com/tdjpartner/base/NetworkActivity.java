package com.tdjpartner.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.viewmodel.NetworkViewModel;
import com.tdjpartner.widget.ProgressDialog;

import butterknife.ButterKnife;

/**
 * Created by LFM on 2021/4/26.
 */
public abstract class NetworkActivity extends BaseActivity {

    private ProgressDialog mProgressDialog;
    private RxPermissions rxPermissions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Eyes.translucentStatusBar(this, true);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    public void showLoading() {
        if (mProgressDialog == null) mProgressDialog = ProgressDialog.createDialog(this);
        if (mProgressDialog.isShowing()) return;

        mProgressDialog.setMessage("加载中...");
        mProgressDialog.show();
    }

    public void showLoading(String s) {
        if (mProgressDialog == null) mProgressDialog = ProgressDialog.createDialog(this);
        if (mProgressDialog.isShowing()) return;

        mProgressDialog.setMessage(s);
        mProgressDialog.show();
    }

    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract int getLayoutId();

    public NetworkViewModel getVM(){
        return ViewModelProviders.of(this).get(NetworkViewModel.class);
    }

    public RxPermissions getRxPermissions() {
        if (rxPermissions == null) rxPermissions = new RxPermissions(this);
        return rxPermissions;
    }
}
