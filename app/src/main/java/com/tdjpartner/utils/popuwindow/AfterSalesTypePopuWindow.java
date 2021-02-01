package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AfterSalesTypeAdapter;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.utils.CustomerData;
import com.tdjpartner.widget.CustomLinearLayout;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class AfterSalesTypePopuWindow  extends BasePopupWindow implements BaseQuickAdapter.OnItemClickListener {
    private View popupView;
    private RecyclerView rv_recyclerView;
    private AfterSalesTypeAdapter afterSalesTypeAdapter;
    private Button bt_cancel;
    private AfterSalesTypePopuWindowListener listener;

    public void setAfterSalesTypListener(AfterSalesTypePopuWindowListener listener) {
        this.listener = listener;
    }
    public interface AfterSalesTypePopuWindowListener {
        void onOk(AfterSalesType afterSalesType);
    }
    public AfterSalesTypePopuWindow(Context context) {
        super(context);
        rv_recyclerView=popupView.findViewById(R.id.rv_recyclerView);
        bt_cancel=popupView.findViewById(R.id.bt_cancel);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

        rv_recyclerView.setLayoutManager(layout);
        afterSalesTypeAdapter=new AfterSalesTypeAdapter(R.layout.after_sales_popu_item_layout,getList(PublicCache.getAfterSaleType()));
        rv_recyclerView.setAdapter(afterSalesTypeAdapter);
        afterSalesTypeAdapter.setOnItemClickListener(this);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = createPopupById(R.layout.after_sales_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
    private List<AfterSalesType> getList(CustomerData<Integer, String, String> customerData) {
        List<AfterSalesType> list = new ArrayList<>();
        for (int i = 0; i < customerData.size(); i++) {
            AfterSalesType afterSalesType = new AfterSalesType();
            afterSalesType.setTypeName(customerData.getValueAtIndex(i));
            afterSalesType.setType(customerData.getKeyAtIndex(i));
            afterSalesType.setFlag(false);
            list.add(afterSalesType);
        }
        return list;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        for (AfterSalesType afterSalesType:(List<AfterSalesType>)baseQuickAdapter.getData()){
            afterSalesType.setFlag(false);
        }
        ((AfterSalesType) baseQuickAdapter.getData().get(i)).setFlag(true);
        afterSalesTypeAdapter.notifyDataSetChanged();
            listener.onOk((AfterSalesType) baseQuickAdapter.getData().get(i));
    }
}
