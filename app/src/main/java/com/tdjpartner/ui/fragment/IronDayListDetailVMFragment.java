package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;
import com.tdjpartner.base.VMFragment;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.viewmodel.IronStatisticsDetailsViewModel;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailVMFragment extends VMFragment<IronStatisticsDetails, IronStatisticsDetailsViewModel> implements View.OnClickListener {


    private IronAdapter ironAdapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected View initView(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".initView~~");
        System.out.println("view = " + view);

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
    protected void loadedData(IronStatisticsDetails ironStatisticsDetails) {
        System.out.println("~~" + getClass().getSimpleName() + ".loadedData~~");
        System.out.println("ironStatisticsDetails = " + ironStatisticsDetails);

        Random random = new Random();
        ironAdapter.clear();
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
    protected IronStatisticsDetailsViewModel setVM() {
        IronStatisticsDetailsViewModel ironStatisticsDetailsViewModel = ViewModelProviders.of(getActivity()).get(IronStatisticsDetailsViewModel.class);
        System.out.println("ironStatisticsDetailsViewModel = " + ironStatisticsDetailsViewModel);
        return ironStatisticsDetailsViewModel;
    }

    @Override
    public void onClick(View v) {}
}