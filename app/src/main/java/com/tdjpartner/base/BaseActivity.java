package com.tdjpartner.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.MainTabActivity;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.model.LoginLoseEfficacyEvent;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.view.IView;
import com.tdjpartner.ui.activity.LoginActivity;
import com.tdjpartner.ui.fragment.ClientFragment;
import com.tdjpartner.utils.ActivityManager;
import com.tdjpartner.utils.Density;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 *
 *
/*
* */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements
        IView {
    protected View view;
    protected P mPresenter;
    private CompositeDisposable compositeDisposable;
    private static long mPreTime;
//    public static List<Activity> mActivities = new LinkedList<Activity>();
    private static Activity mCurrentActivity;// 对所有activity进行管理
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        Density.setDefault(this);
        //初始化的时候将其添加到集合中
//        synchronized (mActivities) {
//            mActivities.add(this);
//        }
        ActivityManager.addActivity(this, getClass());
        //初始化p层
        ButterKnife.bind(this);

        mPresenter = loadPresenter();
        //绑定v层
        initCommonData();
        //初始化空件
        initView();
        //初始化监听
        //加载网络（或者本地）数据
        initData();

    }

    protected abstract P loadPresenter();


    private void initCommonData() {


        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    protected abstract void initData();


    protected abstract void initView();

    protected abstract int getLayoutId();

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }


    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null){
            mPresenter.detachView();
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        unregisterEventBus(this);
        //销毁的时候从集合中移除
//        synchronized (mActivities) {
//            mActivities.remove(this);
//        }
        ActivityManager.removeActivity(this);
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainTabActivity){
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
                GeneralUtils.showToastshort("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();// finish()
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (GeneralUtils.isHideInput(view, ev)) {
                GeneralUtils. hideSoftInput(view.getWindowToken(),this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("onStop");


    }


    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("onStart");
        registerEventBus(this);
    }

    //登陆失效事件
    @Subscribe
    public void onEvent(LoginLoseEfficacyEvent event) {
        UserUtils.getInstance().logout();//先退出
        if (!ActivityManager.isActivityExist(LoginActivity.class) ) {
            BaseActivity baseActivity = ActivityManager.getTopActivity();
            if (baseActivity == null) return;
            ActivityManager.removeActivity(this);//删除所有activity
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);//这里的Activity是弹出登录的
        }else {
            ActivityManager.setTopActivity(LoginActivity.class);//设置置顶
        }
    }



}
