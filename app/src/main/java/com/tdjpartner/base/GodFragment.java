package com.tdjpartner.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.viewmodel.IronViewModel;
import com.tdjpartner.widget.ProgressDialog;

/**
 * Created by LFM on 2021/3/20.
 */
public abstract class GodFragment<VM extends IronViewModel> extends Fragment {

    private VM vm;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = setVM();
    }

    @Override
    public void onDestroyView() {
        LocationUtils.getInstance().stopLocalService();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
////        showLoading();
////        loadData();
//    }

    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.createDialog(getContext());
            mProgressDialog.setMessage("加载中...");
            mProgressDialog.show();
        }
    }

    public void dismissLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public VM getVm() {
        return vm;
    }
    protected abstract VM setVM();
    protected abstract int getLayoutId();
    protected abstract void loadData();
}
