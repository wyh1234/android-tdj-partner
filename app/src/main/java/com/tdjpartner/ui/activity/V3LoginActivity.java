package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class V3LoginActivity extends NetworkActivity {

    @OnClick({R.id.tv_iron, R.id.tv_net})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_net:
                login(1);
                break;
            case R.id.tv_iron:
                login(2);
                break;
        }
    }

    private void login(int type) {
        Intent intent = new Intent(V3LoginActivity.this, LoginActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.v3_login_activity;
    }


}
