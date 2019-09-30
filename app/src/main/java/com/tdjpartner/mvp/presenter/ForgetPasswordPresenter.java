package com.tdjpartner.mvp.presenter;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.ForgetPasswordActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.MD5AndSHA;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordPresenter extends BasePresenter<Model, ForgetPasswordActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void smsCode(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("telephone", username);
        map.put("smsType", 3);//1注册，2登陆，3忘记密码
        map.put("userType", 0);//1注册，2登陆，3忘记密码
        getIView().addSubscribe(RequestPresenter.smsCode(map, new BaseObserver<Object>(getIView().getContext(),true) {
            @Override
            protected void onSuccess(Object o) {
                getIView().smsCode_Success();

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
    public void forget_pwd(String username,String password,String smsCode){
        //customer采购商
       Map<String,Object> map_pamms = new HashMap<>();
        map_pamms.put("account", username);
        map_pamms.put("smsCode", smsCode);
        map_pamms.put("uniqueId", GeneralUtils.getAndroidId());
        map_pamms.put("newPassword", MD5AndSHA.md5Encode(password));
        getIView().addSubscribe(RequestPresenter.forget_pwd(map_pamms, new BaseObserver<Object>(getIView().getContext(),true) {
            @Override
            protected void onSuccess(Object o) {
                getIView().forget_pwd_Success();
            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

    public void login(String username, String password, String type) {
        Map<String, Object> map_login = new HashMap<>();
        map_login.put("sourceType", "android");
        map_login.put("account", username);
        map_login.put("loginType", type);//PASSWORD_LOGIN密码登录；VER_CODE_LOGIN验证码登录
        map_login.put("password", MD5AndSHA.md5Encode(password));
        getIView().addSubscribe(RequestPresenter.loginData(map_login, new BaseObserver<UserInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(UserInfo userInfo) {
                LogUtils.e(userInfo);

            }

            @Override
            protected void onFailed(Throwable e) {
            }

        }));


    }
}
