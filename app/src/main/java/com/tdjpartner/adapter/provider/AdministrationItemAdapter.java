package com.tdjpartner.adapter.provider;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.chaychan.adapter.MultipleItemRvAdapter;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdministrationAdapter;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.utils.GeneralUtils;

public class AdministrationItemAdapter extends BaseItemProvider<ParentList.UserListBean, BaseViewHolder> {
    private int total;
    private String time;


    public void setTotal(int total) {
        this.total = total;
    }
    public void setTime(String time) {
        this.time = time;
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
    public void convert(BaseViewHolder baseViewHolder, ParentList.UserListBean userListBean, int pos) {
//        LogUtils.e(pos);
        if (total <= 3) {
            baseViewHolder.setGone(R.id.ll_bottom, false);
        } else {

            if (mData.size()-1== pos) {
                baseViewHolder.setGone(R.id.ll_bottom, true);

            } else {
                baseViewHolder.setGone(R.id.ll_bottom, false);
            }
            if (userListBean.isF()) {
                baseViewHolder.setGone(R.id.tv_close, true);
                baseViewHolder.setGone(R.id.tv_open, false);
            } else {
                baseViewHolder.setGone(R.id.tv_close, false);
                baseViewHolder.setGone(R.id.tv_open, true);
            }

        }
        baseViewHolder.addOnClickListener(R.id.ll_bottom);
        baseViewHolder.addOnClickListener(R.id.tv_num);

        baseViewHolder.setText(R.id.tv_name,userListBean.getNick_name());
        if (userListBean.getCall_num() > 0) {
            baseViewHolder.setGone(R.id.tv_num,true);
            baseViewHolder.setGone(R.id.ll,false);
            baseViewHolder.setText(R.id.tv_num,userListBean.getConversion_num()+"/"+userListBean.getCall_num());
            baseViewHolder.setText(R.id.tv_staste, "已出勤");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.gray_66));
            Drawable drawable = mContext.getResources().getDrawable(
                    R.mipmap.right_bg);
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());

            ((TextView)baseViewHolder.getView(R.id.tv_num)).setCompoundDrawables(null,null,drawable,null);
        } else {

            baseViewHolder.setText(R.id.tv_staste, "未出勤");
            baseViewHolder.setTextColor(R.id.tv_staste, GeneralUtils.getColor(AppAplication.getAppContext(),R.color.view_bg1));
            baseViewHolder.setGone(R.id.tv_num,false);
            baseViewHolder.setGone(R.id.ll,true);
            if (time.equals(GeneralUtils.getCurr())){

                baseViewHolder.setGone(R.id.tv_right,false);
                baseViewHolder.setGone(R.id.tv_cq,true);
            }else {
                baseViewHolder.setGone(R.id.tv_cq,false);
                baseViewHolder.setGone(R.id.tv_right,true);
            }



        }

        baseViewHolder.addOnClickListener(R.id.ll);


    }
}
