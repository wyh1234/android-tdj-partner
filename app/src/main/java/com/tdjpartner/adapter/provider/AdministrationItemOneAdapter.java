package com.tdjpartner.adapter.provider;

import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.utils.GeneralUtils;

import java.math.BigDecimal;

public class AdministrationItemOneAdapter extends BaseItemProvider<ParentList.CustomerListBean, BaseViewHolder> {
    private int total;


    public void setTotal(int total) {
        this.total = total;
    }
    @Override
    public int viewType() {
        return AdministrationAdapter.LIST;
    }


    @Override
    public int layout() {
        return R.layout.administration_baifang_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ParentList.CustomerListBean customerListBean, int pos) {
        if (total <= 3) {
            baseViewHolder.setGone(R.id.ll_bottom, false);
        } else {
            if (mData.size()-1== pos) {
                baseViewHolder.setGone(R.id.ll_bottom, true);
            } else {
                baseViewHolder.setGone(R.id.ll_bottom, false);
            }
            if (customerListBean.isF()) {
                baseViewHolder.setGone(R.id.tv_close, true);
                baseViewHolder.setGone(R.id.tv_open, false);
            } else {
                baseViewHolder.setGone(R.id.tv_close, false);
                baseViewHolder.setGone(R.id.tv_open, true);
            }

        }
        baseViewHolder.addOnClickListener(R.id.ll_bottom);
        baseViewHolder.setGone(R.id.tv_money,true);
        baseViewHolder.addOnClickListener(R.id.tv_money);
        baseViewHolder.setText(R.id.tv_name, customerListBean.getBuy_name());
        if (customerListBean.getAmount().compareTo(BigDecimal.valueOf(0)) == 1) {
            baseViewHolder.setText(R.id.tv_staste, "已下单");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(), R.color.gray_66));
            baseViewHolder.setText(R.id.tv_money, customerListBean.getAmount().toString());

        } else {
            baseViewHolder.setText(R.id.tv_staste, "未下单");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(), R.color.view_bg1));
            baseViewHolder.setText(R.id.tv_money, "——");

        }
        baseViewHolder.setText(R.id.tv_num, customerListBean.getCall_name());


    }
}

