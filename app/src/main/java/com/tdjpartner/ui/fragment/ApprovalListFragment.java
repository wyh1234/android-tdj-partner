package com.tdjpartner.ui.fragment;

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
import java.util.Map;

import butterknife.BindView;

/**
 * Created by LFM on 2021/3/15.
 */
public class ApprovalListFragment extends NetworkFragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    private ListViewAdapter<HotelAuditPageList.HotelAuditPage> adapter;

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
                    ((TextView) convertView.findViewById(R.id.commissioner_name)).setText("DB：" + (TextUtils.isEmpty(item.commissioner_name) ? "无" : item.commissioner_name));
                    ((TextView) convertView.findViewById(R.id.authStatus)).setText(authStatus);
                    ((TextView) convertView.findViewById(R.id.verify_customer)).setText("" + item.nick_name + " " + item.phone);
                    ((TextView) convertView.findViewById(R.id.created_at)).setText("" + item.created_at);
                    ((TextView) convertView.findViewById(R.id.enterprise_msg)).setText("" + item.enterprise_msg);

                    if(!TextUtils.isEmpty(item.image_url))ImageLoad.loadImageViewLoding(item.image_url, convertView.findViewById(R.id.image_url), R.mipmap.yingyezhao_bg);
                    if(!TextUtils.isEmpty(item.bzlicence_url))ImageLoad.loadImageViewLoding(item.bzlicence_url, convertView.findViewById(R.id.bzlicence_url), R.mipmap.yingyezhao_bg);

                })
                .build(getContext());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("~~" + getClass().getSimpleName() + ".onItemClick~~");
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);

                Intent intent = new Intent(getContext(), ((HotelAuditPageList.HotelAuditPage) parent.getAdapter().getItem(position)).authStatus == 0 ? ApprovalHandleActivity.class : ApprovalDetailActivity.class);
                intent.putExtra("customerId", ((HotelAuditPageList.HotelAuditPage) parent.getAdapter().getItem(position)).entity_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_list_fragment;
    }

    public void onRefresh() {
        Map<String, Object> map = new HashMap<>();

        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("dayDate", GeneralUtils.getTimeFilter(new Date()));
        map.put("monthTime", GeneralUtils.getMonthFilter(new Date()));
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());

        getVMWithFragment().loading(HotelAuditPageList.class, getArgs());
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}