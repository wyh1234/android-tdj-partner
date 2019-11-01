package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.TeamMemberActivity;

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

}
