package com.tdjpartner.mvp.presenter;

import android.graphics.Bitmap;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.RealNameAuthenticationActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;

import java.util.List;
import java.util.Map;

public class RealNameAuthenticationPresenter extends BasePresenter<Model, RealNameAuthenticationActivity> {
    @Override
    public Model loadModel() {
        return null;
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
    public void addUserCard(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.addUserCard(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer data) {
                GeneralUtils.showToastshort("认证成功");

                getIView().addUserCardSuccess( data);

            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
