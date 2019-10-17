package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.WithdrawPresnter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawActivity extends BaseActivity<WithdrawPresnter> {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv_bottom)
    TextView tv_bottom;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.ed_money)
    EditText ed_money;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @OnClick({R.id.btn_back,R.id.btn,R.id.rl})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
                if (GeneralUtils.isNullOrZeroLenght(ed_money.getText().toString())){
                    GeneralUtils.showToastshort("请输入提现金额");
                    return;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("capitalWithdrawal",new BigDecimal(ed_money.getText().toString()));
                mPresenter.cashWithdrawalFlow(map);

                break;
            case R.id.rl:
                ed_money.setText(getIntent().getStringExtra("tv_ky_money"));
                break;
        }
    }
    @Override
    protected WithdrawPresnter loadPresenter() {
        return new WithdrawPresnter();
    }

    @Override
    protected void initData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.bankAccount(map);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("提现");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.withdraw_layout;
    }

    public void bankAccountSuccess(Bank bank) {
        ImageLoad.loadImageViewLoding(bank.getCashImgUrl(),iv);
        tv_no.setText(bank.getBankName()+"******"+(bank.getAccountNo().substring(bank.getAccountNo().length()-4,bank.getAccountNo().length())));
        tv1.setText("当前最高可提现"+getIntent().getStringExtra("tv_ky_money")+"元");


    }

    public void cashWithdrawalFlowSuccess() {
        finish();
    }
}
