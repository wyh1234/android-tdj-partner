package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.DiscountCouponActivity;
import com.tdjpartner.ui.fragment.DiscountCouponFragment;

import java.util.Map;

public class DiscountCouponPresenter extends BasePresenter<Model, DiscountCouponFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void coupons_findByUser(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.coupons_findByUser(map, new BaseObserver<DiscountCoupon>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(DiscountCoupon discountCoupon) {
                getIView().coupons_findByUser_Success(discountCoupon);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().coupons_findByUser_Failed();
            }
        }));

    }
}
