package com.tdjpartner.common;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.http.RetrofitServiceManager;
import com.tdjpartner.http.RxUtils;
import com.tdjpartner.model.HomePageFuncationButton;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestPresenter {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static ApiService getApiService(){
       return RetrofitServiceManager.getInstance().creat(ApiService.class);
    }


    public  static Disposable commendCategory(BaseObserver<List<HomePageFuncationButton>> baseObserver){
        return getApiService().commendCategory(1).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }
    public  static Disposable get_rentinglist(BaseObserver<RentingInfos> baseObserver, Map<String, Object> map){
        return getApiService().get_rentinglist(map).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }
    public  static Disposable commodity_queryList(Map<String, Object> map,BaseObserver<IntegralShop> baseObserver){
        return getApiService().commodity_queryList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    //采购商登录
    public static Disposable loginData(Map<String, Object> params,BaseObserver<UserInfo> baseObserver) {
        return getApiService().loginData(params).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }


    public static RequestBody  jsonData(Map<String ,Object> map){
        map.put("versionType", "Android");
        map.put("versionCode", String.valueOf(GeneralUtils.getVersionCode()));
        map.put("versionName",GeneralUtils.getVersionName());
        map.put("uniqueId",GeneralUtils.getAndroidId());
        LogUtils.e(map);
        return RequestBody.create(JSON, new Gson().toJson(map));
    }

}
