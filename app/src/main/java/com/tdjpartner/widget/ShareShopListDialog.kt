package com.tdjpartner.widget

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tdjpartner.R
import com.tdjpartner.adapter.ShareListAdapter

open class ShareShopListDialog : BaseDialogFragment() {

    private val inviteStr = arrayListOf("全部", "已下单", "未下单", "待审核", "审核失败")
    private var recyclerView : RecyclerView ?= null
    private var mAdapter : ShareListAdapter ?= null

    private var listener : OnItemClickListener ?= null

    override fun attachLayoutRes(): Int {
        return R.layout.shape_list_dialog
    }

    override fun initData() {

    }

    override fun initView() {
        recyclerView = mRootView.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ShareListAdapter()
        recyclerView?.adapter = mAdapter
        mAdapter?.setNewData(inviteStr)

        mAdapter?.setOnItemClickListener { _, _, p2 ->
            if (listener != null){
                listener?.onItemClick(p2)
            }
            finishSelf()
        }
    }

    companion object{
        fun newInstance(): ShareShopListDialog {
            return ShareShopListDialog()
        }
    }

    open fun setOnItemClickListener(listener : OnItemClickListener){
        this.listener = listener
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}