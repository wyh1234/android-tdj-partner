package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.utils.GeneralUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailFragment extends NetworkFragment {

    private ListViewAdapter<IronStatisticsDetails.ListBean> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                .setResource(R.layout.iron_day_list_datail_item)
                .setOnClickListener(this::onClick)
                .addChildId(R.id.ll_call)
                .setInitView((data, convertView) -> {
                    System.out.println("data = " + data + ", convertView = " + convertView);

                    ((TextView) convertView.findViewById(R.id.todayAmount)).setText("" + data.getTodayAmount());
                    ((TextView) convertView.findViewById(R.id.todayTimes)).setText("" + data.getMonthTimes());//日下单次数同月下单次数
                    ((TextView) convertView.findViewById(R.id.categoryNum)).setText("" + data.getCategoryNum());

                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.getName());
                    ((TextView) convertView.findViewById(R.id.tv_username)).setText("负责专员:" + data.getPartnerName());
                    ((TextView) convertView.findViewById(R.id.tv_regionCollNo)).setText(data.getRegionCollNo());
                    ((TextView) convertView.findViewById(R.id.tv_address)).setText(data.getAddress());

                    TextView textView = convertView.findViewById(R.id.tv_boss);
                    textView.setText(data.getBoss());
                    textView.setTag(data.getMobile());
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