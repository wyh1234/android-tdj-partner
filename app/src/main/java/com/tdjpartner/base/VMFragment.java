package com.tdjpartner.base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.widget.ProgressDialog;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LFM on 2021/3/20.
 */
public abstract class VMFragment<M, VM extends BaseViewModel<M>> extends Fragment {

    private VM vm;
    private Unbinder unbinder;

    private Map<String, Object> args;
    private ProgressDialog mProgressDialog;
    private boolean isShowProgressDialog = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = setVM();
        showLoading();
        vm.getData().observe(getActivity(),
                new Observer<M>() {
                    @Override
                    public void onChanged(@Nullable M m) {
                        updateView(m);
                        onLoadEnd();
                        dismissLoading();
                    }
                });
        vm.loading(getArgs());
    }

    @Override
    public void onDestroyView() {
        LocationUtils.getInstance().stopLocalService();
        super.onDestroyView();
    }

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
        onLoadBegin();
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

    public Map<String, Object> getArgs() {
        if (args == null) {
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

    protected void onLoadBegin() {
    }

    protected void onLoadEnd() {
    }

    protected abstract VM setVM();

    protected abstract void updateView(M m);
}