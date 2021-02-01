package com.tdjpartner.adapter.provider;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.utils.GeneralUtils;

public class AdministrationItemAdapter extends BaseItemProvider<ParentList.UserListBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return AdministrationAdapter.LIST;
    }

    @Override
    public int layout() {
        return R.layout.administration_baifang_item;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ParentList.UserListBean userListBean, int pos) {
        if (mData.size()>3){
            baseViewHolder.setVisible(R.id.ll_bottom,false);
            if (userListBean.isF()){
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

        baseViewHolder.setText(R.id.tv_name,userListBean.getNick_name());
        if (userListBean.getCall_num() > 0) {
            baseViewHolder.setVisible(R.id.tv_num,false);
            baseViewHolder.setVisible(R.id.ll,true);

            baseViewHolder.setText(R.id.tv_staste, "已出勤");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.gray_66));
        } else {

            baseViewHolder.setText(R.id.tv_staste, "未出勤");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.view_bg1));
            baseViewHolder.setVisible(R.id.tv_num,true);
            baseViewHolder.setVisible(R.id.ll,false);
            baseViewHolder.setText(R.id.tv_num,userListBean.getCustomer_num()+"/"+userListBean.getCall_num());
            Drawable drawable = mContext.getResources().getDrawable(
                    R.mipmap.right_bg);
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());

            ((TextView)baseViewHolder.getView(R.id.tv_num)).setCompoundDrawables(null,null,drawable,null);
        }



    }
}
