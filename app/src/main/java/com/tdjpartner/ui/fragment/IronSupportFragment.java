package com.tdjpartner.ui.fragment;

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
import com.tdjpartner.model.AfterSaleInfo;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.viewmodel.AfterSalesViewModel;

import java.util.Arrays;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronSupportFragment extends GodFragment<AfterSaleInfoData, AfterSalesViewModel> implements View.OnClickListener {

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
    protected void loadedData(AfterSaleInfoData afterSaleInfoData) {
        System.out.println("~~" + getClass().getSimpleName() + ".loadedData~~");
        System.out.println("afterSaleInfoData = " + afterSaleInfoData);

//        ironAdapter.clear();
//        for (AfterSaleInfo afterSaleInfo : afterSaleInfoData.buGeting_list) {
////            ironAdapter.add(Arrays.asList(afterSaleInfo.hotel_name,
////                            afterSaleInfo.operator_user_name));
//        }
//        ironAdapter.notifyDataSetChanged();

        ironAdapter.clear();
        ironAdapter.add(Arrays.asList("111", "222"));
    }

    @Override
    protected AfterSalesViewModel setVM() {
        return ViewModelProviders.of(getActivity()).get(AfterSalesViewModel.class);
    }

    @Override
    public void onClick(View v) {

    }
}