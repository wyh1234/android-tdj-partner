package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.AddBaifangActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;

import java.util.Map;

public class AddBaifangPresenter extends BasePresenter<Model, AddBaifangActivity> {
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
    public void worship(Map<String,Object> map){

        getIView().addSubscribe(RequestPresenter.worship(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer integer) {
                getIView().worship_Success();




            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));


    }
}
