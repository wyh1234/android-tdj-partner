package com.tdjpartner.mvp.presenter;

import com.tdjpartner.mvp.view.IView;

/**
 * Created by LFM on 2021/3/19.
 */
public class IronAfterSalesPresenter implements IPresenter {
    @Override
    public void attachView(IView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public IView getIView() {
        return null;
    }

//    @Override
//    protected void loading() {
////        getIView().addSubscribe(
////                RequestPresenter.getafterSalesTask(map, new BaseObserver<AfterSaleInfoData>(getIView().getContext(), true) {
////                    @Override
////                    protected void onSuccess(AfterSaleInfoData afterSaleInfoData) {
////                        getIView().ok(afterSaleInfoData);
////                    }
////
////                    @Override
////                    protected void onFailed(Throwable e) {
////                    }
////                }));
//    }
}