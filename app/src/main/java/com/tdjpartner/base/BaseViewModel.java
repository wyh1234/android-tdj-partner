package com.tdjpartner.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.ParseException;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.ApiException;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.http.RxUtils;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.LoginLoseEfficacyEvent;
import com.tdjpartner.utils.ClickCheckedUtil;
import com.tdjpartner.utils.GeneralUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Created by LFM on 2021/3/20.
 */
public abstract class BaseViewModel<T> extends ViewModel {
    private Disposable disposable;

    private MediatorLiveData<T> liveData;

    @Override
    protected void onCleared() {
        System.out.println("~~" + getClass().getSimpleName() + ".onCleared~~");
        System.out.println(this);
        if(disposable != null) disposable.dispose();
    }

    public MediatorLiveData<T> getData() {
        if (liveData == null) {
            liveData = new MediatorLiveData<>();
        }
        return liveData;
    }

    public MediatorLiveData<T> loading(@Nullable Map<String, Object> map){
        if(disposable != null) disposable.isDisposed();
        disposable = loadData(map).subscribe(this::onNext, this::onError);
        return getData();
    }

    protected abstract Observable<T> loadData(@Nullable Map<String, Object> map);

    public void onNext(T t) {
        liveData.postValue(t);
    }

    public void onError(Throwable e) {
        LogUtils.e(e);

        if (e instanceof ApiException) {
            //处理API错误
//            LogUtils.e(((ApiException) e).getCode());
//
//            if (((ApiException) e).getCode() == 4 || ((ApiException) e).getCode() == 901) {
//                GeneralUtils.showToastshort(((ApiException) e).getMsg());
//                if (ClickCheckedUtil.onClickChecked(1000))
//                    EventBus.getDefault().post(new LoginLoseEfficacyEvent());
//                return;
//            } else if (((ApiException) e).getCode() == 0) {
////                onNext(null);
//            } else {
//                GeneralUtils.showToastshort(((ApiException) e).getMsg());
//            }

        } else if (e instanceof HttpException) {
            GeneralUtils.showToastshort("网络错误");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSyntaxException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            GeneralUtils.showToastshort("数据解析错误");
        } else if (e instanceof ClassCastException) {
            GeneralUtils.showToastshort("类型转换错误");
        } else if (e instanceof ConnectException) {
            GeneralUtils.showToastshort("连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            GeneralUtils.showToastshort("证书验证失败");
        } else if (e instanceof ConnectTimeoutException) {
            GeneralUtils.showToastshort("连接超时");
        } else if (e instanceof java.net.SocketTimeoutException) {
            GeneralUtils.showToastshort("连接超时");
        } else if (e instanceof UnknownHostException) {
            GeneralUtils.showToastshort("无法解析该域名");
        } else if (e instanceof NullPointerException) {
            GeneralUtils.showToastshort("NullPointerException");
        } else {
            GeneralUtils.showToastshort("未知错误");
        }
    }
}
