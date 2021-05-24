package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

/**
 * Created by LFM on 2021/3/15.
 */
public class StatisticsListFragment extends NetworkFragment {

    private ListViewAdapter<IronStatisticsDetails.ListBean> adapter;
    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型
    boolean isDay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDay = getArgs().containsKey("dayDate");
        getVMWithFragment().loadingWithNewLiveData(IronStatisticsDetails.class, getArgs())
                .observe(this, ironStatisticsDetails -> {
                    adapter.clear();
                    adapter.addAll(ironStatisticsDetails.getList());
                    dismissLoading();
                });
        showLoading();
    }

    @Override
    protected View createView() {
        System.out.println("~~" + getClass().getSimpleName() + ".initView~~");

        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        adapter = new ListViewAdapter.Builder<IronStatisticsDetails.ListBean>()
                .setResource(userType == 1 ? R.layout.net_statistics_list_item : R.layout.iron_statistics_list_item)
                .setOnClickListener(this::onClick)
                .addChildId(R.id.ll_call, R.id.tv_driverName)
                .setInitView((data, convertView) -> {
                    System.out.println("data = " + data + ", convertView = " + convertView);

                    if (userType == 1) {//网军
//                        if (isDay) {
//                            ((TextView) convertView.findViewById(R.id.todayAmountNum)).setText("" + data.getTodayAmount());
//                            ((TextView) convertView.findViewById(R.id.afterSaleAmount)).setText("" + data.getAfterSaleAmount());
//                            ((TextView) convertView.findViewById(R.id.callNum)).setText("" + data.getCallNum());
//                        } else {
//                            ((TextView) convertView.findViewById(R.id.todayAmount)).setText("月GMV");
//                            ((TextView) convertView.findViewById(R.id.today)).setText("月下单次数");
//                            ((TextView) convertView.findViewById(R.id.afterSale)).setText("月退款金额");
//                            ((TextView) convertView.findViewById(R.id.call)).setText("月拜访数");
//
//                            ((TextView) convertView.findViewById(R.id.todayAmountNum)).setText("" + data.getMonthAmount());
//                            ((TextView) convertView.findViewById(R.id.afterSaleAmount)).setText("" + data.getMonthAfterSaleAmount());
//                            ((TextView) convertView.findViewById(R.id.callNum)).setText("" + data.getMonthCallNum());
//                        }

                        if (!isDay) {
                            ((TextView) convertView.findViewById(R.id.todayAmount)).setText("月GMV");
                            ((TextView) convertView.findViewById(R.id.today)).setText("月下单次数");
                            ((TextView) convertView.findViewById(R.id.afterSale)).setText("月退款金额");
                            ((TextView) convertView.findViewById(R.id.call)).setText("月拜访数");
                        }
                        ((TextView) convertView.findViewById(R.id.todayAmountNum)).setText("" + data.getTodayAmount());
                        ((TextView) convertView.findViewById(R.id.afterSaleAmount)).setText("" + data.getAfterSaleAmount());
                        ((TextView) convertView.findViewById(R.id.callNum)).setText("" + data.getCallNum());
                        ((TextView) convertView.findViewById(R.id.todayTimes)).setText("" + data.getMonthTimes());//日下单次数同月下单次数
                    } else {//铁军
                        if (isDay) {
                            ((TextView) convertView.findViewById(R.id.todayAmount)).setText("" + data.getTodayAmount());
                            ((TextView) convertView.findViewById(R.id.categoryNum)).setText("" + data.getCategoryNum());
                        } else {
                            ((TextView) convertView.findViewById(R.id.todayAmount)).setText("月下单额");
                            ((TextView) convertView.findViewById(R.id.today)).setText("月下单次数");

                            ((TextView) convertView.findViewById(R.id.todayAmount)).setText("" + data.getMonthAmount());
                            ((TextView) convertView.findViewById(R.id.categoryNum)).setText("" + data.getMonthAmount());
                        }
                        ((TextView) convertView.findViewById(R.id.todayTimes)).setText("" + data.getMonthTimes());//日下单次数同月下单次数
                    }

                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.getName());
                    ((TextView) convertView.findViewById(R.id.tv_username)).setText("负责专员:" + data.getPartnerName());
                    ((TextView) convertView.findViewById(R.id.tv_regionCollNo)).setText(data.getRegionCollNo());
                    ((TextView) convertView.findViewById(R.id.tv_address)).setText(data.getAddress());

                    TextView textView;
                    textView = convertView.findViewById(R.id.tv_boss);
                    textView.setText(data.getBoss());
                    textView.setTag(data.getMobile());


                    textView = convertView.findViewById(R.id.tv_driverName);
                    if (TextUtils.isEmpty(data.getDriverName())) {
                        textView.setVisibility(View.GONE);
                    } else {
                        textView.setText(data.getDriverName());
                        textView.setTag(data.getDriverTel());
                    }


                })
                .build(getContext());
        listView.setOnItemClickListener(this::onItemClick);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        return listView;
    }

    public void onClick(View view) {
        System.out.println("view = " + view);

        switch (view.getId()) {
            case R.id.ll_call:
                GeneralUtils.action_call(new RxPermissions(getActivity()), "" + view.findViewById(R.id.tv_boss).getTag(), getContext());
                break;
            case R.id.tv_driverName:
                GeneralUtils.action_call(new RxPermissions(getActivity()), "" + view.getTag(), getContext());
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("IronDayListDetailFragment.onItemClick");
        System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
        Intent intent = new Intent(getContext(), ClientDetailsActivity.class);
        intent.putExtra("customerId", adapter.getItem(position).getCustomerId() + "");
        startActivity(intent);
    }
}