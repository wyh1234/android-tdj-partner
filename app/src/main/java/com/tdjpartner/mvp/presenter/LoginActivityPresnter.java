package com.tdjpartner.mvp.presenter;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.LoginActivity;
import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.MD5AndSHA;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class LoginActivityPresnter extends BasePresenter<Model, LoginActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void login(String username, String password, String type) {
        Map<String, Object> map_login = new HashMap<>();
        map_login.put("sourceType", "android");
        map_login.put("account", username);
        map_login.put("loginType", type);//PASSWORD_LOGIN密码登录；VER_CODE_LOGIN验证码登录
        if (type.equals("VER_CODE_LOGIN")) {
            map_login.put("smsCode", password);
        } else {
            map_login.put("password", MD5AndSHA.md5Encode(password));
        }
        getIView().addSubscribe(RequestPresenter.loginData(map_login, new BaseObserver<UserInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(UserInfo userInfo) {
                LogUtils.e(userInfo);
                getIView().getlogin(userInfo);

            }

            @Override
            protected void onFailed(Throwable e) {
            }

        }));


    }

    public void commodity_queryList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.commodity_queryList(map, new BaseObserver<IntegralShop>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(IntegralShop integralShop) {

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
