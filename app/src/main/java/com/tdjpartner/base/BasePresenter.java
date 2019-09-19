package com.tdjpartner.base;




import com.tdjpartner.mvp.model.IModel;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.view.IView;

import java.lang.ref.WeakReference;


/**
 * Created by GaoSheng on 2016/11/26.
 * 17:21
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.base
 */

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    private WeakReference actReference;
    protected V iView;
    protected M iModel;

    public M getiModel() {
        iModel = loadModel(); //使用前先进行初始化
        return iModel;
    }

    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    @Override
    public V getIView() {
        if (actReference!=null){
            return (V) actReference.get();
        }
       return (V) new WeakReference(iView).get();
    }

    public abstract M loadModel();

}
