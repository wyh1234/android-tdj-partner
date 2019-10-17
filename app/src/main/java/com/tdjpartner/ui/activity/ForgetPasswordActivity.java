package com.tdjpartner.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.LoginLoseEfficacyEvent;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.presenter.ForgetPasswordPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.iv_way_password)
    ImageView iv_way_password;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.tv_code)
    TextView tv_code;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Handler handler = GeneralUtils.getHandler();
    private int datetime = 60;

    Runnable runable = new Runnable() {
        @Override
        public void run() {
            // TODO 自动生成的方法存根
            if (datetime == 0) {
                tv_code.setText("点击获取");

                tv_code.setEnabled(true);
                datetime = 60;
                return;
            } else {
                tv_code.setEnabled(false);
            }
            tv_code.setText("已发送(" + datetime + "s)");
            handler.postDelayed(runable, 1000);
            datetime--;
        }
    };
    @OnClick({R.id.btn_back,R.id.iv_way_password,R.id.tv_code,R.id.btn_save})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_way_password:
                if (iv_way_password.isSelected()){
                    iv_way_password.setSelected(false);
                    ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());//显示
                }else {
                    ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//隐藏
                    iv_way_password.setSelected(true);
                }
                ed_password.setSelection(ed_password.getText().length());
                break;
            case R.id.tv_code:
                if (GeneralUtils.isNullOrZeroLenght(ed_phone.getText().toString())){
                    GeneralUtils.showToastshort("手机号不能为空");
                }
                if (!GeneralUtils.isTel(ed_phone.getText().toString())){
                    GeneralUtils.showToastshort("您输入的手机号码格式不正确");
                    return;
                }
                mPresenter.smsCode(ed_phone.getText().toString());

                break;
            case R.id.btn_save:

                if (GeneralUtils.isNullOrZeroLenght(ed_phone.getText().toString())){
                    GeneralUtils.showToastshort("手机号不能为空");
                }
                if (!GeneralUtils.isTel(ed_phone.getText().toString())){
                    GeneralUtils.showToastshort("您输入的手机号码格式不正确");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_code.getText().toString())){
                    GeneralUtils.showToastshort("请您输入短信验证码");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_password.getText().toString())){
                    GeneralUtils.showToastshort("请您输入新密码");
                    return;
                }
                mPresenter.forget_pwd(ed_phone.getText().toString(),ed_password.getText().toString(),ed_code.getText().toString());

                break;
        }
    }
    @Override
    protected ForgetPasswordPresenter loadPresenter() {
        return new ForgetPasswordPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        Eyes.setLightStatusBar(this,true);
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setImageResource(R.mipmap.back);
        toolbar.setBackgroundResource(R.color.white);
        if (getIntent().getStringExtra("forgetpassword")!=null){
            tv_title.setText("忘记密码");

        }else {
            tv_title.setText("修改密码");
        }

        tv_title.setTextColor(GeneralUtils.getColor(this,R.color.gray_66));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.forgetpassword_layout;
    }

    public Context getContext() {
        return this;
    }

    public void smsCode_Success() {
        handler.post(runable);
        tv_code.setText("已发送(" + datetime + "s)");
        GeneralUtils.showToastshort("短信发送成功");
    }

    public void forget_pwd_Success() {
        EventBus.getDefault().post(new LoginLoseEfficacyEvent());

    }


}
