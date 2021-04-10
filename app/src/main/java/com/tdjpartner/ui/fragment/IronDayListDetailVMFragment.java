package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.base.GodFragment;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.viewmodel.AfterSalesViewModel;
import com.tdjpartner.viewmodel.IronStatisticsDetailsViewModel;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailVMFragment extends GodFragment<IronStatisticsDetails, IronStatisticsDetailsViewModel> implements View.OnClickListener {


    private IronAdapter ironAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.iron_support_list_fragment;
    }

    @Override
    protected void initView(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".initView~~");
        System.out.println("view = " + view);
        ListView listView = view.findViewById(R.id.listView);
        ironAdapter = new IronAdapter.Builder()
                .setResource(R.layout.iron_support_list_item)
                .setInitView((data, convertView) -> {
                    ((TextView) convertView.findViewById(R.id.hotel_name)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.operator_user_name)).setText(data.get(1));
                })
//                .addChildId(R.id.hotel_name)
//                .setOnClickListener(this)
                .build(getContext());
        listView.setAdapter(ironAdapter);
    }

    @Override
    protected void loadedData(IronStatisticsDetails ironStatisticsDetails) {
        System.out.println("~~" + getClass().getSimpleName() + ".loadedData~~");
        System.out.println("ironStatisticsDetails = " + ironStatisticsDetails);

        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        ironAdapter = new IronAdapter.Builder()
                .setResource(R.layout.iron_day_list_datail_item)
                .setInitView((data, convertView) -> {
                    System.out.println("data = " + data + ", convertView = " + convertView);

                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.get(1));
                    ((TextView) convertView.findViewById(R.id.orderNum)).setText(data.get(2));

                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.get(3));
                    ((TextView) convertView.findViewById(R.id.tv_boss)).setText(data.get(4));
                    ((TextView) convertView.findViewById(R.id.tv_regionCollNo)).setText(data.get(5));
                    ((TextView) convertView.findViewById(R.id.tv_username)).setText(data.get(6));
                    ((TextView) convertView.findViewById(R.id.tv_address)).setText(data.get(7));


                })
                .setOnClickListener(this)
                .build(getContext());
        listView.setAdapter(ironAdapter);
        listView.setDivider(null);
    }


    @Override
    protected IronStatisticsDetailsViewModel setVM() {
        return ViewModelProviders.of(getActivity()).get(IronStatisticsDetailsViewModel.class);
    }

    @Override
    public void onClick(View v) {

    }
}