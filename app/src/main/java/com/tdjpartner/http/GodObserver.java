package com.tdjpartner.http;

import android.content.Context;
import android.net.ParseException;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.tdjpartner.model.LoginLoseEfficacyEvent;
import com.tdjpartner.utils.ClickCheckedUtil;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.widget.ProgressDialog;

import org.apache.http.conn.ConnectTimeoutException;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 *
 * @author Administrator
 * @date 2018/6/13
 */

public abstract class GodObserver<T> implements Observer<T> {

    private String errorMsg;

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e);
        if(!TextUtils.isEmpty(errorMsg)){
            GeneralUtils.showToastshort( errorMsg);
        }else if(e instanceof ApiException){
            LogUtils.e(((ApiException) e).getCode());

            if (((ApiException) e).getCode()==4||((ApiException) e).getCode()==901){
                GeneralUtils.showToastshort(((ApiException) e).getMsg());
                if (ClickCheckedUtil.onClickChecked(1000))
                    EventBus.getDefault().post(new LoginLoseEfficacyEvent());
                return;
            }else if (((ApiException) e).getCode()==0){
                onNext(null);

            }else {
                GeneralUtils.showToastshort(((ApiException) e).getMsg());
            }

        }else if(e instanceof HttpException){
            GeneralUtils.showToastshort( "网络错误");
        }else if (e  instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSyntaxException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException){
            GeneralUtils.showToastshort( "数据解析错误");
        }else if (e instanceof ClassCastException) {
            GeneralUtils.showToastshort( "类型转换错误");
        } else if (e instanceof ConnectException) {
            GeneralUtils.showToastshort( "连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            GeneralUtils.showToastshort( "证书验证失败");
        } else if (e instanceof ConnectTimeoutException) {
            GeneralUtils.showToastshort( "连接超时");
        } else if (e instanceof java.net.SocketTimeoutException) {
            GeneralUtils.showToastshort( "连接超时");
        } else if (e instanceof UnknownHostException) {
            GeneralUtils.showToastshort( "无法解析该域名");
        } else if (e instanceof NullPointerException) {
            GeneralUtils.showToastshort( "NullPointerException");
        }else{
            GeneralUtils.showToastshort( "未知错误");
        }

        onFailed(e);
    }

    @Override
    public void onNext(T t) {

        if (t instanceof BaseResponse){
            LogUtils.e(((BaseResponse) t).isSuccess());
            if (((BaseResponse) t).isSuccess()){
                onSuccess(t);
            }else {
                ApiException apiException = new ApiException(((BaseResponse) t).getCode(),((BaseResponse) t).getMsg());
                onError(apiException);
            }

        }else {
            onSuccess(t);
        }
    }

    protected abstract void onSuccess(T t);
    protected abstract void onFailed(Throwable e);

}
