package com.tdjpartner.mvp.presenter;
import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.HomepageFragment;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;


public class HomepageFragmentPresenter extends BasePresenter<Model, HomepageFragment> {


    @Override
    public Model loadModel() {
        return null;
    }

}
