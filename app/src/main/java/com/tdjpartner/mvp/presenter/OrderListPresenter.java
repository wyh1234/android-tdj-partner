package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.OrderListActivity;

import java.util.Map;

public class OrderListPresenter extends BasePresenter<Model, OrderListActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void order_pageList(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.order_pageList(map, new BaseObserver<OrderList>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(OrderList orderList) {
                getIView().order_pageList_Success(orderList);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().order_pageList_Failed();
            }
        }));

    }
}
