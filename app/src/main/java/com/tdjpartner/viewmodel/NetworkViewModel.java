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
import java.util.Map;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by LFM on 2021/4/15.
 */
public class NetworkViewModel extends ViewModel {


    private CompositeDisposable compositeDisposable;
    private MediatorLiveData<HotelAuditInfo> hotelAuditInfoLiveData;
    private MediatorLiveData<HotelAuditPageList> hotelAuditPageListLiveData;
    private MediatorLiveData<IronStatisticsDetails> ironStatisticsDetailsLiveData;
    private MediatorLiveData<IronDayAndMonthData> ironDayAndMonthDataLiveData;
    private MediatorLiveData<String> hotelAuditRejectLiveData;

    public MediatorLiveData<HotelAuditInfo> getHotelAuditInfoLiveData() {
        if (hotelAuditInfoLiveData == null) hotelAuditInfoLiveData = new MediatorLiveData<>();
        return hotelAuditInfoLiveData;
    }

    public void loadHotelAuditInfo(@Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.hotelAuditInfo(map).subscribe(this::onNext, this::onError));
    }

    public MediatorLiveData<HotelAuditPageList> getHotelAuditPageListLiveData() {
        if (hotelAuditPageListLiveData == null) hotelAuditPageListLiveData = new MediatorLiveData<>();
        return hotelAuditPageListLiveData;
    }

    public void loadHotelAuditPageList(@Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.hotelAuditPageListObservable(map).subscribe(this::onNext, this::onError));
    }

    public MediatorLiveData<IronStatisticsDetails> getIronStatisticsDetailsLiveData() {
        if (ironStatisticsDetailsLiveData == null) ironStatisticsDetailsLiveData = new MediatorLiveData<>();
        return ironStatisticsDetailsLiveData;
    }

    public void loadIronStatisticsDetails(@Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.ironStatisticsDetails(map).subscribe(this::onNext, this::onError));
    }

    public MediatorLiveData<IronDayAndMonthData> getIronDayAndMonthDataLiveData() {
        if (ironDayAndMonthDataLiveData == null) ironDayAndMonthDataLiveData = new MediatorLiveData<>();
        return ironDayAndMonthDataLiveData;
    }

    public void loadIronDayAndMonthData(@Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.ironDayAndMonthData(map).subscribe(this::onNext, this::onError));
    }

    public MediatorLiveData<String> gethotelAuditRejectLiveData() {
        if (hotelAuditRejectLiveData == null) hotelAuditRejectLiveData = new MediatorLiveData<>();
        return hotelAuditRejectLiveData;
    }
    public void posthotelAuditReject(@Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.hotelAuditReject(map).subscribe(this::onNext, this::onError));
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

    public void onNext(Object o) {
        if (o instanceof HotelAuditInfo && getHotelAuditInfoLiveData().hasObservers()) {
            getHotelAuditInfoLiveData().postValue((HotelAuditInfo) o);
        } else if (o instanceof HotelAuditPageList && getHotelAuditPageListLiveData().hasObservers()) {
            getHotelAuditPageListLiveData().postValue((HotelAuditPageList) o);
        } else if (o instanceof IronStatisticsDetails && getIronStatisticsDetailsLiveData().hasObservers()) {
            getIronStatisticsDetailsLiveData().postValue((IronStatisticsDetails) o);
        }  else if (o instanceof IronDayAndMonthData && getIronDayAndMonthDataLiveData().hasObservers()) {
            getIronDayAndMonthDataLiveData().postValue((IronDayAndMonthData) o);
        }  else if (o instanceof String && gethotelAuditRejectLiveData().hasObservers()) {
            gethotelAuditRejectLiveData().postValue((String) o);
        } else {
            GeneralUtils.showToastshort("操作失败，未知数据");
        }

    }
}
