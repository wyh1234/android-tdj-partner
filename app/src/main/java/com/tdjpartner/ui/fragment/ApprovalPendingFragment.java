package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.HotelAuditPageList;
import com.tdjpartner.ui.activity.ApprovalHandleActivity;
import com.tdjpartner.ui.activity.ApprovalDetailActivity;
import com.tdjpartner.utils.glide.ImageLoad;

/**
 * Created by LFM on 2021/3/15.
 */
public class ApprovalPendingFragment extends NetworkFragment implements BaseQuickAdapter.OnItemChildClickListener {

    private BaseQuickAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVMWithFragment().loadingWithNewLiveData(HotelAuditPageList.class, getArgs())
                .observe(this, hotelAuditPageList -> {
                    adapter.setNewData(hotelAuditPageList.obj);
                    dismissLoading();
                });
        showLoading();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        adapter = new BaseQuickAdapter<HotelAuditPageList.HotelAuditPage, BaseViewHolder>(R.layout.iron_approval_item) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, HotelAuditPageList.HotelAuditPage item) {
                System.out.println("~~" + getClass().getSimpleName() + ".convert~~");
                System.out.println("baseViewHolder = " + baseViewHolder + ", item = " + item);


                String authStatus;
                switch (item.authStatus) {
                    case 0:
                        authStatus = "待审核";
                        break;
                    case 1:
                        authStatus = "审核成功";
                        baseViewHolder.getView(R.id.authStatus).setBackgroundResource(R.drawable.bg_green_12);
                        break;
                    case 2:
                        authStatus = "审核驳回";
                        baseViewHolder.getView(R.id.authStatus).setBackgroundResource(R.drawable.bg_grey_12);
                        break;
                    default:
                        authStatus = "未知状态";
                }

                baseViewHolder.setText(R.id.enterprise_code, "" + item.enterprise_code)
                        .setText(R.id.commissioner_name, "DB:" + item.commissioner_name)
                        .setText(R.id.authStatus, authStatus)
                        .setText(R.id.person_name, "" + item.nick_name + "：" + "" + item.phone)
                        .setText(R.id.created_at, "" + item.created_at)
                        .setText(R.id.enterprise_msg, "" + item.enterprise_msg);

                baseViewHolder.addOnClickListener(R.id.fl_header);
                baseViewHolder.addOnClickListener(R.id.ll_body);

                ImageLoad.loadImageViewLoding(item.image_url, baseViewHolder.getView(R.id.image_url));
                ImageLoad.loadImageViewLoding(item.bzlicence_url, baseViewHolder.getView(R.id.bzlicence_url));

            }
        };

        adapter.setOnItemChildClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        System.out.println("~~" + getClass().getSimpleName() + ".onItemChildClick~~");
        System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);
        Intent intent;
        switch (view.getId()) {
            case R.id.fl_header:
                intent = new Intent(getContext(), ApprovalHandleActivity.class);
                intent.putExtra("customerId", ((HotelAuditPageList.HotelAuditPage) baseQuickAdapter.getItem(i)).entity_id);
                startActivity(intent);
                break;
            case R.id.ll_body:
                intent = new Intent(getContext(), ApprovalDetailActivity.class);
                intent.putExtra("customerId", ((HotelAuditPageList.HotelAuditPage) baseQuickAdapter.getItem(i)).entity_id);
                startActivity(intent);
                break;
        }
    }
}