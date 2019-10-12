package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tdjpartner.MainTabActivity;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.rl_bank)
    RelativeLayout rl_bank;
    @BindView(R.id.rl_modification)
    RelativeLayout rl_modification;
    @OnClick({R.id.btn_back,R.id.rl_bank,R.id.rl_modification})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case  R.id.rl_bank:
                Intent intent=new Intent(this,MyBankActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_modification:
                Intent intent1=new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent1);
                    break;
        }
    }
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.setting_layout;
    }
}
