package com.tdjpartner.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class OrderListDetailsAdapter extends BaseQuickAdapter<OrderDetail.ItemsBean, BaseViewHolder> {
    private long now_12;//中午12点的时间
    private long now_12_delayed = 0;//缓存的时间
    private String statusCode = "";//缓存的时间
    public OrderListDetailsAdapter(int layoutResId, @Nullable List<OrderDetail.ItemsBean> data, String statusCode) {
        super(layoutResId, data);
        this.statusCode = statusCode;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderDetail.ItemsBean orderList) {
        ImageLoad.loadImageViewLoding(orderList.getImage(),baseViewHolder.getView(R.id.iv1));
        baseViewHolder.setText(R.id.tv_goods_name,orderList.getName());
        baseViewHolder.setText(R.id.tv_nickname,orderList.getNickName());
        baseViewHolder.setText(R.id.tv_tag,orderList.getStoreName());
        baseViewHolder.setText(R.id.tv_order_no,"商品编号："+orderList.getQrCodeId());
        baseViewHolder.addOnClickListener(R.id.tv_copys);
        baseViewHolder.addOnClickListener(R.id.tv_after);
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
        if (orderList.getStatus() == 6 ) {
            baseViewHolder.setText(R.id.tv_after, "售后中");
            baseViewHolder.setVisible(R.id.tv_after,true);
            baseViewHolder.setBackgroundRes(R.id.tv_after,R.drawable.time_shap_gray);
            baseViewHolder.setTextColor(R.id.tv_after, Color.parseColor("#666666"));

        }else if ( orderList.getStatus() == 9){
            baseViewHolder.setText(R.id.tv_after, "售后结束");
            baseViewHolder.setVisible(R.id.tv_after,true);
            baseViewHolder.setBackgroundRes(R.id.tv_after,R.drawable.time_shap_gray);
            baseViewHolder.setTextColor(R.id.tv_after, Color.parseColor("#666666"));
        }else if (orderList.getStatus() == 0||orderList.getStatus() == 1 || statusCode.equals("wait_seller_send_goods")){
            baseViewHolder.setVisible(R.id.tv_after,false);
        }else {
            //            if (System.currentTimeMillis() < now_12) {
            baseViewHolder.setVisible(R.id.tv_after,true);
            baseViewHolder.setText(R.id.tv_after, "申请售后");
            baseViewHolder.setBackgroundRes(R.id.tv_after,R.drawable.time_shap);
            baseViewHolder.setTextColor(R.id.tv_after, Color.parseColor("#FF6633"));
            //如果缓存的时间和中午的时间不相等则重新
//                if (now_12_delayed != now_12) {
//                    now_12_delayed = now_12;
//                    baseViewHolder.getView(R.id.tv_after).removeCallbacks(runnable);
//                    baseViewHolder.getView(R.id.tv_after).postDelayed(runnable, now_12 - System.currentTimeMillis() + 10L);
//                }
//            }{
//                baseViewHolder.setVisible(R.id.tv_after,false);
//            }

        }




    }

    public void setExpectDeliveredDate(String expectDeliveredDate) {
        now_12 = GeneralUtils.dateStringToLong(expectDeliveredDate + " 12:00:00");
    }
    private Runnable runnable = this::notifyDataSetChanged;
}
