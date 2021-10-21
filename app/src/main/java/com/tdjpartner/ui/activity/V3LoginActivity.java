package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.LoginSucc;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class V3LoginActivity extends NetworkActivity {

    @OnClick({R.id.tv_iron, R.id.tv_net,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_net:
                login(1);
                break;
            case R.id.tv_iron:
                login(2);
                break;
            case R.id.iv_back:
                finish();
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



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(LoginSucc event){
        if (event.isLogin())
            this.finish();
    }
}
