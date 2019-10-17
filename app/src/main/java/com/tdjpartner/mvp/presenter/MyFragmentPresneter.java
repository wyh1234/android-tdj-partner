package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.fragment.MyFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;

import java.util.List;
import java.util.Map;

public class MyFragmentPresneter extends BasePresenter<Model, MyFragment> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void customer_refreshInfo(int entityId,int loginUserId){
        getIView().addSubscribe(RequestPresenter.customer_refreshInfo(entityId,loginUserId, new BaseObserver<UserInfo>(getIView().getContext(), false) {
            @Override
            protected void onSuccess(UserInfo  userInfo) {
                getIView().getrefreshInfo(userInfo);

            }

            @Override
            protected void onFailed(Throwable e) {
                getIView().getrefreshInfo_failed();
            }
        }));

    }
    public void imageUpload(String path){
        getIView().addSubscribe(RequestPresenter.imageUpload(GeneralUtils.getFileNames(path),
                BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmap(path), true),
                new BaseObserver<String>(getIView().getContext(), true) {
                    @Override
                    protected void onSuccess(String data) {
                        getIView().getImage_Success(data);

                    }

                    @Override
                    protected void onFailed(Throwable e) {

                    }
                }));

    }
}
