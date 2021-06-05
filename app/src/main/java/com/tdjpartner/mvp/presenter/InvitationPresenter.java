package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.IntegralItem;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.InvitationActivityNew;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationPresenter extends BasePresenter<Model, InvitationActivityNew> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void version_check() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "android");
        getIView().addSubscribe(RequestPresenter.version_check(map, new BaseObserver<AppVersion>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(AppVersion appVersion) {
                getIView().version_check_success(appVersion);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }

    public void initRecordList() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("type", 1);
        getIView().addSubscribe(RequestPresenter.queryInviteList(map, new BaseObserver<List<IntegralItem>>(getIView().getContext()) {
            @Override
            protected void onSuccess(List<IntegralItem> integralItem) {
                getIView().queryInviteListSuccess(integralItem);
            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));
    }
}
