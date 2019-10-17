package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.WithdrawDetalisActivity;

import java.util.List;
import java.util.Map;

public class WithdrawDetalisPresenter extends BasePresenter<Model, WithdrawDetalisActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void findCashWithdrawalFlowList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.findCashWithdrawalFlowList(map, new BaseObserver<WithdrawDetalis>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(WithdrawDetalis  withdrawDetalis) {
                getIView().getWithdrawDetailsSuccess(withdrawDetalis);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().getpushMessage_item_failed();

            }
        }));

    }
}
