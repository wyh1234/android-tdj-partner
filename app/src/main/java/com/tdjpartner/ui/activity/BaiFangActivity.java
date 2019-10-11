package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class BaiFangActivity extends BaseActivity {
    @BindView(R.id.rl_dk)
    RelativeLayout rl_dk;
    @BindView(R.id.rl_location)
    RelativeLayout rl_location;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private FollowUpPopuWindow followUpPopuWindow;
    @OnClick({R.id.rl_dk,R.id.rl_location,R.id.btn,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_dk:
                GeneralUtils.showToastshort("您已打卡成功");

                break;
            case R.id.rl_location:
                Intent intent=new Intent(this,CallLocationActivity.class);
                startActivity(intent);

                break;
            case R.id.btn:
                if (followUpPopuWindow!=null){
                    if (followUpPopuWindow.isShowing()){
                        return;
                    }
                    followUpPopuWindow.showPopupWindow();
                }else {

                    followUpPopuWindow = new FollowUpPopuWindow(this,"BAIFANG");
                    followUpPopuWindow.setDismissWhenTouchOutside(false);
                    followUpPopuWindow.setInterceptTouchEvent(false);
                    followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
                    followUpPopuWindow.showPopupWindow();
                }
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

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.baifang_layout;
    }
}
