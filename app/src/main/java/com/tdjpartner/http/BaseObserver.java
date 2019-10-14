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

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 *
 * @author Administrator
 * @date 2018/6/13
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private String errorMsg;
    private boolean isShowErrorView;
    private Context mContext;
    private boolean isShowProgressDialo;

    private ProgressDialog mProgressDialog;



    @Override
    protected void onStart() {
        super.onStart();
        if (isShowProgressDialo){
            showLoading();
        }



    }

    public BaseObserver(Context mContext){
        this.mContext = mContext;
    }

    public BaseObserver(Context mContext,boolean isShowProgressDialo){
        this.mContext = mContext;
        this.isShowProgressDialo=isShowProgressDialo;
    }
    public BaseObserver(Context mContext, String errorMsg){
        this.mContext = mContext;
        this.errorMsg = errorMsg;
    }

    public BaseObserver(Context mContext, String errorMsg, boolean isShowErrorView){
        this.mContext = mContext;
        this.errorMsg = errorMsg;
        this.isShowErrorView = isShowErrorView;
    }

    @Override
    public void onComplete() {
        dismissLoading();


    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e);
        if(!TextUtils.isEmpty(errorMsg)){
            GeneralUtils.showToastshort( errorMsg);
        }else if(e instanceof ApiException){
            LogUtils.e(((ApiException) e).getCode());
            GeneralUtils.showToastshort(((ApiException) e).getMsg());
            if (((ApiException) e).getCode()==4||((ApiException) e).getCode()==901){
                if (ClickCheckedUtil.onClickChecked(1000))
                    EventBus.getDefault().post(new LoginLoseEfficacyEvent());
                return;
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
        dismissLoading();
    }

    @Override
    public void onNext(T t) {
        dismissLoading();
        LogUtils.e(t);
        LogUtils.e(t instanceof BaseResponse);
        if (t instanceof BaseResponse){
            LogUtils.e(((BaseResponse) t).isSuccess());
//            LogUtils.i(((BaseResponse) t).isSuccess());
            if (((BaseResponse) t).isSuccess()){
//                if (((BaseResponse) t).getPageinfo()!=null){
//                  onSuccess((BaseResponse<T>) t);
                onSuccess(t);
             /*   }else {
                    onSuccess((BaseResponse<T>) ((BaseResponse) t).getData());
                }*/
            }else {
                ApiException apiException = new ApiException(((BaseResponse) t).getCode(),((BaseResponse) t).getMsg());
                onError(apiException);
            }


        }else {
            LogUtils.e(t);
//            onSuccess((BaseResponse<T>) t);
            onSuccess(t);
        }



    }
    protected abstract void onSuccess(T t);
//    protected abstract void onSuccess(BaseResponse<T> t);
    protected abstract void onFailed(Throwable e);

    private void showLoading(){
        if(mProgressDialog == null){
            mProgressDialog = ProgressDialog.createDialog(mContext);
            mProgressDialog.setMessage("加载中...");
            mProgressDialog.show();
        }
    }

    private void dismissLoading(){
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
}
