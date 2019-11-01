package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.mvp.presenter.BindingBankPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class BindingBankActivity extends BaseActivity<BindingBankPresenter> {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_sel)
    RelativeLayout rl_sel;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_idcard)
    TextView tv_idcard;
    @BindView(R.id.ed_accountno)
    EditText ed_accountno;
    @BindView(R.id.tv_bankname)
    TextView tv_bankname;
    @BindView(R.id.btn)
    Button btn;

    @OnClick({R.id.rl_sel,R.id.btn_back,R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_sel:
                Intent intent=new Intent(this,BankListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
                if (GeneralUtils.isNullOrZeroLenght(tv_bankname.getText().toString())){
                    GeneralUtils.showToastshort("请选择开户银行");
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_accountno.getText().toString())){
                    GeneralUtils.showToastshort("请输入银行卡号");
                }
                Map<String,Object> map=new HashMap<>();
                map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("cityId",UserUtils.getInstance().getLoginBean().getSite());//城市id
                map.put("accountNo",ed_accountno.getText().toString());
                map.put("partnerName",tv_username.getText().toString());
                map.put("partnerPhone",tv_phone.getText().toString());
                map.put("bankName",tv_bankname.getText().toString());
                map.put("bankAddress","");
                map.put("idCard",tv_idcard.getText().toString());
                mPresenter.addBankAccount(map);
                break;
        }
    }
    @Override
    protected BindingBankPresenter loadPresenter() {
        return new BindingBankPresenter();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("绑定银行卡");
        if (getIntent().getSerializableExtra("mybank")!=null){
            Bank bank= (Bank) getIntent().getSerializableExtra("mybank");
            tv_phone.setText(bank.getPartnerPhone());
            tv_username.setText(bank.getPartnerName());
            tv_idcard.setText(bank.getIdCard());
            tv_bankname.setText(bank.getBankName());
            ed_accountno.setText(bank.getAccountNo());
        }else {
            tv_phone.setText(UserUtils.getInstance().getLoginBean().getPhoneNumber());
            tv_username.setText(UserUtils.getInstance().getLoginBean().getRealname());
            tv_idcard.setText(UserUtils.getInstance().getLoginBean().getIdCard());
        }

    }

    @Subscribe
    public void onEvent(BankList bankName) {
        LogUtils.e(bankName);
        tv_bankname.setText(bankName.getBankName());

    }
    @Override
    protected int getLayoutId() {
        return R.layout.binding_bank_layout;
    }

    public void addBankAccountSuccess(Bank bank) {
        GeneralUtils.showToastshort("绑定银行卡成功");
        if (getIntent().getStringExtra("mybank")!=null){
            EventBus.getDefault().post(bank);
        }

        finish();
    }
}
