package com.tdjpartner.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.tdjpartner.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment {
    protected BaseActivity mActivity;
    protected Unbinder mBinder;
    private View mRootView;
    protected Window mWindow;

    @LayoutRes
    protected abstract int attachLayoutRes();

    protected abstract void initData();

    protected abstract void initView();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mRootView = inflater.inflate(attachLayoutRes(), null);
        mBinder = ButterKnife.bind(this, mRootView);
        int style = initStyleRes();
        Dialog dialog;
        if (style != STYLE_NORMAL) {
            dialog = new Dialog(mActivity, initStyleRes());
        } else {
            dialog = new Dialog(mActivity);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(mRootView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        mWindow = dialog.getWindow();
        initWindow();
        initView();
        initData();
        return dialog;
    }

    private int widthDp = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int heightDp = ViewGroup.LayoutParams.WRAP_CONTENT;

    protected void setWidth(int px){
        widthDp = px;
    }

    protected void setHeight(int px){
        heightDp = px;
    }

    @Override
    public void onStart() {
        super.onStart();
        mWindow.setLayout(widthDp, heightDp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    @StyleRes
    protected int initStyleRes() {
        return STYLE_NORMAL;
    }

    protected void initWindow() {
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mWindow.setGravity(Gravity.CENTER);
    }

    protected void finishSelf() {
        mActivity.getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }


}
