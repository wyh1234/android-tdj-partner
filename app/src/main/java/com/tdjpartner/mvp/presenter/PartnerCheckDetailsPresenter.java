package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.PartnerCheckDetailsActivity;

import java.util.List;
import java.util.Map;

public class PartnerCheckDetailsPresenter extends BasePresenter<Model, PartnerCheckDetailsActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void verifyDetail(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.verifyDetail(map, new BaseObserver<PartnerCheckDetails>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(PartnerCheckDetails partnerCheckDetails) {


            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }
}
