package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
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

import static android.text.Html.FROM_HTML_MODE_LEGACY;

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
    View current;
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
        updateTextView( current = ll_replenish, R.color.orange_red, R.drawable.bg_ring_orange);
        ((ImageView) ll_replenish.findViewById(R.id.image)).setImageResource(R.mipmap.replenish);
        ((ImageView) ll_replace.findViewById(R.id.image)).setImageResource(R.mipmap.replace);
        ((ImageView) ll_refund.findViewById(R.id.image)).setImageResource(R.mipmap.refund);

        listViewAdapter = new ListViewAdapter.Builder<AfterSaleInfoData.AfterSaleInfo>()
                .setResource(R.layout.net_support_list_item)
                .setInitView((data, convertView) -> {

                    ((TextView) convertView.findViewById(R.id.customer_name)).setText(data.customer_name);
                    ((TextView) convertView.findViewById(R.id.pick_user_name)).setText("专员：" + data.pick_user_name);
                    ((TextView) convertView.findViewById(R.id.dispatch_time)).setText(data.dispatch_time);
                    ((TextView) convertView.findViewById(R.id.commissioner_name)).setText("指派人：" + data.commissioner_name);

                    ((TextView) convertView.findViewById(R.id.product_criteria)).setText(data.product_criteria.equals("1") ? "通" : "精");

                    String level3 = TextUtils.isEmpty(data.level_3_unit) ? "" : "*" + data.level_3_value + data.level_3_unit;
                    String level2 = TextUtils.isEmpty(data.level_2_unit) ? "" : "（" + data.level_2_value + data.level_2_unit + level3 + "）";
                    String value = data.price + "元/" + data.unit + (data.level_type == 3 ? "" : level2);
                    String styledText = "<font color='grey'>" + value + "</font>" + "<font color='red'>×" + data.original_amount + "</font>";
                    ((TextView) convertView.findViewById(R.id.unit)).setText(Html.fromHtml(styledText, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);


                    value = data.original_amount + data.unit + "/共" + (data.price * data.original_amount) + "元";
                    ((TextView) convertView.findViewById(R.id.price)).setText(value);

                    styledText = "<font color='black'>" + data.name + "</font>" + "<font color='grey'><small>（" + data.nick_name + "）</small></font>";
                    ((TextView) convertView.findViewById(R.id.name)).setText(Html.fromHtml(styledText, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
//                    ((TextView) convertView.findViewById(R.id.store_name)).setText(data.store_name.length() > 3 ? data.store_name.substring(0, 3) : data.store_name);
                    ((TextView) convertView.findViewById(R.id.store_name)).setText(data.store_name);

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

                    ImageLoad.loadRoundImage(data.product_img, 25, convertView.findViewById(R.id.product_img), R.mipmap.baifangjiudain_bg);

                })
                .build(getContext());
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);


        showLoading();
        getVMWithFragment().loadingWithNewLiveData(AfterSaleInfoData.class, getArgs())
                .observe(this, afterSaleInfoData -> {
                    dismissLoading();
                    this.afterSaleInfoData = afterSaleInfoData;
                    listViewAdapter.clear();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getVMWithFragment().loading(AfterSaleInfoData.class, getArgs());
    }

    @OnClick({R.id.ll_refund, R.id.ll_replace, R.id.ll_replenish})
    public void onClick(View view) {
        updateTextView(current, R.color.gray_c2c2c2, R.drawable.bg_ring_grey);
        updateTextView(current = view, R.color.orange_red, R.drawable.bg_ring_orange);

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

    private void updateTextView(View current, int colorId, int drawableId) {
        TextView textView;
        textView = current.findViewById(R.id.tab);
        textView.setTextColor(getResources().getColor(colorId, null));
        textView = current.findViewById(R.id.count);
        textView.setTextColor(getResources().getColor(colorId, null));
        textView.setBackground(getResources().getDrawable(drawableId, null));
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
        startActivityForResult(intent, 1);
    }
}