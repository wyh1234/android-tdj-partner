package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.AdministrationPaifangHistoryActivity;

import java.util.Map;

public class AdministrationPaifangPresneter extends BasePresenter<Model, AdministrationPaifangHistoryActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void distinctList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.parentList(map, new BaseObserver<ParentList>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(ParentList parentList) {
                getIView().getDistinctList(parentList);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
