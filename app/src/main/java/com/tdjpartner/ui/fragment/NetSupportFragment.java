package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.ui.activity.NetSupportDetailActivity;
import com.tdjpartner.utils.glide.ImageLoad;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/15.
 */
public class NetSupportFragment extends NetworkFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.ll_replenish)
    LinearLayout ll_replenish;
    @BindView(R.id.ll_replace)
    LinearLayout ll_replace;
    @BindView(R.id.ll_refund)
    LinearLayout ll_refund;
    AfterSaleInfoData afterSaleInfoData;
    String type;
    public final static String REPLENISH = "上门补货", REPLACE = "上门换货", REFUND = "上门退货";

    private ListViewAdapter<AfterSaleInfoData.AfterSaleInfo> listViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.net_support_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = REPLENISH;

        ((ImageView) ll_replenish.findViewById(R.id.image)).setImageResource(R.mipmap.replenish);
        ((ImageView) ll_replace.findViewById(R.id.image)).setImageResource(R.mipmap.replace);
        ((ImageView) ll_refund.findViewById(R.id.image)).setImageResource(R.mipmap.refund);

        listViewAdapter = new ListViewAdapter.Builder<AfterSaleInfoData.AfterSaleInfo>()
                .setResource(R.layout.net_support_list_item)
                .setInitView((data, convertView) -> {

                    ((TextView) convertView.findViewById(R.id.customer_name)).setText(data.customer_name);
                    ((TextView) convertView.findViewById(R.id.operator_user_name)).setText("专员：" + data.operator_user_name);
                    ((TextView) convertView.findViewById(R.id.dispatch_time)).setText(data.dispatch_time);
                    ((TextView) convertView.findViewById(R.id.commissioner_name)).setText("指派人：" + data.commissioner_name);

                    ((TextView) convertView.findViewById(R.id.product_criteria)).setText(data.product_criteria.equals("1") ? "通" : "精");

                    ((TextView) convertView.findViewById(R.id.name)).setText(data.name + (data.nick_name.isEmpty() ? "" : "（" + data.nick_name + "）"));
                    ((TextView) convertView.findViewById(R.id.store_name)).setText(data.store_name.length() > 3 ? data.store_name.substring(0, 3) : data.store_name);

                    String amount = "";
                    switch (type) {
                        case REPLENISH:
                            ((TextView) convertView.findViewById(R.id.type)).setText(REPLENISH);
                            amount = REPLENISH.substring(2, 3);
                            break;
                        case REPLACE:
                            ((TextView) convertView.findViewById(R.id.type)).setText(REPLACE);
                            amount = REPLACE.substring(2, 3);
                            break;
                        case REFUND:
                            ((TextView) convertView.findViewById(R.id.type)).setText(REFUND);
                            amount = REFUND.substring(2, 3);
                            break;
                    }


                    switch (data.level_type) {
                        case 1:
                            ((TextView) convertView.findViewById(R.id.tv_amount)).setText(amount + data.amount + data.unit);
                            break;
                        case 2:
                            ((TextView) convertView.findViewById(R.id.tv_amount)).setText(amount + data.level_2_value + data.level_2_unit);
                            break;
                        case 3:
                            ((TextView) convertView.findViewById(R.id.tv_amount)).setText(amount + data.level_3_value + data.level_3_unit);
                            break;
                    }

                    ImageLoad.loadImageViewLoding(data.product_img, convertView.findViewById(R.id.product_img));

                })
                .build(getContext());
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);


        showLoading();
        getVMWithFragment().loadingWithNewLiveData(AfterSaleInfoData.class, getArgs())
                .observe(this, afterSaleInfoData -> {
                    dismissLoading();
                    this.afterSaleInfoData = afterSaleInfoData;
                    listViewAdapter.addAll(afterSaleInfoData.buGeting_list);

                    if (afterSaleInfoData.buTotalNum > 0) {
                        ((TextView) ll_replenish.findViewById(R.id.count)).setText(afterSaleInfoData.buTotalNum + "");
                    } else {
                        ll_replenish.findViewById(R.id.count).setVisibility(View.GONE);
                    }

                    if (afterSaleInfoData.huanTotalNum > 0) {
                        ((TextView) ll_replace.findViewById(R.id.count)).setText(afterSaleInfoData.huanTotalNum + "");
                    } else {
                        ll_replace.findViewById(R.id.count).setVisibility(View.GONE);
                    }

                    if (afterSaleInfoData.tuiTotalNum > 0) {
                        ((TextView) ll_refund.findViewById(R.id.count)).setText(afterSaleInfoData.tuiTotalNum + "");
                    } else {
                        ll_refund.findViewById(R.id.count).setVisibility(View.GONE);
                    }
                });

    }

    @OnClick({R.id.ll_refund, R.id.ll_replace, R.id.ll_replenish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_replenish:
                listViewAdapter.clear();
                listViewAdapter.addAll(afterSaleInfoData.buGeting_list);
                type = REPLENISH;
                break;
            case R.id.ll_replace:
                listViewAdapter.clear();
                listViewAdapter.addAll(afterSaleInfoData.huanGeting_list);
                type = REPLACE;
                break;
            case R.id.ll_refund:
                listViewAdapter.clear();
                listViewAdapter.addAll(afterSaleInfoData.tuiGeting_list);
                type = REFUND;
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
        Intent intent = new Intent(getContext(), NetSupportDetailActivity.class);
        intent.putExtra("entityId", listViewAdapter.getItem(position).entity_id);
        intent.putExtra("type", type);
        intent.putExtra("original", listViewAdapter.getItem(position).original_amount + listViewAdapter.getItem(position).unit);
        intent.putExtra("amount", listViewAdapter.getItem(position).amount + listViewAdapter.getItem(position).avg_unit);
        intent.putExtra("money", listViewAdapter.getItem(position).discount_price + "元/" + listViewAdapter.getItem(position).unit);
        startActivity(intent);
    }
}