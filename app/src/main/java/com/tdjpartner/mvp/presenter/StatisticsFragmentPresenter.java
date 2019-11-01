package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.HomeDataDetails;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.StatisticsFragment;

import java.util.Map;

public class StatisticsFragmentPresenter extends BasePresenter<Model, StatisticsFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void homeDataDetails(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.homeDataDetails(map, new BaseObserver<HomeDataDetails>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(HomeDataDetails homeDataDetails) {
                getIView().homeDataDetails_Success(homeDataDetails);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().homeDataDetails_Failed();
            }
        }));

    }
}
