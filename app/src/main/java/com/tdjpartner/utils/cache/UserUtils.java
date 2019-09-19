package com.tdjpartner.utils.cache;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tdjpartner.model.LoginInfo;


public class UserUtils {
    private static final String KEY_LOGIN_BEAN = "KEY_LOGIN_BEAN";

    private LoginInfo mLoginBean = null;

    private static class Holder {
        private static final UserUtils INSTANCE = new UserUtils();
    }

    public static UserUtils getInstance() {
        return Holder.INSTANCE;
    }

    private UserUtils() {
        getLoginBean();
    }

    public LoginInfo getLoginBean() {
        if (mLoginBean == null) {
            String json = SPUtils.getInstance().get(KEY_LOGIN_BEAN, "");
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = new Gson().fromJson(json, LoginInfo.class);
                } catch (Exception ignore) {
                }
            }
        }
        return mLoginBean;
    }

    public void login(LoginInfo loginBean) {
        mLoginBean = loginBean;
        String json = new Gson().toJson(loginBean);
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, json);
    }

    public void logout() {
        mLoginBean = null;
        SPUtils.getInstance().clear();
    }

    public void update(LoginInfo loginBean) {
        mLoginBean = loginBean;
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, mLoginBean);
    }

    public boolean isLogin() {
        LoginInfo loginBean = getLoginBean();
        if (loginBean == null) {
            return false;
        }
        if (loginBean.getId() > 0) {
            return true;
        }
        return false;
    }

    public boolean doIfLogin(Context context) {
        if (isLogin()) {
            return true;
        } else {
//            LoginActivity.start(context);
            return false;
        }
    }

}
