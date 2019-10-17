package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.GoodsAndStoreFragment;

import java.util.Map;

public class GoodsAndStorePresenter extends BasePresenter<Model, GoodsAndStoreFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void collect_products(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.collect_products(map, new BaseObserver<GoodsInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(GoodsInfo goodsInfo) {
                getIView().collect_products_Success(null,goodsInfo);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().collect_products_Failed();
            }
        }));

    }

    public void collect_stores(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.collect_stores(map, new BaseObserver<StoreInfo>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(StoreInfo storeInfo) {
                getIView().collect_products_Success(storeInfo,null);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().collect_products_Failed();
            }
        }));

    }

}
