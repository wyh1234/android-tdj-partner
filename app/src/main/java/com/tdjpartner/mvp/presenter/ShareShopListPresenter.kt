package com.tdjpartner.mvp.presenter

import com.tdjpartner.base.BasePresenter
import com.tdjpartner.common.RequestPresenter
import com.tdjpartner.http.BaseObserver
import com.tdjpartner.model.ShareShopListBean
import com.tdjpartner.model.UserInfo
import com.tdjpartner.mvp.model.Model
import com.tdjpartner.ui.activity.ShareShopListActivity
import kotlin.collections.HashMap

class ShareShopListPresenter : BasePresenter<Model, ShareShopListActivity>() {
    override fun loadModel(): Model? = null
    fun getData(userInfo: UserInfo,
                date:String,
                type : Int) {
        val map = HashMap<String,Any>()
        map["applyCode"] = userInfo.verifyCode
        map["site"] = userInfo.site
        map["queryDate"] = date
        map["type"] = type
        map["pn"] = 1
        map["ps"] = 100
        
        getIView().addSubscribe(RequestPresenter.getShareShopList(userInfo.verifyCode,
            userInfo.site,date, type,1,100,
             object : BaseObserver<ShareShopListBean>(getIView().context,true){
                 override fun onSuccess(it: ShareShopListBean?) {
                     getIView().getDataSuccess(it)
                 }

                 override fun onFailed(e: Throwable?) {

                 }

             }
        ))
            
        
    }

}