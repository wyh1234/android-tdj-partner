package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.ClientDetailsActivity;

import java.util.List;
import java.util.Map;

public class ClientDetailsPresenter extends BasePresenter<Model, ClientDetailsActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void client_details(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.client_details(map, new BaseObserver<ClientDetails>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(ClientDetails clientDetails) {
                getIView().client_details_Success(clientDetails);



            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

}
