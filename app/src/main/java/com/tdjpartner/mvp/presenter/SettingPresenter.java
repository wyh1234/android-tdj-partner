package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.LoginLoseEfficacyEvent;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.SettingActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class SettingPresenter extends BasePresenter<Model, SettingActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void bankAccount(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.bankAccount(map, new BaseObserver<Bank>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Bank bank) {
                getIView().bankAccountSuccess(bank);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
    public void pushMessageLogout(){
        getIView().addSubscribe(RequestPresenter.pushMessageLogout(0, UserUtils.getInstance().getLoginBean().getPhoneNumber(), new BaseObserver<Object>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Object object) {
                if ((Boolean) object){
                    EventBus.getDefault().post(new LoginLoseEfficacyEvent());

                }


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

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
