package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.CustomerInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.V3ClientListSeachFragment;

import java.util.Map;

public class V3ClientListSeachPresenter extends BasePresenter<Model, V3ClientListSeachFragment> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void listData(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.listData(map, new BaseObserver<CustomerInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(CustomerInfo customerInfo) {
                getIView().listData_Success(customerInfo);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getIView().hotelMap_failed();
            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().hotelMap_failed();
            }
        }));


    }
}
