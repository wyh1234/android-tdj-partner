package com.tdjpartner.model;

public class LoginSucc {
    private boolean isLogin = false;

    public LoginSucc(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
