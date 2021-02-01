package com.tdjpartner.mvp.presenter;

import android.widget.ScrollView;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.PageByCSIdList;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.AdterSalesOrderListActivity;


public class AdterSalesOrderListPresenter extends BasePresenter<Model, AdterSalesOrderListActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void findPageByCSIdList(int pn, int id, String startTime, String endTime) {
        getIView().addSubscribe(RequestPresenter.findPageByCSIdList(pn,id,startTime,endTime ,new BaseObserver<PageByCSIdList>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(PageByCSIdList pageByCSIdList) {
                getIView().pageByCSIdList_Success(pageByCSIdList);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().pageByCSIdList_Failed();
            }
        }));

    }


}
