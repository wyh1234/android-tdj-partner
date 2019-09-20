package com.tdjpartner.http;

/**
 * Created by Administrator on 2018/3/24.
 */

public class BaseResponse<T> {
    private int err;
    private String msg;
    private T data;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int err) {
        this.err = err;
    }

    public int getCode() {
        return err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }


    public boolean isSuccess(){
        return err == 0;
    }
}
