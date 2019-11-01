package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.HomePageActivity;

import java.util.Map;

public class HomePagePresenter extends BasePresenter<Model, HomePageActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void homeData(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.homeData(map, new BaseObserver<HomeData>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(HomeData homeData) {
                getIView().homeData_Success(homeData);

            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().homeData_failed();

            }
        }));

    }
}
