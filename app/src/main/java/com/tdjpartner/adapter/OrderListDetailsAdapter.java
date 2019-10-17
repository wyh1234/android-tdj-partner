package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class OrderListDetailsAdapter extends BaseQuickAdapter<OrderDetail.ItemsBean, BaseViewHolder> {
    public OrderListDetailsAdapter(int layoutResId, @Nullable List<OrderDetail.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderDetail.ItemsBean orderList) {
        ImageLoad.loadImageViewLoding(orderList.getImage(),baseViewHolder.getView(R.id.iv1));
        baseViewHolder.setText(R.id.tv_goods_name,orderList.getName());
        baseViewHolder.setText(R.id.tv_nickname,orderList.getNickName());
        baseViewHolder.setText(R.id.tv_tag,orderList.getStoreName());

        if (orderList.getLevelType()== 1) {
            baseViewHolder.setText(R.id.tv_tiltle,orderList.getPrice()+"元/"+orderList.getUnit());
        } else if (orderList.getLevelType()== 2){
            baseViewHolder.setText(R.id.tv_tiltle,orderList.getPrice()+"元/"+
                    orderList.getUnit()+"("+orderList.getLevel2Value() + ""+
                    orderList.getLevel2Unit()+")");
        }else if (orderList.getLevelType()== 3){
            baseViewHolder.setText(R.id.tv_tiltle,orderList.getPrice()+"元/"
                    +orderList.getUnit()+"("+orderList.getLevel2Value() +
                    ""+ orderList.getLevel2Unit()+"*"+orderList.getLevel3Value()+  orderList.getLevel3Unit()+ ""+")");
        }else {
            baseViewHolder.setText(R.id.tv_tiltle,orderList.getPrice()+"元/"+orderList.getUnit());

        }



    }
}
