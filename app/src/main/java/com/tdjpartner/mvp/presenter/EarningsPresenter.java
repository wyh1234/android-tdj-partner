package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.model.MyCountMoney;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.EarningsActivity;

import java.util.Map;

public class EarningsPresenter extends BasePresenter<Model, EarningsActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void myCountMoney(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.myCountMoney(map, new BaseObserver<MyCountMoney>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(MyCountMoney myCountMoney) {
                getIView().myCountMoneySuccess(myCountMoney);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
