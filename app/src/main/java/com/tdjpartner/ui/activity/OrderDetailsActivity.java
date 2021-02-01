package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.adapter.OrderListDetailsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.OrderDetailsPresenter;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements BaseQuickAdapter.OnItemChildClickListener{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_mTextViewFuKuan)
    TextView tv_mTextViewFuKuan;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.tv_cash_pledge_sum)
    TextView tv_cash_pledge_sum;
    @BindView(R.id.tv_cash_coupon_used_mon)
    TextView tv_cash_coupon_used_mon;
    @BindView(R.id.tv_cash_coupon_used_money)
    TextView tv_cash_coupon_used_money;
    @BindView(R.id.tv_goods_money)
    TextView tv_goods_money;
    @BindView(R.id.tv_count_image)
    TextView tv_count_image;
    @BindView(R.id.cart_price)
    TextView cart_price;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_data)
    TextView tv_data;
    @BindView(R.id.tv_div)
    TextView tv_div;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_store)
    TextView tv_store;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    private OrderListDetailsAdapter baseQuickAdapter;
    private List<OrderDetail.ItemsBean> orderLists=new ArrayList<>();
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected OrderDetailsPresenter loadPresenter() {
        return new OrderDetailsPresenter();
    }

    @Override
    protected void initData() {
        Eyes.translucentStatusBar(this,true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        baseQuickAdapter=new OrderListDetailsAdapter(R.layout.order_details_item,orderLists);
        baseQuickAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(baseQuickAdapter);

        Map<String,Object> map=new HashMap<>();
        map.put("orderNO",getIntent().getStringExtra("orderNO"));
//        map.put("orderNO","65922838302066278407");
        map.put("site", UserUtils.getInstance().getLoginBean().getSite());
        mPresenter.findOne(map);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.orderlist_details_layout;
    }

    public void findOne_Success(OrderDetail orderList) {
        if (orderLists.size()>0){
            orderLists.clear();
        }
        if (orderList!=null){
            tv_mTextViewFuKuan.setText("实付款：￥"+orderList.getActualTotalCost());
            tv_time.setText("下单时间："+orderList.getCreateTime());
            tv_order_no.setText("订单编号："+orderList.getOrderNo());
            tv_cash_pledge_sum.setText("+￥" + String.valueOf(orderList.getOrderForegift()) + "元");
            tv_cash_coupon_used_mon.setText("+￥" + orderList.getTotalFreight() .toString()+ "元");
            tv_cash_coupon_used_money.setText("-￥" + String.valueOf(orderList.getCouponAmount()) + "元");
            tv_goods_money.setText("￥"+orderList.getSubtotalCost().toString() + "元");
            tv_count_image.setText(orderList.getItemCount()+"");
            cart_price.setText("总计："+orderList.getSubtotalCost().toString());
            baseQuickAdapter.setNewData(orderList.getItems());
            baseQuickAdapter.setExpectDeliveredDate(orderList.getExpectDeliveredDate());
            tv.setText("要求送达时间："+orderList.getExpectDeliveredDate()+"  ");
            tv_data.setText(orderList.getExpectDeliveredEarliestTime() + "—" + orderList.getExpectDeliveredLatestTime());
            tv_div.setText("(司机:"+orderList.getItems().get(0).getDriverName()+")");
            tv2.setText(orderList.getReceiveAddr().getHotelName());
            tv_store.setText(orderList.getReceiveAddr().getName());
            tv_phone.setText(orderList.getItems().get(0).getReceiveAddr().getTelephone());

        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()==R.id.tv_after){
            if (orderLists.get(i).getStatus() == 6||orderLists.get(i).getStatus() == 9){
                Intent intent=new Intent(this,AfterSalesDetailActivity.class);
                intent.putExtra("itemId", orderLists.get(i).getItemId());
                intent.putExtra("buyId",getIntent().getStringExtra("buyId"));
                startActivity(intent);
            }else {
                EventBus.getDefault().postSticky(baseQuickAdapter.getData().get(i));
                Intent intent=new Intent(this,AfterSalesCreateActivity.class);
                intent.putExtra("buyId",getIntent().getStringExtra("buyId"));
                startActivity(intent);
            }

        }

    }

    @Subscribe
    public void onEvent(AfterSales event) {
        if (event != null) {
            for (int i = 0; i < orderLists.size(); i++) {
                OrderDetail.ItemsBean bean =orderLists.get(i);
                if (bean == null) continue;
                if (bean.getItemId() == event.getOrderItemId()) {
                    bean.setStatus(6);
                    baseQuickAdapter.notifyItemChanged(i);
                    break;
                }
            }

        }
    }
}
