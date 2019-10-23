package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.utils.glide.ImageLoad;

import java.math.BigDecimal;
import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderList.ItemsBean, BaseViewHolder> {
    public OrderListAdapter(int layoutResId, @Nullable List<OrderList.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderList.ItemsBean orderList) {
        baseViewHolder.setText(R.id.iv_store_name,orderList.getLastName());
        baseViewHolder.setText(R.id.tv_time,"下单时间："+orderList.getCreateTime());
        baseViewHolder.setText(R.id.tv_count_image,orderList.getItemCount()+"");
        baseViewHolder.setText(R.id.cart_price_sum,"总计："+orderList.getActualTotalCost()+"元");

        ImageLoad.loadImageViewLoding(orderList.getExtraField().get(0).getProductImage(),baseViewHolder.getView(R.id.iv1));
        baseViewHolder.setText(R.id.tv_goods_name,orderList.getExtraField().get(0).getProductName());
        baseViewHolder.setText(R.id.tv_nickname,orderList.getExtraField().get(0).getNickName());
        baseViewHolder.setText(R.id.tv_tag,orderList.getExtraField().get(0).getStoreName());

            if (orderList.getExtraField().get(0).getLevelType()== 1) {
                baseViewHolder.setText(R.id.tv_tiltle,orderList.getExtraField().get(0).getProductPrice()+"元/"+orderList.getExtraField().get(0).getProductUnit());
            } else if (orderList.getExtraField().get(0).getLevelType()== 2){
             /*   baseViewHolder.setText(R.id.tv_tiltle,orderList.getExtraField().get(0).getProductPrice()+"元/"+
                        orderList.getExtraField().get(0).getProductUnit()+"("+orderList.getExtraField().get(0).getLevel2Value() + ""+
                        orderList.getExtraField().get(0).getLevel2Unit()+"*"+orderList.getExtraField().get(0).getLevel3Value()+  orderList.getExtraField().get(0).getLevel3Unit()+ ""+")");*/
                baseViewHolder.setText(R.id.tv_tiltle,orderList.getExtraField().get(0).getProductPrice()+"元/"+
                        orderList.getExtraField().get(0).getProductUnit()+"("+orderList.getExtraField().get(0).getLevel2Value() + ""+
                        orderList.getExtraField().get(0).getLevel2Unit()+")");
            }else if (orderList.getExtraField().get(0).getLevelType()== 3){
                baseViewHolder.setText(R.id.tv_tiltle,orderList.getExtraField().get(0).getProductPrice()+"元/"
                        +orderList.getExtraField().get(0).getProductUnit()+"("+orderList.getExtraField().get(0).getLevel2Value() +
                        ""+ orderList.getExtraField().get(0).getLevel2Unit()+"*"+orderList.getExtraField().get(0).getLevel3Value()+  orderList.getExtraField().get(0).getLevel3Unit()+ ""+")");
            }else {
            baseViewHolder.setText(R.id.tv_tiltle,orderList.getExtraField().get(0).getProductPrice()+"元/"+orderList.getExtraField().get(0).getProductUnit());

        }


    }
}
