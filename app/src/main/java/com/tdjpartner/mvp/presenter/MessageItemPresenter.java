package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.MessageItemActivity;

import java.util.List;
import java.util.Map;

public class MessageItemPresenter extends BasePresenter<Model, MessageItemActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void pushMessage_item(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.pushMessage_item(map, new BaseObserver<PartnerMessageItemInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(PartnerMessageItemInfo partnerMessageItemInfo) {
                getIView().getpushMessage_item(partnerMessageItemInfo);

            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().getpushMessage_item_failed();

            }
        }));

    }
}
