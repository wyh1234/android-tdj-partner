package com.tdjpartner.ui.fragment;

import android.view.View;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.mvp.presenter.IPresenter;

public class ClientFragment  extends BaseFrgment {
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
    protected int getContentId() {
        return R.layout.client_fragment;
    }
}
