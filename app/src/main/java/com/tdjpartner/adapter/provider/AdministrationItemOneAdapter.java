package com.tdjpartner.adapter.provider;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.utils.GeneralUtils;

import java.math.BigDecimal;

public class AdministrationItemOneAdapter extends BaseItemProvider<ParentList.CustomerListBean, BaseViewHolder> {
@Override
public int viewType() {
        return AdministrationAdapter.LIST_ONE;
        }

@Override
public int layout() {
        return R.layout.administration_baifang_item;
        }

@Override
public void convert(BaseViewHolder baseViewHolder, ParentList.CustomerListBean customerListBean, int pos) {
        if (mData.size()>3){
        baseViewHolder.setVisible(R.id.ll_bottom,false);
        if (customerListBean.isF()){
        baseViewHolder.setVisible(R.id.tv_close,false);
        baseViewHolder.setVisible(R.id.tv_open,true);

        baseViewHolder.itemView.setVisibility(View.VISIBLE);
        }else {
        baseViewHolder.setVisible(R.id.tv_close,true);
        baseViewHolder.setVisible(R.id.tv_open,false);
        if (pos>2){
        baseViewHolder.itemView.setVisibility(View.GONE);
        }else {
        baseViewHolder.itemView.setVisibility(View.VISIBLE);
        }

        }
        }else{
        baseViewHolder.setVisible(R.id.ll_bottom,true);
        baseViewHolder.itemView.setVisibility(View.VISIBLE);
        }

        baseViewHolder.setText(R.id.tv_name,customerListBean.getBuy_name());
        if (customerListBean.getAmount().compareTo(BigDecimal.valueOf(0))==1){
            baseViewHolder.setText(R.id.tv_staste,"已下单");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.gray_66));
            baseViewHolder.setText(R.id.tv_money,customerListBean.getAmount().toString());

        }else {
            baseViewHolder.setText(R.id.tv_staste,"未下单");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.view_bg1));
            baseViewHolder.setText(R.id.tv_money,"——");

        }
                   baseViewHolder.setText(R.id.tv_num,customerListBean.getCall_name());


        }
        }

