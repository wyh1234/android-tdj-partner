package com.tdjpartner.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.MultipleItemRvAdapter;
import com.tdjpartner.adapter.provider.TitleDatarovider;
import com.tdjpartner.model.AttentionData;
import com.tdjpartner.model.CarouselData;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.RankingData;
import com.tdjpartner.model.StatisticalData;
import com.tdjpartner.model.TitleData;
import com.tdjpartner.model.TodayData;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class MessageListAdapter  extends MultipleItemRvAdapter<Message, BaseViewHolder> {

    public static final int TYPE_TITLE= 0;
    public static final int TYPE_DATA_ONE = 1;
    public static final int TYPE_DATA_TWO = 2;
    public static final int TYPE_DATA_THREE = 3;
    public static final int TYPE_DATA_FIVE= 4;
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
        if (message instanceof TitleData) {//月份title
            return TYPE_TITLE;
        }else if (message instanceof StatisticalData){//月份title对应数据
            return TYPE_DATA_ONE;
        } else if (message instanceof AttentionData){//关注
            return TYPE_DATA_TWO;
        } else if (message instanceof RankingData){//排行
            return TYPE_DATA_THREE;
        }else if (message instanceof CarouselData){//轮播
            return TYPE_DATA_FIVE;
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
                    }else if (message instanceof RankingData){
                        return 3;
                    }
                    return 1;

                }
            });
        }


    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new TitleDatarovider());
    }
}
