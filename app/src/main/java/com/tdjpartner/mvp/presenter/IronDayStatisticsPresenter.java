package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.HomeDataDetails;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.IronStatisticsActivity;

import java.util.Map;

/**
 * Created by LFM on 2021/3/3.
 */
public class IronDayStatisticsPresenter extends BasePresenter<Model, IronStatisticsActivity> {


    @Override
    public Model loadModel() {
        return null;
    }

    public void getData(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.ironStatisticsDetails(map, new BaseObserver<IronStatisticsDetails>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(IronStatisticsDetails homeDataDetails) {
                getIView().success(homeDataDetails);
            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().failure();
            }
        }));
    }
}
