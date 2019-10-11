package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class EarningsActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
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
                Intent intent1=new Intent(this,WithdrawActivity.class);
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
        tv_right.setText("明细");
        tv_title.setText("收益");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.earnings_layout;
    }
}
