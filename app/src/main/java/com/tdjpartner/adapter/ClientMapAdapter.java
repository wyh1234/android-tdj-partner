package com.tdjpartner.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.utils.GeneralUtils;

import java.util.List;

public class ClientMapAdapter extends BaseQuickAdapter<ClientInfo, BaseViewHolder> {
    private Activity mContext;
    public ClientMapAdapter(int layoutResId, @Nullable List<ClientInfo> data,Activity context) {
        super(layoutResId, data);
        this.mContext = context;
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
            baseViewHolder.setGone(R.id.tv_boss,true);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.jiudianthree_bg);
        }else if (clientMapInfo.getUserType()==4){
            baseViewHolder.setGone(R.id.tv_boss,true);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.heise);
        }else if (clientMapInfo.getUserType()==5){
            baseViewHolder.setGone(R.id.tv_boss,true);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.huangse);
        }else {
            baseViewHolder.setGone(R.id.tv_boss,true);
            baseViewHolder.setImageResource(R.id.iv,R.mipmap.jiudiantwo_bg);
        }
        baseViewHolder.setText(R.id.tv_tiltle,clientMapInfo.getName());
        baseViewHolder.setText(R.id.tv_regionCollNo,clientMapInfo.getRegionCollNo());
        baseViewHolder.setText(R.id.tv_address,clientMapInfo.getAddress());
        baseViewHolder.setText(R.id.tv_boss,clientMapInfo.getBoss());
        baseViewHolder.setText(R.id.tv_num,(baseViewHolder.getAdapterPosition()+1)+"");
        baseViewHolder.getView(R.id.tv_boss).setOnClickListener(v->{
            GeneralUtils.action_call(new RxPermissions(mContext),clientMapInfo.getMobile(),mContext);});

    }
}
