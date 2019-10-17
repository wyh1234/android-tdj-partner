package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.CommonFollowUpActivity;
import com.tdjpartner.utils.GeneralUtils;

import java.util.Map;

public class CommonFollowUpPresenter extends BasePresenter<Model, CommonFollowUpActivity> {
    @Override
    public Model loadModel() {
        return null;
    }


    public void followList(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.followList(map, new BaseObserver<DropOuting>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(DropOuting dropOuting) {
                getIView().followListSuccess(dropOuting);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().getfollowList_Failed();

            }
        }));

    }

    public void internationalWaters(Map<String, Object> map) {
        getIView().addSubscribe(RequestPresenter.internationalWaters(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer dropOuting) {
                GeneralUtils.showToastshort("跟进成功请在24小时内进行拜访");
                getIView().internationalWatersSuccess();


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
