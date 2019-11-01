package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.TeamPreviewActivity;

import java.util.Map;

public class TeamPreviewPresenter extends BasePresenter<Model, TeamPreviewActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void teamOverView_day(Map<String,Object> map){
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

    public void teamOverView_month(Map<String,Object> map){
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

    public void teamOverView_all(Map<String,Object> map){
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

    public void myTeamPartnerList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.myTeamPartnerList(map, new BaseObserver<MyTeam>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(MyTeam myTeam) {
                getIView().myTeamPartnerList_Success(myTeam);
            }

            @Override
            protected void onFailed(Throwable e) {
            }
        }));

    }
}
