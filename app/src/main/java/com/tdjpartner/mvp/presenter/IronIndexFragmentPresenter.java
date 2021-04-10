package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.IronHomeData;
import com.tdjpartner.model.IronHomeTopData;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.IronIndexFragment;

import java.util.Map;

/**
 * Created by LFM on 2021/3/3.
 */
public class IronIndexFragmentPresenter extends BasePresenter<Model, IronIndexFragment> {


    @Override
    public Model loadModel() {
        return null;
    }

    public void homeData(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.ironHomeData(map, new BaseObserver<IronHomeData>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(IronHomeData homeData) {
                getIView().homeData_Success(homeData);
            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().homeData_failed();
            }
        }));
    }
}
