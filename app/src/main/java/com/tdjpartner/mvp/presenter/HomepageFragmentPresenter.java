package com.tdjpartner.mvp.presenter;
import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.fragment.HomepageFragment;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.mvp.model.Model;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;


public class HomepageFragmentPresenter extends BasePresenter<Model, HomepageFragment> {


    @Override
    public Model loadModel() {
        return null;
    }
    public Disposable get_rentinglist(int pn){
        Map<String, Object> map = new HashMap<>();
        map.put("lim",10);
        map.put("offs",pn);
        map.put("userId", 26819);
        map.put("name","");
        map.put("sort","");
        map.put("status","1");
        map.put("siteId","3");
        return RequestPresenter.get_rentinglistS(new BaseObserver<RentingInfos>(getIView().getContext(),true) {
            @Override
            protected void onSuccess(BaseResponse<RentingInfos> t) {

            }

            @Override
            protected void onFailed(Throwable e) {
//                getIView().get_RentingList_Failed();
            }

        },map);




    }

}
