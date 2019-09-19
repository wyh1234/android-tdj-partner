package com.tdjpartner.common;

import com.google.gson.Gson;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.RetrofitServiceManager;
import com.tdjpartner.http.RxUtils;
import com.tdjpartner.model.HomePageFuncationButton;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;
import java.util.Map;

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
    public  static Disposable get_rentinglistS(BaseObserver<RentingInfos> baseObserver, Map<String, Object> map){
        return getApiService().get_rentinglistS(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static RequestBody  jsonData(Map<String ,Object> map){
        map.put("versionType", "Android");
        map.put("versionCode", String.valueOf(GeneralUtils.getVersionCode()));
        map.put("versionName",GeneralUtils.getVersionName());
        map.put("uniqueId",GeneralUtils.getAndroidId());
        return RequestBody.create(JSON, new Gson().toJson(map));
    }

}
