package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class BindingBankActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_sel)
    RelativeLayout rl_sel;
    @OnClick({R.id.rl_sel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_sel:
                Intent intent=new Intent(this,BankListActivity.class);
                startActivity(intent);
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
        tv_title.setText("绑定银行卡");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binding_bank_layout;
    }
}
