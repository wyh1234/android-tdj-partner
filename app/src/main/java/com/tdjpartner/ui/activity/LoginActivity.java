package com.tdjpartner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.MainTabActivity;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.presenter.LoginActivityPresnter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginActivityPresnter> {
    @BindView(R.id.iv_way_password)
    ImageView iv_way_password;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.forget_password)
    TextView forget_password;
    @OnClick({R.id.iv_way_password,R.id.btn_login,R.id.forget_password})
    public void onClick(View view){
        switch (view.getId()){
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
            case R.id.btn_login:
                if (GeneralUtils.isNullOrZeroLenght(ed_phone.getText().toString())){
                  GeneralUtils.showToastshort("手机号不能为空");
               }
                if (!GeneralUtils.isTel(ed_phone.getText().toString())){
                    GeneralUtils.showToastshort("您输入的手机号码格式不正确");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_password.getText().toString())){
                    GeneralUtils.showToastshort("密码不能为空");
                    return;
                }
                mPresenter.login(ed_phone.getText().toString(),ed_password.getText().toString(),"0");
                break;
            case R.id.forget_password:
                Intent intent=new Intent(this, ForgetPasswordActivity.class);
                intent.putExtra("forgetpassword","forgetpassword");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected LoginActivityPresnter loadPresenter() {
        return new LoginActivityPresnter();
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        Eyes.setStatusBarColor(this, GeneralUtils.getColor(this, R.color.white));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout;
    }

    public static void start(Context context) {
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);

    }

    public Context getContext() {
        return this;
    }

    public void getlogin(UserInfo userInfo) {
        if (userInfo!=null){
            UserUtils.getInstance().login(userInfo);
            Intent intent=new Intent(this, MainTabActivity.class);
            startActivity(intent);
            finish();
        }
/*        Map<String,Object> map=new HashMap<>();
        map.put("lim","10");
        map.put("offs","1");
        map.put("userId",userInfo.getEmpRole()==0?userInfo.getEntityId()+"":userInfo.getLoginUserId()+"");
//        map.put("userId",PublicCache.loginPurchase.getEmpRole()==0?PublicCache.loginPurchase.getEntityId():PublicCache.loginPurchase.getSubUserId());
        map.put("name","");
        map.put("sort","");
        map.put("status","1");
        mPresenter.commodity_queryList(map);*/

    }
}
