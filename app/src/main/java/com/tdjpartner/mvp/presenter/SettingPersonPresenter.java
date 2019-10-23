package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.SettingPersonActivity;

import java.util.Map;

public class SettingPersonPresenter extends BasePresenter<Model, SettingPersonActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void managerList(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.managerList(map, new BaseObserver<SettingPerson>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(SettingPerson settingPerson) {
                getIView().managerList_Success(settingPerson);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().managerList_failed();
            }
        }));

    }
}
