package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.base.GodFragment;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.mvp.presenter.IronAfterSalesPresenter;
import com.tdjpartner.viewmodel.AfterSalesViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronSupportFragment extends GodFragment<AfterSalesViewModel> implements View.OnClickListener {

    private AfterSaleInfoData data;
    private IronAdapter ironAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.iron_support_list_fragment;
    }

    @Override
    protected void loadData() {
        System.out.println("~~" + getClass().getSimpleName() + ".loadData~~");

        getVm().getData().observe(getActivity(), afterSaleInfoData -> {
            System.out.println("afterSaleInfoData is " + afterSaleInfoData);
            dismissLoading();
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("~~" + getClass().getSimpleName() + ".onViewCreated~~");
        System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);


        ListView listView = view.findViewById(R.id.listView);


        ironAdapter = new IronAdapter.Builder()
                .setResource(R.layout.iron_support_list_item)
                .setIView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.hotel_name)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.operator_user_name)).setText(data.get(1));
                })
                .build(getContext());

        listView.setAdapter(ironAdapter);


        showLoading();
        loadData();

    }

    @Override
    protected AfterSalesViewModel setVM() {
        return ViewModelProviders.of(getActivity()).get(AfterSalesViewModel.class);
    }

    @Override
    public void onClick(View v) {

    }
}