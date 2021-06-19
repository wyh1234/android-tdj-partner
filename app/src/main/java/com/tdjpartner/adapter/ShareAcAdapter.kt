package com.tdjpartner.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tdjpartner.R
import com.tdjpartner.model.IntegralItem

class ShareAcAdapter : BaseQuickAdapter<IntegralItem,BaseViewHolder>(R.layout.item_share_record) {

    override fun convert(holder: BaseViewHolder?, item: IntegralItem?) {
        var mobile = item?.mobile
        var sb = StringBuilder()
        var phoneNum = sb.append(mobile?.subSequence(0,3)).append("****").append(mobile?.subSequence(7,11))
        holder?.setText(R.id.tv_name,item?.nickName)
            holder?.setText(R.id.tv_phone_num,phoneNum)
            holder?.setText(R.id.tv_item_action,item?.typeName)
            holder?.setText(R.id.tv_date,item?.createTime?.subSequence(0,10))
            holder?.setText(R.id.tv_points, item?.integral.toString())
    }
}