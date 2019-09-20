package com.tdjpartner;

import android.content.Context;
import android.content.Intent;

import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.LoginActivityPresnter;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity<LoginActivityPresnter> {

    @Override
    protected LoginActivityPresnter loadPresenter() {
        return new LoginActivityPresnter();
    }

    @Override
    protected void initData() {
        mPresenter.login("12345676001","123456","0");

    }

    @Override
    protected void initView() {

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
        Map<String,Object> map=new HashMap<>();
        map.put("lim","10");
        map.put("offs","1");
        map.put("userId",userInfo.getEmpRole()==0?userInfo.getEntityId()+"":userInfo.getLoginUserId()+"");
//        map.put("userId",PublicCache.loginPurchase.getEmpRole()==0?PublicCache.loginPurchase.getEntityId():PublicCache.loginPurchase.getSubUserId());
        map.put("name","");
        map.put("sort","");
        map.put("status","1");
        mPresenter.commodity_queryList(map);

    }
}
