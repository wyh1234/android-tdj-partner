package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientInfo;

import java.util.List;

public class ClientMapAdapter extends BaseQuickAdapter<ClientInfo, BaseViewHolder> {
    public ClientMapAdapter(int layoutResId, @Nullable List<ClientInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClientInfo clientMapInfo) {
        if (clientMapInfo.getUserType()==1){
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.jiudianone_bg);
            baseViewHolder.setGone(R.id.tv_boss,true);
        }else if (clientMapInfo.getUserType()==2){
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.jiudiantwo_bg);
            baseViewHolder.setGone(R.id.tv_boss,true);
        }else if (clientMapInfo.getUserType()==3){
            baseViewHolder.setGone(R.id.tv_boss,false);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.jiudianthree_bg);
        }else {
            baseViewHolder.setGone(R.id.tv_boss,true);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.huangse);
        }
        baseViewHolder.setText(R.id.tv_tiltle,clientMapInfo.getName());
        baseViewHolder.setText(R.id.tv_regionCollNo,clientMapInfo.getRegionCollNo()+"-"+clientMapInfo.getRegionNo());
        baseViewHolder.setText(R.id.tv_address,clientMapInfo.getAddress());
        baseViewHolder.setText(R.id.tv_address,clientMapInfo.getAddress());
        baseViewHolder.setText(R.id.tv_boss,clientMapInfo.getBoss());
        baseViewHolder.setText(R.id.tv_num,baseViewHolder.getAdapterPosition()+"");


    }
}
