package com.tdjpartner.utils.cache;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tdjpartner.AppAplication;
import com.tdjpartner.http.cookie.PersistentCookieStore;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.ui.activity.LoginActivity;


public class UserUtils {
    private static final String KEY_LOGIN_BEAN = "KEY_LOGIN_BEAN";

    private UserInfo mLoginBean = null;

    private static class Holder {
        private static final UserUtils INSTANCE = new UserUtils();
    }

    public static UserUtils getInstance() {
        return Holder.INSTANCE;
    }

    private UserUtils() {
        getLoginBean();
    }

    public UserInfo getLoginBean() {
        if (mLoginBean == null) {
            String json = SPUtils.getInstance().get(KEY_LOGIN_BEAN, "");
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = new Gson().fromJson(json, UserInfo.class);
                } catch (Exception ignore) {
                }
            }
        }
        return mLoginBean;
    }

    public void login(UserInfo loginBean) {
        mLoginBean = loginBean;
        String json = new Gson().toJson(loginBean);
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, json);

    }

    public void logout() {
        mLoginBean = null;
        SPUtils.getInstance().clear();
        new PersistentCookieStore(AppAplication.getAppContext()).removeAll();//清空cookies
    }

    public void update(UserInfo loginBean) {
        mLoginBean = loginBean;
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, mLoginBean);
    }

    public boolean isLogin() {
        UserInfo loginBean = getLoginBean();
        if (loginBean == null) {
            return false;
        }
        if (loginBean.getEntityId() > 0) {
            return true;
        }
        return false;
    }

    public boolean doIfLogin(Context context) {
        if (isLogin()) {
            return true;
        } else {
            LoginActivity.start(context);
            return false;
        }
    }

}
