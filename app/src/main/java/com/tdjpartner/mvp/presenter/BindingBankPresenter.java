package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.BindingBankActivity;

import java.util.List;
import java.util.Map;

public class BindingBankPresenter extends BasePresenter<Model, BindingBankActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void addBankAccount(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.addBankAccount(map, new BaseObserver<Bank>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Bank bank) {

                getIView().addBankAccountSuccess(bank);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
