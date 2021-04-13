package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.base.VMFragment;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.viewmodel.StatisticsDetailsViewModel;

import java.util.Arrays;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailVMFragment extends VMFragment<IronStatisticsDetails, StatisticsDetailsViewModel> implements View.OnClickListener {

    private IronAdapter ironAdapter;

    @Override
    protected StatisticsDetailsViewModel setVM() {
        return ViewModelProviders.of(this).get(StatisticsDetailsViewModel.class);
    }

    @Override
    protected View createView() {
        System.out.println("~~" + getClass().getSimpleName() + ".initView~~");

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

        return listView;
    }

    @Override
    protected void updateView(IronStatisticsDetails ironStatisticsDetails) {
        System.out.println("~~" + getClass().getSimpleName() + ".updateView~~");
        System.out.println("ironStatisticsDetails = " + ironStatisticsDetails);
        System.out.println(ironStatisticsDetails.getList().size() + "|ironStatisticsDetails = " + ironStatisticsDetails.getList());

        ironAdapter.clear();


//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//            ironAdapter.add(Arrays.asList("" + random.nextInt(1000),
//                    "" + random.nextInt(1000),
//                    "" + random.nextInt(1000),
//                    "华天大酒店" + random.nextInt(1000),
//                    "负责专员:李四" + random.nextInt(1000),
//                    "D92区72-58" + random.nextInt(1000),
//                    "李四" + random.nextInt(1000),
//                    "武昌徐东大街100号" + random.nextInt(1000)));
//        }


        for (IronStatisticsDetails.ListBean bean : ironStatisticsDetails.getList()) {
            ironAdapter.add(Arrays.asList("" + bean.getTodayAmount(),
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
    }

    @Override
    public void onClick(View v) {}
}