package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.IronStatisticsDetails;

import java.util.Arrays;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailFragment extends NetworkFragment {

    private ListViewAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithFragment().loadingWithNewLiveData(IronStatisticsDetails.class, getArgs())
                .observe(this, ironStatisticsDetails -> {

                    adapter.clear();

                    for (IronStatisticsDetails.ListBean bean : ironStatisticsDetails.getList()) {
                        adapter.add(Arrays.asList("" + bean.getTodayAmount(),
                                "" + bean.getCategoryNum(),
                                "" + bean.getFirstOrderNum(),
                                bean.getName(),
                                "负责专员:" + bean.getPartnerName(),
                                bean.getRegionCollNo(),
                                bean.getName(),
                                bean.getAddress()));

                        System.out.println("bean = " + bean);
                        System.out.println(bean.getTodayAmount() + "," +
                                bean.getCategoryNum() + "," +
                                bean.getFirstOrderNum() + "," +
                                bean.getName() + "," +
                                "负责专员:" + bean.getPartnerName() + "," +
                                bean.getRegionCollNo() + "," +
                                bean.getName() + "," +
                                bean.getAddress() + ",");
                    }
                    dismissLoading();
                });
        showLoading();
    }

    @Override
    protected View createView() {
        System.out.println("~~" + getClass().getSimpleName() + ".initView~~");

        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        adapter = new ListViewAdapter.Builder<>()
                .setResource(R.layout.iron_day_list_datail_item)
                .setInitView((data, convertView) -> {
                    System.out.println("data = " + data + ", convertView = " + convertView);

//                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.get(0));
//                    ((TextView) convertView.findViewById(R.id.activeNum)).setText(data.get(1));
//                    ((TextView) convertView.findViewById(R.id.orderNum)).setText(data.get(2));
//
//                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.get(3));
//                    ((TextView) convertView.findViewById(R.id.tv_boss)).setText(data.get(4));
//                    ((TextView) convertView.findViewById(R.id.tv_regionCollNo)).setText(data.get(5));
//                    ((TextView) convertView.findViewById(R.id.tv_username)).setText(data.get(6));
//                    ((TextView) convertView.findViewById(R.id.tv_address)).setText(data.get(7));

                })
                .build(getContext());
        listView.setAdapter(adapter);
        listView.setDivider(null);

        return listView;
    }
}