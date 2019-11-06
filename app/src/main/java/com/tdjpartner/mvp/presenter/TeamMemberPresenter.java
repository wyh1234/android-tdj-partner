package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.TeamMemberActivity;

import java.util.List;
import java.util.Map;

public class TeamMemberPresenter  extends BasePresenter<Model, TeamMemberActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void myTeamPartnerList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.myTeamPartnerList(map, new BaseObserver<MyTeam>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(MyTeam myTeam) {
                getIView().myTeamPartnerList_Success(myTeam);
            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().myTeamPartnerList_failed();
            }
        }));

    }
    public void myTeamPartnerSelectList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.myTeamPartnerSelectList(map, new BaseObserver<List<String>>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(List<String> stringList) {
                getIView().myTeamPartnerSelectList_Success(stringList);

            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().myTeamPartnerList_failed();
            }
        }));

    }

}
