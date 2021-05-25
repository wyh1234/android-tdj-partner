package com.tdjpartner.model;

import java.util.List;

public class CustomerPhone {

    public int userId; //2023930,
    public boolean selected; //false,
    public String nickName; //"测3",
    public String password; //"14e1b600b1fd579f47433b88e8d85291",
    public String siteName; //"深圳",
    public String websiteId; //"95",
    public String phone; //"18200000002",
    public String accountCode; //"18257107777"

    @Override
    public String toString () {
        return "CustomerPhone{" +
                "userId=" + userId +
                ", selected=" + selected +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", siteName='" + siteName + '\'' +
                ", websiteId='" + websiteId + '\'' +
                ", phone='" + phone + '\'' +
                ", accountCode='" + accountCode + '\'' +
                '}';
    }
}
