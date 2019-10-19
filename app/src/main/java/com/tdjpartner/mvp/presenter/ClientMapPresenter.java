package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.ClientMapFragment;

import java.util.List;
import java.util.Map;

public class ClientMapPresenter extends BasePresenter<Model, ClientMapFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void hotelMap(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.hotelMap(map, new BaseObserver<List<ClientInfo>>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(List<ClientInfo> clientInfoList) {
                getIView().hotelMap_Success(clientInfoList);



            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
