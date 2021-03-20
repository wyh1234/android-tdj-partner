package com.tdjpartner.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.github.nukc.stateview.StateView;
import com.tdjpartner.R;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.view.IView;
import com.tdjpartner.utils.LocationUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by wanyh on 2017/9/11.
 */

public abstract class BaseFrgment<P extends IPresenter> extends LazyLoadFragment implements IView {//应该继承CustomerFragment
    protected View view;
    protected P mPresenter;
    public CompositeDisposable compositeDisposable;
    protected Activity mActivity;
    protected StateView mStateView;//用于显示加载中、网络异常，空布局、内容布局
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mPresenter == null) {
            //创建P层
            this.mPresenter = loadPresenter();
        }

        if (mPresenter != null)
            mPresenter.attachView(this);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(getContentId(),container,false);
            ButterKnife.bind(this, view);
            mStateView = StateView.inject(getStateViewRoot());
            if (mStateView != null){
//                mStateView.setLoadingResource(R.layout.page_loading);
                mStateView.setRetryResource(R.layout.page_net_error);
                mStateView.setEmptyResource(R.layout.page_empty);
            }

            initView(view);
            initViews(view,savedInstanceState);
            loadData();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    protected  void initViews(View view, Bundle savedInstanceState){

    }

    @Override
    protected void onFragmentFirstVisible() {
        //当第一次可见的时候，加载数据

    }

    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null) {
            mPresenter.detachView();
            mPresenter=null;
        }

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        LocationUtils.getInstance().stopLocalService();
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected abstract void initView(View view);

    protected abstract void loadData();

    protected abstract P loadPresenter();

    protected abstract int getContentId();
    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }
    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }
    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }


}

