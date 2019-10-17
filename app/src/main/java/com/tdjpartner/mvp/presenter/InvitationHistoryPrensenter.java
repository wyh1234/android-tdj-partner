package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.InvitationHistoryActivity;

import java.util.Map;

public class InvitationHistoryPrensenter extends BasePresenter<Model, InvitationHistoryActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void myCustomerList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.myCustomerList(map, new BaseObserver<InvitationHistory>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(InvitationHistory invitationHistory) {
                getIView().myCustomerListSuccess(invitationHistory);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().myCustomerListfailed();

            }
        }));

    }
}
