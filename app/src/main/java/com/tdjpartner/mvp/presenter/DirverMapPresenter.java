package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DegreeOfSatisfaction;
import com.tdjpartner.model.DriverLocation;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.DirverMapActivity;

import java.util.Map;

import retrofit2.http.Query;

public class DirverMapPresenter extends BasePresenter<Model, DirverMapActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void getDriverLocation( String orderNo, String driverTel,  int customerAddrId){
        getIView().addSubscribe(RequestPresenter.getDriverLocation(orderNo,driverTel,customerAddrId, new BaseObserver<DriverLocation>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(DriverLocation driverLocation) {
                getIView().getDriverLocationSuccess(driverLocation);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
    public void getDegreeOfSatisfaction( int driverId,String extOrderId){
        getIView().addSubscribe(RequestPresenter.getDegreeOfSatisfaction(driverId,extOrderId, new BaseObserver<DegreeOfSatisfaction>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(DegreeOfSatisfaction degreeOfSatisfaction) {
                getIView().getDegreeOfSatisfactionSuccess(degreeOfSatisfaction);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

}
