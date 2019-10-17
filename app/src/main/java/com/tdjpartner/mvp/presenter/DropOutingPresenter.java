package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.DropOutingActivity;

import java.util.Map;

public class DropOutingPresenter extends BasePresenter<Model, DropOutingActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void downList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.downList(map, new BaseObserver<DropOuting>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(DropOuting dropOuting) {
                getIView().downListSuccess(dropOuting);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().getdownList_Failed();

            }
        }));

    }
}
