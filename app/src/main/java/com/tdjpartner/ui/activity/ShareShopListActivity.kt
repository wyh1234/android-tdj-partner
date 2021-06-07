package com.tdjpartner.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.TimePickerView
import com.tdjpartner.R
import com.tdjpartner.R.layout.page_empty
import com.tdjpartner.adapter.ShareShopListAdapter
import com.tdjpartner.base.BaseActivity
import com.tdjpartner.model.ShareShopListBean
import com.tdjpartner.model.UserInfo
import com.tdjpartner.mvp.presenter.ShareShopListPresenter
import com.tdjpartner.utils.ColorUtil
import com.tdjpartner.utils.DensityUtil
import com.tdjpartner.utils.MySpecialStyle
import com.tdjpartner.utils.cache.UserUtils
import com.tdjpartner.utils.statusbar.Eyes
import com.tdjpartner.widget.ShareShopListDialog
import com.zyinux.specialstring.relase.SpecialStringBuilder
import java.text.SimpleDateFormat
import java.util.*

class ShareShopListActivity :BaseActivity<ShareShopListPresenter>(), View.OnClickListener {

    private var txtDate : TextView ?= null
    private var txtSelect : TextView ?= null
    private var txtTotal : TextView ?= null
    private var recyclerView : RecyclerView ?= null
    private var loginBean : UserInfo ?= null
    private val mAdapter :ShareShopListAdapter by lazy { ShareShopListAdapter() }
    private val inviteStr = arrayOf("全部", "已下单", "未下单", "待审核", "审核失败")
    private var type : Int = 0
    private var pvTime : TimePickerView ?= null

    override fun loadPresenter(): ShareShopListPresenter = ShareShopListPresenter()

    override fun initData() {
        txtDate?.text.toString().let { mPresenter.getData(loginBean!!, it,type) }
        setTotalShop(0,0,0)
    }

    override fun initView() {
        loginBean = UserUtils.getInstance().loginBean
        Eyes.translucentStatusBar(this, true)
        txtDate = findViewById(R.id.txt_date)
        txtSelect = findViewById(R.id.txt_select)
        txtTotal = findViewById(R.id.txt_total)
        recyclerView = findViewById(R.id.swipe_target)
        findViewById<ImageView>(R.id.btn_back).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tv_title)?.text = "我的邀请"
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = mAdapter
        txtSelect?.text = inviteStr[0]
        mAdapter.emptyView = getEmptyView()
        initTimePickView()
        txtSelect?.setOnClickListener(this)
        txtDate?.setOnClickListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_share_shop_list
    }

    private fun setTotalShop(register:Int,
                     order:Int,
                     unSuccess:Int){
        val style = MySpecialStyle()
        val sb = SpecialStringBuilder()
        style.setColor(ColorUtil.getColor(R.color.orange_yellow_ff7d01))
        sb.append("邀请成功注册 $register 家（", style)
        style.setColor(ColorUtil.getColor(R.color.f120a539))
        sb.append("已下单 $order 家", style)
        style.setColor(ColorUtil.getColor(R.color.orange_yellow_ff7d01))
        sb.append(" / 未下单 $unSuccess 家）", style)
        txtTotal!!.text = sb.charSequence
    }

    fun getDataSuccess(it: ShareShopListBean?) {
        setTotalShop(it!!.total,it.orderCount,it.noOrderCount)
        mAdapter.setNewData(it.list)
    }

    private fun initTimePickView(){
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        startDate.set(2013,1,1)
        endDate.set(2030,11,31)
        txtDate?.text = getTime(selectedDate.time)
        val timePickerBuilder = TimePickerBuilder(this) { date, _ ->
            txtDate?.text = getTime(date)
            mPresenter.getData(loginBean!!, date.toString(), type)
        }
        pvTime = timePickerBuilder.setType(booleanArrayOf(true, true, true, false, false, false))
            .setCancelText("取消")
            .setSubmitText("确定")
            .setContentTextSize(18)
            .setTitleSize(20)
            .setOutSideCancelable(true)
            .isCyclic(false)
            .setSubmitColor(DensityUtil.getColor(this, R.color.gray_66))
            .setCancelColor(DensityUtil.getColor(this, R.color.gray_66))
            .setTitleBgColor(DensityUtil.getColor(this, R.color.gray_df))
            .setBgColor(DensityUtil.getColor(this, R.color.white))
            .setDate(selectedDate)
            .setRangDate(startDate, endDate)
            .setLabel("年", "月", "日", "时", "分", "秒")
            .isCenterLabel(false)
            .isDialog(true)
            .build()

        val dialog = pvTime?.dialog
        if (dialog != null){
            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
            )
            params.leftMargin = 0
            params.rightMargin = 0
            pvTime?.dialogContainerLayout?.layoutParams = params
            val dialogWindow = dialog.window
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)
                dialogWindow.setGravity(Gravity.BOTTOM)
                dialogWindow.setDimAmount(0.1f)
            }
        }
    }

    private fun getTime(date: Date) : String{
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txt_date ->
                if (pvTime == null) {
                    initTimePickView()
                    pvTime!!.show()
                } else {
                    if (!pvTime!!.isShowing) {
                        pvTime!!.show()
                    }
                }
            R.id.txt_select ->{
                val newInstance = ShareShopListDialog.newInstance()
                newInstance.setOnItemClickListener(object : ShareShopListDialog.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        val str = inviteStr[position]
                        txtSelect?.text = str
                        type = position
                        mPresenter.getData(loginBean!!,txtDate?.text.toString(),type)
                    }
                })
                if(!newInstance.isAdded){
                    supportFragmentManager.beginTransaction()
                        .add(newInstance,"ShareShopListDialog")
                        .commitAllowingStateLoss()
                }
            }
        }
    }

    private fun getEmptyView():View{
        return layoutInflater.inflate(R.layout.page_empty,null)
    }
}