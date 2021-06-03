package com.tdjpartner.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.adapter.SimpleAfterSalesAdapter;
import com.tdjpartner.adapter.SimpleAfterSalesImageAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.DeleteSalesAppByEntityId;
import com.tdjpartner.model.RefundDetail;
import com.tdjpartner.mvp.presenter.AfterSalesDetailPresenter;
import com.tdjpartner.utils.CustomerData;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.GridSpacingItemDecoration;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AfterSalesDetailActivity extends BaseActivity<AfterSalesDetailPresenter> {

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.problem_info)
    TextView problem_info;
    @BindView(R.id.tv_problem_type)
    TextView tv_problem_type;
    @BindView(R.id.lv_problem_type)
    LinearLayout lv_problem_type;
    @BindView(R.id.tv_problem_remark)
    TextView tv_problem_remark;
    @BindView(R.id.refund_no)
    TextView refund_no;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView_11)
    TextView textView_11;
    @BindView(R.id.textView_12)
    TextView textView_12;
    @BindView(R.id.textView_1)
    TextView textView1;
    @BindView(R.id.tv_cause)
    TextView tv_cause;
    @BindView(R.id.tv_explain)
    TextView tv_explain;
    @BindView(R.id.refund_cancel)
    TextView refundCancel;
    @BindView(R.id.refund_time)
    TextView refundTime;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.refund_count)
    TextView refundCount;
    @BindView(R.id.refund_money)
    TextView refundMoney;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.image_recyler)
    RecyclerView image_recyler;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> imageUrlList = new ArrayList<>();
    private RefundDetail refundDetail;
    private static final int send_msg_code = 0x101;
    private SimpleAfterSalesAdapter setAdapter;
    private SimpleAfterSalesImageAdapter simpleAfterSalesImageAdapter;
    @OnClick({R.id.btn_back,R.id.refund_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.refund_cancel:
                if (UserUtils.getInstance().getLoginBean().getGrade()==4||UserUtils.getInstance().getLoginBean().getGrade()==5){
                    GeneralUtils.showToastshort("暂无操作权限");
                    return;
                }
                if (refundDetail != null) {
                    LogUtils.e(refundDetail.getEntity_id()+","+refundDetail.getOrder_id()+","+refundDetail.getOrder_item_id());
                    mPresenter.deleteSalesAppByEntityId(refundDetail.getEntity_id(),refundDetail.getOrder_id(),refundDetail.getOrder_item_id());
                }
                break;
        }
    }
    @Override
    protected AfterSalesDetailPresenter loadPresenter() {
        return new AfterSalesDetailPresenter();
    }

    @Override
    protected void initData() {

        int itemId = getIntent().getIntExtra("itemId", -1);
        mPresenter.after_details_order(itemId);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("售后详情");
        image_recyler.setLayoutManager(new GridLayoutManager(this,3));
        image_recyler.addItemDecoration(new GridSpacingItemDecoration(3, 10,false,10));
        setAdapter = new SimpleAfterSalesAdapter(R.layout.t_imageview,imageUrlList);
        image_recyler.setAdapter(setAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        simpleAfterSalesImageAdapter = new SimpleAfterSalesImageAdapter(R.layout.item_refund_detail);
        recyclerView.setAdapter(simpleAfterSalesImageAdapter);

    }
    public void  setData(String images) {
        if (TextUtils.isEmpty(images)) return ;
        String[] str = images.split(",");
        List<String> list = new ArrayList<>();
        for (String ss : str) {
            list.add(ss);
        }
        setAdapter.setNewData(list);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_refund_detail;
    }

    public void getRefundDetail_Success(RefundDetail data) {

        textView.setText("商品编号：");
        refund_no.setText(data.getQr_code_id());
        CustomerData<Integer, String, String> problem = PublicCache.getAfterSaleProblem();
        if (problem != null) {
            String pro = problem.getValueOfKey(data.getProblem_type());
            if (!TextUtils.isEmpty(pro)) {
                tv_problem_type.setText(pro);
            }
        } else GeneralUtils.showToastshort("问题类型出错 type=" + data.getApply_type());

        if (TextUtils.isEmpty(data.getRemark())) {
            lv_problem_type.setVisibility(View.GONE);
        } else {
            lv_problem_type.setVisibility(View.VISIBLE);
            tv_problem_remark.setText(data.getRemark());
        }
        refundDetail = data;
        handler.sendEmptyMessage(send_msg_code);
//                loadingDimss();
        if (null == data.getProblem_info()){
            problem_info.setText("售后责任归属：其他");
        }else if (data.getProblem_info()==1){
            problem_info.setText("售后责任归属：物流责任");
        }else if (data.getProblem_info()==2){
            problem_info.setText("售后责任归属：客户责任");
        }else if (data.getProblem_info()==3){
            problem_info.setText("售后责任归属：销售商责任");
        }else if (data.getProblem_info()==4){
            problem_info.setText("售后责任归属：无效售后");
        }else {
            problem_info.setText("售后责任归属：其他");
        }


    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == send_msg_code) {
                onEvent();
            }
        }
    };
    public void onEvent() {

        if (refundDetail == null) return;

        String[] image = null;
        image = refundDetail.getCertificate_photos().split(",");
        for (int i = 0; i < image.length; i++) {
            if (image[i] != null && image[i].contains(".")) {
                imageUrlList.add(image[i]);
            }
        }
        //1退款,2换货
        if (refundDetail.getApply_type() == 1) {
            textView_11.setText("申请退款：");
            textView1.setText("申请退款时间：");
            textView_12.setText("退款金额：");
            tv_cause.setText("退款原因：");
            tv_explain.setText("退款说明：");
        } else if (refundDetail.getApply_type() == 2) {
            textView_11.setText("申请换货：");
            textView1.setText("申请换货时间：");
            textView_12.setText("换货金额：");
            tv_cause.setText("换货原因：");
            tv_explain.setText("换货说明：");
        } else if (refundDetail.getApply_type() == 3) {
            textView_11.setText("申请退款：");
            textView1.setText("申请售后时间：");
            textView_12.setText("退款金额：");
            tv_cause.setText("退款原因：");
            tv_explain.setText("退款说明：");
        } else if (refundDetail.getApply_type() == 4) {
            textView_11.setText("申请补货：");
            textView1.setText("申请补货时间：");
            textView_12.setText("补货金额：");
            tv_cause.setText("补货原因：");
            tv_explain.setText("补货说明：");
        }


        refundTime.setText(refundDetail.getCreate_time());
        description.setText(refundDetail.getProblem_description());
        refundCount.setText(String.valueOf(refundDetail.getAmount()));
        refundMoney.setText(String.valueOf(refundDetail.getTotal_price()));
        unit.setText(refundDetail.getAvg_unit());
        simpleAfterSalesImageAdapter.setNewData(refundDetail.getItems());


        String imageStr = "";
        for (int i = 0; i < imageUrlList.size(); i++) {
            if (i != imageUrlList.size() - 1) {
                imageStr += imageUrlList.get(i) + ",";
            } else {
                imageStr += imageUrlList.get(i);
            }
        }
     setData(imageStr);

         if ( refundDetail.getStatus() > 3){
             refundCancel.setVisibility(View.GONE);
         }else {
             refundCancel.setVisibility(View.VISIBLE);
         }


    }

    public void getDeleteSalesAppByEntityId_Success(DeleteSalesAppByEntityId data) {
        AfterSales afterSales=new AfterSales();
        afterSales.setStatus(4);
        afterSales.setOrderItemId(refundDetail.getOrder_item_id());
        GeneralUtils.showToastshort("撤销成功");
        EventBus.getDefault().post(afterSales);
        finish();
    }
}
