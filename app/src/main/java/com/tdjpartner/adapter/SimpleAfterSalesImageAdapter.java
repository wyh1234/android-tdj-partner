package com.tdjpartner.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.model.RefundDetail;

public class SimpleAfterSalesImageAdapter extends BaseQuickAdapter<RefundDetail.ItemsBean,BaseViewHolder> {
    public SimpleAfterSalesImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RefundDetail.ItemsBean itemsBean) {
        baseViewHolder.setText(R.id.handle_info,itemsBean.getHandle_info()+"");
        baseViewHolder.setText(R.id.create_time,itemsBean.getCreate_time()+"");
        baseViewHolder.setText(R.id.handle_operator,itemsBean.getHandle_operator()+"");
        baseViewHolder.setText(R.id.tv_remark,itemsBean.getRemarks() == null || itemsBean.getRemarks().isEmpty()? "无":itemsBean.getRemarks()+"");
    }
}
