package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
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

//    public void homeData(Map<String,Object> map){
//        getIView().addSubscribe(RequestPresenter.homeData(map, new BaseObserver<HomeData>(getIView().getContext(), false) {
//            @Override
//            protected void onSuccess(HomeData homeData) {
//                getIView().homeData_Success(homeData);
//
//            }
//
//            @Override
//            protected void onFailed(Throwable e) {
//                getIView().homeData_failed();
//
//            }
//        }));
//
//    }

    public void newhomeData(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.newhomeData(map, new BaseObserver<NewHomeData>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(NewHomeData homeData) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSuccess~~");
                System.out.println("homeData = " + homeData);
                getIView().homeData_Success(homeData);

            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().homeData_failed();

            }
        }));

    }

    public void teamOverView_day(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.teamOverView_day(map, new BaseObserver<TeamOverView>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(TeamOverView teamOverView) {
                getIView().teamOverView_day_Success(teamOverView);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

    public void teamOverView_month(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.teamOverView_month(map, new BaseObserver<TeamOverView>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(TeamOverView teamOverView) {
                getIView().teamOverView_month_Success(teamOverView);

            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }

    public void teamOverView_all(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.teamOverView_all(map, new BaseObserver<TeamOverView>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(TeamOverView teamOverView) {

                getIView().teamOverView_all_Success(teamOverView);
            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }

}
