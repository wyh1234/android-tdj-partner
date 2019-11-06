package com.tdjpartner.utils;

import android.widget.TextView;

import com.tdjpartner.R;

public class StringUtils {

    public static void tvStatus(String tv_staste, TextView textView){
        switch (tv_staste){
            case  "wait_seller_to_audit":
                textView.setText("待审核");
                break;
            case  "wait_buyer_pay":
                textView.setText("待付款");
                break;
            case  "wait_seller_confirm_goods":
                textView.setText("待卖家确认");
                break;
            case  "wait_seller_send_goods":
                textView.setText("待发货");
                break;
            case  "wait_buyer_confirm_goods":
                textView.setText("待收货");
                break;
            case  "trade_success":
                textView.setText("已收货");
                break;
            case  "wait_buyer_evaluate":
                textView.setText("待买家评价");
                break;
            case  "wait_seller_evaluate":
                textView.setText("待卖家评价");
                break;
            case  "trade_finished":
                textView.setText("已成交");
                break;
            case  "trade_closed":
                textView.setText("已关闭");
                break;
        }

    }
}
