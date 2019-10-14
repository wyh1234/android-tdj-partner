package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.MessageActivity;

import java.util.List;
import java.util.Map;

public class MessagePersenter extends BasePresenter<Model, MessageActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void pushMessage(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.pushMessage(map, new BaseObserver<List<PartnerMessageInfo>>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(List<PartnerMessageInfo>  partnerMessageInfoList) {
                getIView().getPushMessage(partnerMessageInfoList);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
