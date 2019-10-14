package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tdjpartner.MainTabActivity;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.SettingPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> {
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
            case  R.id.rl_bank://判断是否实名，通过用户信息判断
                Map<String,Object> map=new HashMap<>();
                map.put("userId",25653);
                mPresenter.bankAccount(map);

                break;
            case R.id.rl_modification:
                Intent intent1=new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent1);
                    break;
        }
    }
    @Override
    protected SettingPresenter loadPresenter() {
        return new SettingPresenter();
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

    public void bankAccountSuccess(Bank bank) {
        if (GeneralUtils.isNullOrZeroLenght(bank.getAccountNo())){
            Intent intent=new Intent(this,BindingBankActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(this,MyBankActivity.class);
            intent.putExtra("bank",bank);
            startActivity(intent);
        }


    }
}
