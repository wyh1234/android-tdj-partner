package com.tdjpartner.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdjpartner.viewmodel.NetworkViewModel;
import com.tdjpartner.widget.ProgressDialog;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LFM on 2021/3/20.
 */
public abstract class NetworkFragment extends Fragment{

    private Unbinder unbinder;

    private Map<String, Object> args;
    private ProgressDialog mProgressDialog;
    private boolean isShowProgressDialog = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            View view = createView();
            if (view != null) unbinder = ButterKnife.bind(this, view);
            return view;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        if (view != null) unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void showLoading() {
        if (!isShowProgressDialog) return;
        if (mProgressDialog == null) mProgressDialog = ProgressDialog.createDialog(getContext());
        mProgressDialog.setMessage("加载中...");
        mProgressDialog.show();
    }

    public void dismissLoading() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
    }

    public void setShowProgressDialog(boolean showProgressDialog) {
        isShowProgressDialog = showProgressDialog;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public NetworkViewModel getVMWithFragment(){
        return ViewModelProviders.of(this).get(NetworkViewModel.class);
    }

    public NetworkViewModel getVMWithActivity(){
        return ViewModelProviders.of(getActivity()).get(NetworkViewModel.class);
    }

    public Map<String, Object> getArgs() {
        if (args == null && getArguments() != null) {
            this.args = (Map<String, Object>) getArguments().getSerializable("args");
        }
        return args;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) unbinder.unbind();
        super.onDestroy();
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View createView() {
        return null;
    }
}