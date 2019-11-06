package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBankActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.binding_bank_iv)
    ImageView binding_bank_iv;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_accountno)
    TextView tv_accountno;
    @OnClick({R.id.binding_bank_iv,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.binding_bank_iv:
                Intent intent=new Intent(this,BindingBankActivity.class);
                intent.putExtra("mybank", getIntent().getSerializableExtra("bank"));
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
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
    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onEvent(Bank bankName) {
        tv_accountno.setText(bankName.getAccountNo().substring(bankName.getAccountNo().length()-4,bankName.getAccountNo().length()));

    }
    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("绑定银行卡");
       Bank bank= (Bank) getIntent().getSerializableExtra("bank");
        tv_accountno.setText(bank.getAccountNo().substring(bank.getAccountNo().length()-4,bank.getAccountNo().length()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_bank_layout;
    }
}
