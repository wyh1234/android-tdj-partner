package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.SettingActivity;
import com.tdjpartner.utils.GeneralUtils;

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
}
