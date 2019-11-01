package com.tdjpartner.mvp.presenter;

import com.tdjpartner.MainTabActivity;
import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.mvp.model.Model;

import java.util.HashMap;
import java.util.Map;

public class MainTabPresenter extends BasePresenter<Model, MainTabActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void version_check(){
        Map<String,Object> map=new HashMap<>();
        map.put("type","android");
        getIView().addSubscribe(RequestPresenter.version_check(map, new BaseObserver<AppVersion>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(AppVersion appVersion) {
                getIView().version_check_success(appVersion);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

}
