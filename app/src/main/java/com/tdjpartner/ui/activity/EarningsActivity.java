package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.MyCountMoney;
import com.tdjpartner.mvp.presenter.EarningsPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class EarningsActivity extends BaseActivity<EarningsPresenter> {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_ky_money)
    TextView tv_ky_money;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_tx_money)
    TextView tv_tx_money;
    @BindView(R.id.btn)
    Button btn;
    @OnClick({R.id.btn_back,R.id.tv_right,R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_right:
                Intent intent=new Intent(this,WithdrawDetalisActivity.class);
                startActivity(intent);
                break;
            case R.id.btn:
                if (UserUtils.getInstance().getLoginBean()!=null){
                    if (!GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard())){
                        Intent intent1=new Intent(this,WithdrawActivity.class);
                        intent1.putExtra("tv_ky_money",tv_ky_money.getText().toString());
                        startActivity(intent1);
                    }else {
                        Intent intent2=new Intent(this,RealNameAuthenticationActivity.class);
                        startActivity(intent2);
                    }
                }

                break;
        }

    }
    @Override
    protected EarningsPresenter loadPresenter() {
        return new EarningsPresenter();
    }

    @Override
    protected void initData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.myCountMoney(map);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_right.setText("明细");
        tv_title.setText("收益");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.earnings_layout;
    }

    public void myCountMoneySuccess(MyCountMoney myCountMoney) {
        if (!GeneralUtils.isNullOrZeroLenght(myCountMoney.getCount())){
            tv_money.setText(myCountMoney.getCount());

        }
        if (!GeneralUtils.isNullOrZeroLenght(myCountMoney.getSurplusAmount())){
            tv_ky_money.setText(myCountMoney.getSurplusAmount());
        }
        if (!GeneralUtils.isNullOrZeroLenght(myCountMoney.getWithdrawalAmount())){
            tv_tx_money.setText(myCountMoney.getWithdrawalAmount());

        }

    }
}
