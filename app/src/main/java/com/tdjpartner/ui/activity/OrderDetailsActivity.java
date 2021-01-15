package com.tdjpartner.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.adapter.OrderListDetailsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.OrderDetailsPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> {
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
    @BindView(R.id.tv_copy)
    TextView tv_copy;

    private BaseQuickAdapter baseQuickAdapter;
    private List<OrderDetail.ItemsBean> orderLists=new ArrayList<>();
    private String orderNo;
    @OnClick({R.id.btn_back,R.id.tv_copy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_copy:

                ClipboardManager  cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(orderNo); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
                GeneralUtils.showToastshort("复制成功");
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
        if (orderList!=null){
            tv_mTextViewFuKuan.setText("实付款：￥"+orderList.getActualTotalCost());
            tv_time.setText("下单时间："+orderList.getCreateTime());
            orderNo=orderList.getOrderNo();
            tv_order_no.setText("订单编号："+orderList.getOrderNo());
            tv_cash_pledge_sum.setText("+￥" + String.valueOf(orderList.getOrderForegift()) + "元");
            tv_cash_coupon_used_mon.setText("+￥" + orderList.getTotalFreight() .toString()+ "元");
            tv_cash_coupon_used_money.setText("-￥" + String.valueOf(orderList.getCouponAmount()) + "元");
            tv_goods_money.setText("￥"+orderList.getSubtotalCost().toString() + "元");
            tv_count_image.setText(orderList.getItemCount()+"");
            cart_price.setText("总计："+orderList.getSubtotalCost().toString());
            baseQuickAdapter.setNewData(orderList.getItems());
            tv.setText("要求送达时间："+orderList.getExpectDeliveredDate()+"  ");
            tv_data.setText(orderList.getExpectDeliveredEarliestTime() + "—" + orderList.getExpectDeliveredLatestTime());
        }
    }

}
