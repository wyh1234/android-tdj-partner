package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.BankListActivity;

import java.util.List;
import java.util.Map;

public class BankListPresneter extends BasePresenter<Model, BankListActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void selectBankInfoList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.selectBankInfoList(map, new BaseObserver<List<BankList>>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(List<BankList> bank) {
                getIView().selectBankInfoListSuccess(bank);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
