package com.tdjpartner.adapter.provider;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MessageListAdapter;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.utils.glide.ImageLoad;

import java.math.BigDecimal;
import java.util.List;

public class GoodsListAdapter extends BaseItemProvider<GoodsInfo.ObjBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return MessageListAdapter.GoodsList;
    }

    @Override
    public int layout() {
        return R.layout.goods_list_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, GoodsInfo.ObjBean goodsInfo, int i) {
        ImageLoad.loadImageViewLoding(goodsInfo.getImage(),baseViewHolder.getView(R.id.iv));
        baseViewHolder.setText(R.id.tv,goodsInfo.getName());
        baseViewHolder.setText(R.id.tv_nickname,goodsInfo.getNickName());
        baseViewHolder.setText(R.id.tv_tag,goodsInfo.getStoreName());

        //如果只有一个规格
        if ((goodsInfo.getMaxPrice()).compareTo(new BigDecimal(-1)) == 0 && goodsInfo.getSpecs().size() == 1) {

            GoodsInfo.ObjBean.SpecsBean gsf = goodsInfo.getSpecs().get(0);
            if (gsf == null) return;


            if (gsf.getLevelType()== 1) {
                baseViewHolder.setText(R.id.tv_tiltle,goodsInfo.getMinPrice()+"元/"+goodsInfo.getUnit());

            } else {
                if (gsf.getLevelType() == 3) {
                    baseViewHolder.setText(R.id.tv_tiltle,goodsInfo.getMinPrice()+"元/"+goodsInfo.getUnit()+"("+gsf.getLevel2Value() + ""+ gsf.getLevel2Unit()+"*"+gsf.getLevel3Value()+  gsf.getLevel3Unit()+ ""+")");
                } else {
                    baseViewHolder.setText(R.id.tv_tiltle,goodsInfo.getMinPrice()+"元/"+goodsInfo.getUnit()+"("+gsf.getLevel2Value() + ""+ gsf.getLevel2Unit()+")");
                }

            }


        } else {
            baseViewHolder.setText(R.id.tv_tiltle,goodsInfo.getMinPrice()+"元/"+goodsInfo.getUnit());
        }
    }
}
