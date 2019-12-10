package com.tdjpartner.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


/**
 * Created by Administrator on 2017/1/3 0003.
 * 自定义的内容更新方法
 * <p>
 * isVisible()方法可以判断Fragment的视图是否创建好
 * <p>
 * 1，加载到activity  走的是正常的生命周期  在onActivityCreated中加载数据
 * <p>
 * 2，ViewPager和Fragment结合 时由于预加载生命周期发生了变化
 * setUserVisibleHint(boolean isVisibleToUser) 会在 onCreate()方法调用之前就调用
 */
public abstract class CustomerFragment extends Fragment {

    private boolean isFirst = true;//是否是第一次加载
    private boolean isPauseRevert = false;//是否从暂停恢复


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            //是ViewPager中的Fragment
            if (getTag() != null && getTag().startsWith("android:switcher:" + getId())) {
                if (isVisible() && isFirst) {
                    isFirst = false;
                    onFragmentFirstVisible();
                }
            } else {
                if (isFirst) {
                    isFirst = false;
                    onFragmentFirstVisible();
                }
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            if (isVisible()) {
                //如果isFirst=true  代有之前没有加载，在显示给用户的时侯加载
                if (isFirst) {
                    isFirst = false;
                    onFragmentFirstVisible();
                } else onUserVisible();
            }
            //被ViewPager复用的fragment,isVisible()=false
            else if (isPauseRevert) {
                isPauseRevert = false;
                onUserVisible();
            }

        } else {
            if (isVisible()) onInvisible();
        }


    }

    @Override
    public void onStop() {
        isPauseRevert = true;
        super.onStop();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPauseRevert && isVisibleToUser(this)) {
            isPauseRevert = false;
            onPauseRevert();
        }
    }
    //判断fragment是否显示在了用户面前
    public boolean isVisibleToUser(Fragment fragment) {
        Fragment frag = fragment.getParentFragment();
        if (fragment.getUserVisibleHint() && isVisible() && frag != null) {
            return frag.getUserVisibleHint() && frag.isVisible() && isVisibleToUser(frag);
        } else {
            return fragment.getUserVisibleHint() && fragment.isVisible();
        }
    }
    //数据显示后，从弹出Activity页面返回后   会在initData()后面执行  AlertDialog  弹起也会调用
    public void onPauseRevert() {
        Log.d(this.getClass().getSimpleName() + ":" + this.getClass().getSimpleName(), "onPauseRevert() ");
    }

    //第一次显示的时侯加载数据,只会调用一次
    protected void onFragmentFirstVisible() {
//        viewModel.initData();
        Log.d(this.getClass().getSimpleName() + ":" +this.getClass().getSimpleName(), "initData() ");
    }

    //可见时调用
    public void onUserVisible() {
        Log.d(this.getClass().getSimpleName() + ":" + this.getClass().getSimpleName(), "onUserVisible() ");
    }

    //不可见时调用
    public void onInvisible() {
        Log.d(this.getClass().getSimpleName() + ":" + this.getClass().getSimpleName(), "onInvisible()");
    }


}
