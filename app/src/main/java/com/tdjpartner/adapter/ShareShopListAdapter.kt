package com.tdjpartner.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tdjpartner.R
import com.tdjpartner.model.ShareShopListBean

class ShareShopListAdapter : BaseQuickAdapter<ShareShopListBean.ListBean,BaseViewHolder>(R.layout.item_share_shop_list) {

    override fun convert(holder: BaseViewHolder?, item: ShareShopListBean.ListBean?) {
        holder?.setText(R.id.text_shop_name,item?.enterpriseCode)
        holder?.setText(R.id.text_person_name,item?.nickName)
        holder?.setText(R.id.text_shop_phone,item?.accountCode)
    }
}