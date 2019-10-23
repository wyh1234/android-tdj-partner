package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.SelectPerson;

import java.util.List;

public class SelectPersonAdapter extends BaseQuickAdapter<SelectPerson.ObjBean, BaseViewHolder> {
    public SelectPersonAdapter(int layoutResId, @Nullable List<SelectPerson.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SelectPerson.ObjBean selectPerson) {
        baseViewHolder.getView(R.id.iv).setSelected(selectPerson.isF());
        baseViewHolder.setText(R.id.tv_nickName_phone_isFullTime,
                selectPerson.getNickName()+"\t\t"+selectPerson.getPhone()+"\t\t"+(selectPerson.getIsFullTime()==0?"兼职":"市场全职"));
    }
}
