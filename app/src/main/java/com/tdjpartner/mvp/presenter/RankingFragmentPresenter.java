package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.IronHomeData;
import com.tdjpartner.model.IronHomeTopData;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.IronIndexFragment;
import com.tdjpartner.ui.fragment.RankingFragment;

import java.util.Map;

/**
 * Created by LFM on 2021/3/3.
 */
public class RankingFragmentPresenter extends BasePresenter<Model, RankingFragment> {


    @Override
    public Model loadModel() {
        return null;
    }



    public void homeTopData(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.ironHomeTopData(map, new BaseObserver<IronHomeTopData>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(IronHomeTopData homeTopData) {
                getIView().homeTopData_Success(homeTopData);
            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().homeTopData_failed();
            }
        }));
    }

}
