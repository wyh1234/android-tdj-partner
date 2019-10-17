package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.EarningsHistoryActivity;
import com.tdjpartner.ui.fragment.EarningsHistoryFragment;

import java.util.Map;

public class EarningsHistoryPresenter extends BasePresenter<Model, EarningsHistoryFragment> {
    @Override
    public Model loadModel() {
        return null;
    }

    public void earning_info(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.earning_info(map, new BaseObserver<EarningsHistory>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(EarningsHistory earningsHistory) {
                getIView().earning_info_Success(earningsHistory);


            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().earning_info_failed();
            }
        }));

    }

}
