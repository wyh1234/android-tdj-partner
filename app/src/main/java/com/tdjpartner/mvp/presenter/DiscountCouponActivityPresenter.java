package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.CouponsStatistics;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.DiscountCouponActivity;

import java.util.Map;

public class DiscountCouponActivityPresenter  extends BasePresenter<Model, DiscountCouponActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void coupons_statistics(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.coupons_statistics(map, new BaseObserver<CouponsStatistics>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(CouponsStatistics couponsStatistics) {
                getIView().coupons_statistics_Success(couponsStatistics);



            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
