package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.HotelAuditPageList;
import com.tdjpartner.ui.activity.ApprovalDetailActivity;
import com.tdjpartner.ui.activity.ApprovalHandleActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;

/**
 * Created by LFM on 2021/3/15.
 */
public class ApprovalListFragment extends NetworkFragment implements Observer<List> {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    final int requestCode = new Random().nextInt(1000);
    String keyword = "";

    private ListViewAdapter<HotelAuditPageList.HotelAuditPage> adapter;
    private MediatorLiveData<List> liveData;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setLiveData(MediatorLiveData<List> liveData) {
        this.liveData = liveData;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithFragment().loadingWithNewLiveData(HotelAuditPageList.class, getArgs())
                .observe(this, hotelAuditPageList -> {
                    adapter.clear();
                    adapter.addAll(hotelAuditPageList.obj);
                    dismissLoading();
                    stop();
                });
        showLoading();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        adapter = new ListViewAdapter.Builder<HotelAuditPageList.HotelAuditPage>()
                .setResource(R.layout.iron_approval_item)
                .setInitView((item, convertView) -> {
                    String authStatus;
                    switch (item.authStatus) {
                        case 0:
                            authStatus = "待审核";
                            break;
                        case 1:
                            authStatus = "审核成功";
                            convertView.findViewById(R.id.authStatus).setBackgroundResource(R.drawable.bg_green_12);
                            break;
                        case 2:
                            authStatus = "审核驳回";
                            convertView.findViewById(R.id.authStatus).setBackgroundResource(R.drawable.bg_grey_12);
                            break;
                        default:
                            authStatus = "未知状态";
                    }

                    ((TextView) convertView.findViewById(R.id.bd)).setText("" + item.enterprise_code);
                    ((TextView) convertView.findViewById(R.id.commissioner_name)).setText("创客：" + (TextUtils.isEmpty(item.commissioner_name) ? "无" : item.commissioner_name));
                    ((TextView) convertView.findViewById(R.id.authStatus)).setText(authStatus);
                    ((TextView) convertView.findViewById(R.id.verify_customer)).setText("" + item.nick_name + " " + item.phone);
                    ((TextView) convertView.findViewById(R.id.created_at)).setText("" + item.created_at);
                    ((TextView) convertView.findViewById(R.id.enterprise_msg)).setText("" + item.enterprise_msg);

                    if (!TextUtils.isEmpty(item.image_url))
                        ImageLoad.loadImageViewLoding(item.image_url, convertView.findViewById(R.id.image_url), R.mipmap.yingyezhao_bg);
                    if (!TextUtils.isEmpty(item.bzlicence_url))
                        ImageLoad.loadImageViewLoding(item.bzlicence_url, convertView.findViewById(R.id.bzlicence_url), R.mipmap.yingyezhao_bg);

                })
                .build(getContext());


        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("~~" + getClass().getSimpleName() + ".onItemClick~~");
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);

                Intent intent = new Intent(getContext(), ((HotelAuditPageList.HotelAuditPage) parent.getAdapter().getItem(position)).authStatus == 0 ? ApprovalHandleActivity.class : ApprovalDetailActivity.class);
                intent.putExtra("customerId", ((HotelAuditPageList.HotelAuditPage) parent.getAdapter().getItem(position)).entity_id);
                startActivityForResult(intent, requestCode);
            }
        });
        liveData.observe(getActivity(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode != requestCode) return;

        if (resultCode == OK) {
            onRefresh();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_list_fragment;
    }

    @Override
    public void onDestroy() {
        System.out.println("~~ApprovalListFragment.onDestroy~~");
        liveData.removeObserver(this);
        super.onDestroy();
    }

    public void onRefresh() {
        getArgs().put("keyword", keyword);
        System.out.println("map = " + getArgs());
        getVMWithFragment().loading(HotelAuditPageList.class, getArgs());
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List list) {
        keyword = list.get(1).toString();
        onRefresh();
    }
}