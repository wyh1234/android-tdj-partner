package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.AdterSalesOrderAdapter;
import com.tdjpartner.adapter.OrderListAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.PageByCSIdList;
import com.tdjpartner.model.RefundDetail;
import com.tdjpartner.mvp.presenter.AdterSalesOrderListPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdterSalesOrderListActivity extends BaseActivity<AdterSalesOrderListPresenter>implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_date_start)
    TextView tv_date_start;
    @BindView(R.id.tv_date_end)
    TextView tv_date_end;
    private Calendar startDate,endDate,selectedDates;
    private TimePickerView pvTime;

    public int pageNo = 1;//翻页计数器
    private BaseQuickAdapter baseQuickAdapter;
    private List<RefundDetail> refundDetails=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @OnClick({R.id.btn_back,R.id.tv_date_start,R.id.tv_date_end})
    public void onClick(View view)  {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_date_start:

                try {
                    selectedDates= GeneralUtils.selectedDates(tv_date_start.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                setTime(tv_date_start,1);
                break;
            case R.id.tv_date_end:
                try {
                    selectedDates=GeneralUtils.selectedDates(tv_date_end.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setTime(tv_date_end,2);
                break;
        }
    }
    @Override
    protected AdterSalesOrderListPresenter loadPresenter() {
        return new AdterSalesOrderListPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        Eyes.translucentStatusBar(this,true);
        tv_title.setText("售后列表");
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        CustomLinearLayout layout = new CustomLinearLayout(getContext(),
                LinearLayoutManager.VERTICAL, false);
        layout.setScrollEnabled(true);
        recyclerView_list.setLayoutManager(layout);
        baseQuickAdapter=new AdterSalesOrderAdapter(R.layout.aftersales_orderlist_item,refundDetails);
        recyclerView_list.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnLoadMoreListener(this,recyclerView_list);
        baseQuickAdapter.setOnItemChildClickListener(this);

        refreshLayout.setRefreshing(true);
        onRefresh();
        startDate=Calendar.getInstance();
        tv_date_end.setText(startDate.get(Calendar.YEAR)+"-"+startDate.get(Calendar.MONTH)+1+"-"+startDate.get(Calendar.DAY_OF_MONTH));
        tv_date_start.setText(startDate.get(Calendar.YEAR)+"-"+startDate.get(Calendar.MONTH)+1+"-"+(startDate.get(Calendar.DAY_OF_MONTH)-7));

        startDate.set(startDate.get(Calendar.YEAR),  (startDate.get(Calendar.MONTH)-1),startDate.get(Calendar.DAY_OF_MONTH));
        endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR),  (endDate.get(Calendar.MONTH)),endDate.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.orderlist_layout;
    }

    public void pageByCSIdList_Success(PageByCSIdList pageByCSIdList) {
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(pageByCSIdList.getData().getItems())) {
                refundDetails.clear();
            }
        }
        stop();
        if (ListUtils.isEmpty(refundDetails)) {
            if (ListUtils.isEmpty(pageByCSIdList.getData().getItems())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(pageByCSIdList.getData().getItems())) {
            //已经获取数据
            if (pageNo!=1){
                baseQuickAdapter.loadMoreEnd();
            }
            return;
        }
        refundDetails.addAll(pageByCSIdList.getData().getItems());
        baseQuickAdapter.setNewData(refundDetails);
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);

    }
    public void setTime(TextView tv,int type){
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                try {
                    if (type==1){

                        if (!GeneralUtils.selectedDate(GeneralUtils.getTimeFilter(date),tv_date_end.getText().toString())){
                            GeneralUtils.showToastshort("开始时间要小于结束时间，请重新选择");
                            return;
                        }

                    }else {
                        if (!GeneralUtils.selectedDate(tv_date_start.getText().toString(),GeneralUtils.getTimeFilter(date))){
                            GeneralUtils.showToastshort("结束时间要大于开始时间，请重新选择");
                            return;
                        }
                    }

                    tv.setText(GeneralUtils.getTimeFilter(date));
                    refreshLayout.setRefreshing(true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(true)
                .setLineSpacingMultiplier(1.8f)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDates)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
        pvTime.show();


    }
    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }
    protected  void getData(int pn){
//        map.put("createAtStart",tv_date_start.getText().toString().trim());
//        map.put("createAtEnd",tv_date_end.getText().toString().trim());
        mPresenter.findPageByCSIdList(pn,Integer.parseInt(getIntent().getStringExtra("buyId")),tv_date_start.getText().toString().trim(),tv_date_end.getText().toString().trim());

    }
    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,AfterSalesDetailActivity.class);
        intent.putExtra("itemId",-1);
        startActivity(intent);

    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.e(pageNo);
        refreshLayout.setRefreshing(false);
        getData(++pageNo);
    }
    @Override
    public void onPause() {
        super.onPause();
        stop();
    }
    public void stop() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);

        }
        if (baseQuickAdapter.isLoadMoreEnable()){
            baseQuickAdapter.loadMoreComplete();
        }
    }


    public void pageByCSIdList_Failed() {
        stop();
        if (ListUtils.isEmpty(refundDetails)) {
            mStateView.showEmpty();//显示重试的布局
        }
    }
    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }
}
