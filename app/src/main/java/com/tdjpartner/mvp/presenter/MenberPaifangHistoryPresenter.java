package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.MenberPaifangHistoryActivity;

import java.util.Map;

public class MenberPaifangHistoryPresenter extends BasePresenter<Model, MenberPaifangHistoryActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void distinctList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.distinctList(map, new BaseObserver<DistinctList>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(DistinctList distinctList) {
                getIView().getDistinctList(distinctList);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
