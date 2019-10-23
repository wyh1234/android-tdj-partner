package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.SelectPersonActivity;

import java.util.List;
import java.util.Map;

public class SelectPersonActivityPresenter extends BasePresenter<Model, SelectPersonActivity> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void userRelations_managerList(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.userRelations_managerList(map, new BaseObserver<SelectPerson>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(SelectPerson selectPersonList) {
                getIView().userRelations_managerList_Success(selectPersonList);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().userRelations_managerList_Success_failed();
            }
        }));

    }

    public void userRelations_setManager(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.userRelations_setManager(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer integer) {
                getIView().userRelations_setManager_Success();


            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }
}
