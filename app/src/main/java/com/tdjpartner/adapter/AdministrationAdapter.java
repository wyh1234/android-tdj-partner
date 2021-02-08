package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.MultipleItemRvAdapter;
import com.tdjpartner.adapter.provider.AdministrationHeadAdapter;
import com.tdjpartner.adapter.provider.AdministrationItemAdapter;
import com.tdjpartner.adapter.provider.AdministrationItemOneAdapter;
import com.tdjpartner.adapter.provider.GoodsListAdapter;
import com.tdjpartner.adapter.provider.StoreListAdapter;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.ParentList;

import java.util.List;
import java.util.PropertyResourceBundle;

public class AdministrationAdapter extends MultipleItemRvAdapter<Message, BaseViewHolder> {

    public static final int LIST= 0;
    public static final int HEAD = 1;
    public AdministrationItemAdapter  administrationItemAdapter;

    public void setUserItemSize(int userItemSize,String time) {
        administrationItemAdapter.setTotal(userItemSize);
        administrationItemAdapter.setTime(time);
    }


    public AdministrationAdapter(@Nullable List<Message> data) {
        super(data);

        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        //getViewType()中可能因为某些业务逻辑，需要将某个值传递过来进行判断，返回对应的viewType
        //registerItemProvider()中可以将值传递给ItemProvider

        //If the constructor has other parameters, it needs to be assigned before calling finishInitialize() and assigned to the global variable
        // This getViewType () and registerItemProvider () method can get the value passed over
        // getViewType () may be due to some business logic, you need to pass a value to judge, return the corresponding viewType
        //RegisterItemProvider() can pass value to ItemProvider

        finishInitialize();
    }

    @Override
    protected int getViewType(Message message) {
        if (message instanceof ParentList.Headinfo){
            return HEAD;
        }else if (message instanceof ParentList.UserListBean) {
            return LIST;
        }
        return HEAD;
    }



    @Override
    public void registerItemProvider() {
        administrationItemAdapter=new AdministrationItemAdapter();
        mProviderDelegate.registerProvider(new AdministrationHeadAdapter());
        mProviderDelegate.registerProvider(administrationItemAdapter);
    }
}
