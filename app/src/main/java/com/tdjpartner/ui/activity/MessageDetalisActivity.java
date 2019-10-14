package com.tdjpartner.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageDetalisActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv)
    ImageView iv;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
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

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("详情");
        tv.setText(getIntent().getStringExtra("Content"));
        ImageLoad.loadImageViewLoding(getIntent().getStringExtra("imageurl"),iv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_details_layout;
    }
}
