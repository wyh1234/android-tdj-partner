package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.ClientSeachInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.ClientListSeachFragment;

import java.util.List;
import java.util.Map;

public class ClientListSeachPresenter extends BasePresenter<Model, ClientListSeachFragment> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void customer_hotelMap(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.customer_hotelMap(map, new BaseObserver<ClientSeachInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(ClientSeachInfo clientInfoList) {
                getIView().hotelMap_Success(clientInfoList);



            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().hotelMap_failed();

            }
        }));

    }
}
