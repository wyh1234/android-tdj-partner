package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.CustomerInfo;
import com.tdjpartner.model.DistanceInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.ClientListFragment;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;
import java.util.Map;

public class ClientListPresenter extends BasePresenter<Model, ClientListFragment> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void hotelMap(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.hotelMap(map, new BaseObserver<List<ClientInfo>>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(List<ClientInfo> clientInfoList) {
                getIView().hotelMap_Success(clientInfoList);
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

    public void internationalWaters(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.internationalWaters(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer dropOuting) {
                GeneralUtils.showToastshort("跟进成功请在24小时内进行拜访");
                getIView().internationalWatersSuccess();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getIView().hotelMap_failed();
            }

            @Override
            protected void onFailed(Throwable e) {getIView().hotelMap_failed();}
        }));

    }

    public void punchDistance(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.punchDistance(map, new BaseObserver<DistanceInfo>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(DistanceInfo distance) {
                getIView().punchDistanceSuccess(distance.distance);
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
