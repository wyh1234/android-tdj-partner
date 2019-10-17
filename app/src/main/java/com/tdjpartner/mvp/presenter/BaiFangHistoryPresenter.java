package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.BaiFangHistoryFragment;

import java.util.Map;

public class BaiFangHistoryPresenter extends BasePresenter<Model, BaiFangHistoryFragment> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void call_list(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.call_list(map, new BaseObserver<BaiFangHistory>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(BaiFangHistory baiFangHistory) {
                getIView().call_list_Success(baiFangHistory);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().call_list_Failed();
            }
        }));

    }
}
