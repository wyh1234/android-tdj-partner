package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.RefundDetail;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.List;

public class AdterSalesOrderAdapter extends BaseQuickAdapter<RefundDetail, BaseViewHolder> {
    public AdterSalesOrderAdapter(int layoutResId, @Nullable List<RefundDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RefundDetail refundDetail) {
        baseViewHolder.setText(R.id.iv_store_name,refundDetail.getReceive_hotel_name());
        baseViewHolder.setText(R.id.tv_time,"下单时间："+refundDetail.getCreate_time());
        baseViewHolder.addOnClickListener(R.id.order_ok);
        ImageLoad.loadImageViewLoding(refundDetail.getProduct_img(),baseViewHolder.getView(R.id.iv1));
        baseViewHolder.setText(R.id.tv_goods_name,refundDetail.getName());
        String nickName = refundDetail.getNick_name();
        baseViewHolder.setText(R.id.tv_nickname, TextUtils.isEmpty(nickName)? "":"("+(nickName.length() > 11 ? nickName.substring(0, 11) + "..." : nickName) + ")");
        baseViewHolder.setText(R.id.tv_tag,refundDetail.getStore_name());

        if (refundDetail.getLevel_type()== 1) {
            baseViewHolder.setText(R.id.tv_tiltle,refundDetail.getPrice()+"元/"+refundDetail.getUnit());
        } else if (refundDetail.getLevel_type()== 2){
            baseViewHolder.setText(R.id.tv_tiltle,refundDetail.getPrice()+"元/"+
                    refundDetail.getUnit()+"("+refundDetail.getLevel_2_value() + ""+
                    refundDetail.getLevel_2_unit()+")");
        }else if (refundDetail.getLevel_type()== 3){
            baseViewHolder.setText(R.id.tv_tiltle,refundDetail.getPrice()+"元/"
                    +refundDetail.getUnit()+"("+refundDetail.getLevel_2_value() +
                    ""+ refundDetail.getLevel_2_unit()+"*"+refundDetail.getLevel_3_value()+  refundDetail.getLevel_3_unit()+ ""+")");
        }else {
            baseViewHolder.setText(R.id.tv_tiltle,refundDetail.getPrice()+"元/"+refundDetail.getUnit());

        }
        String statss = PublicCache.getAfterSaleType().getValueOfKey(refundDetail.getApply_type());
        switch (refundDetail.getStatus()) {
            case 1:
                statss += "申请";
                break;
            case 6:
                statss += "完成";
                break;
            case 2:
            case 3:
            case 7:
                statss += "中";
                break;
            case 4:
            case 5:
            case 8:
                statss = "拒绝" + statss;
                break;
        }
        baseViewHolder.setText(R.id.tv_staste,statss);
        baseViewHolder.setText(R.id.count_image,refundDetail.getOriginal_total_price()+"");
        baseViewHolder.setText(R.id.refund_price,refundDetail.getTotal_price()+"");
        baseViewHolder.setText(R.id.count_sum,refundDetail.getAmount()+"");
        baseViewHolder.setText(R.id.unit,refundDetail.getAvg_unit());

    }
}
