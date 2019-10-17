package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.OrderDetailsActivity;

import java.util.Map;

public class OrderDetailsPresenter extends BasePresenter<Model, OrderDetailsActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void findOne(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.findOne(map, new BaseObserver<OrderDetail>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(OrderDetail orderList) {
                getIView().findOne_Success(orderList);


            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }
}
