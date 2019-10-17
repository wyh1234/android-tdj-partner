package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.ToMakeMoneyActivity;

import java.util.Map;

public class ToMakeMoneyPresenter extends BasePresenter<Model, ToMakeMoneyActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void amountAnalysisRecords(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.amountAnalysisRecords(map, new BaseObserver<ToMakeMoney>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(ToMakeMoney toMakeMoney) {
                getIView().amountAnalysisRecordsSuccess(toMakeMoney);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
