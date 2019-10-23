package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.PartnerCheckFragment;

import java.util.List;
import java.util.Map;

public class PartnerCheckPresenter extends BasePresenter<Model, PartnerCheckFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void verifyList(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.verifyList(map, new BaseObserver<PartnerCheck>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(PartnerCheck partnerCheckList) {
                getIView().verifyList_Success(partnerCheckList);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().verifyList_Failed();
            }
        }));

    }
}
