package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.DeleteSalesAppByEntityId;
import com.tdjpartner.model.RefundDetail;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.AfterSalesDetailActivity;

import java.util.Map;

public class AfterSalesDetailPresenter extends BasePresenter<Model, AfterSalesDetailActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void after_details_order(int id){
        getIView().addSubscribe(RequestPresenter.after_details_order(id,
                new BaseObserver<RefundDetail>(getIView().getContext(), true) {
                    @Override
                    protected void onSuccess(RefundDetail data) {
                        getIView().getRefundDetail_Success(data);

                    }

                    @Override
                    protected void onFailed(Throwable e) {

                    }
                }));

    }
    public void deleteSalesAppByEntityId(int entityId,int orderId,int orderItemId){
        getIView().addSubscribe(RequestPresenter.deleteSalesAppByEntityId(entityId,orderId,orderItemId,
                new BaseObserver<DeleteSalesAppByEntityId>(getIView().getContext(), true) {
                    @Override
                    protected void onSuccess(DeleteSalesAppByEntityId data) {
                        getIView().getDeleteSalesAppByEntityId_Success(data);

                    }

                    @Override
                    protected void onFailed(Throwable e) {

                    }
                }));

    }
}
