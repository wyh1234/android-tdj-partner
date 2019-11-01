package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.MultipleItemRvAdapter;
import com.tdjpartner.adapter.provider.GoodsListAdapter;
import com.tdjpartner.adapter.provider.StoreListAdapter;
import com.tdjpartner.model.AttentionData;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.StatisticalData;
import com.tdjpartner.model.StoreInfo;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class MessageListAdapter  extends MultipleItemRvAdapter<Message, BaseViewHolder> {

    public static final int GoodsList= 0;
    public static final int StoreList = 1;
    public MessageListAdapter(@Nullable List<Message> data) {
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
        //返回对应的viewType
        if (message instanceof GoodsInfo.ObjBean) {//月份title
            return GoodsList;
        }else if (message instanceof StoreInfo.ObjBean){//月份title对应数据
            return StoreList;
        }
        return 0;
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                 Message message=  getData().get(position);
                   if (message instanceof StatisticalData){
                        return ((StatisticalData) message).getType();
                    }else if (message instanceof AttentionData){
                        return 4;
                    }
                    return 1;

                }
            });
        }


    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new GoodsListAdapter());
        mProviderDelegate.registerProvider(new StoreListAdapter());
    }
}
