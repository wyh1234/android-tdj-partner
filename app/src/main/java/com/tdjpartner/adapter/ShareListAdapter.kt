package com.tdjpartner.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tdjpartner.R

class ShareListAdapter : BaseQuickAdapter<String,BaseViewHolder>(R.layout.share_list_adapter) {

    override fun convert(ho: BaseViewHolder?, p1: String?) {
        ho?.setText(R.id.tv_remark,p1)
    }
}