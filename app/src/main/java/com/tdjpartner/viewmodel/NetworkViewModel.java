package com.tdjpartner.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.ParseException;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.ApiException;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.model.HotelAuditPageList;
import com.tdjpartner.model.IronDayAndMonthData;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.utils.GeneralUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by LFM on 2021/4/15.
 */
public class NetworkViewModel extends ViewModel {

    private Map<Class, MediatorLiveData> register;
    private CompositeDisposable compositeDisposable;

    public <D> MediatorLiveData<D> loadingWithNewLiveData(Class<D> dClass, @Nullable Map<String, Object> map) {
        loadData(dClass, map);

        if (getRegister().containsKey(dClass)) {
            return getRegister().get(dClass);
        }

        MediatorLiveData<D> mediatorLiveData = new MediatorLiveData<>();
        getRegister().put(dClass, mediatorLiveData);

        return mediatorLiveData;
    }

    public <D> void loading(Class<D> dClass, @Nullable Map<String, Object> map) {
        if (getRegister().containsKey(dClass)) {
            if (getRegister().get(dClass).hasObservers()) loadData(dClass, map);
        } else {
            LogUtils.e("请先使用loadingWithNewLiveData()注册LiveData，再加载数据");
            GeneralUtils.showToastshort("数据加载失败");
        }
    }

    private <D> void loadData(Class<D> dClass, @Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.loading(dClass, map).subscribe(this::onNext, this::onError));
    }

    @Override
    protected void onCleared() {
        System.out.println("~~" + getClass().getSimpleName() + ".onCleared~~");
        System.out.println(this);
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }

    public Map<Class, MediatorLiveData> getRegister() {
        if (register == null) register = new HashMap<>();
        return register;
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

    public <T> void onNext(T t) {
//        if (o instanceof HotelAuditInfo && getHotelAuditInfoLiveData().hasObservers()) {
//            getHotelAuditInfoLiveData().postValue((HotelAuditInfo) o);
//        } else if (o instanceof HotelAuditPageList && getHotelAuditPageListLiveData().hasObservers()) {
//            getHotelAuditPageListLiveData().postValue((HotelAuditPageList) o);
//        } else if (o instanceof IronStatisticsDetails && getIronStatisticsDetailsLiveData().hasObservers()) {
//            getIronStatisticsDetailsLiveData().postValue((IronStatisticsDetails) o);
//        } else if (o instanceof IronDayAndMonthData && getIronDayAndMonthDataLiveData().hasObservers()) {
//            getIronDayAndMonthDataLiveData().postValue((IronDayAndMonthData) o);
//        } else if (o instanceof String && gethotelAuditRejectLiveData().hasObservers()) {
//            gethotelAuditRejectLiveData().postValue((String) o);
//        } else {
//            GeneralUtils.showToastshort("操作失败，未知数据");
//        }

        if (getRegister().containsKey(t.getClass()) && getRegister().get(t.getClass()).hasObservers()) {
            getRegister().get(t.getClass()).postValue(t);
        } else {
            GeneralUtils.showToastshort("操作失败，未知数据");
        }
    }
}
