package com.tdjpartner.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.util.ArrayMap;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.utils.glide.ImageLoad;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by LFM on 2021/3/16.
 */
public class ApprovalDetailActivity extends NetworkActivity {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.authStatus)
    TextView authStatus;
    @BindView(R.id.bd)
    TextView bd;
    @BindView(R.id.nick_name)
    TextView nick_name;
    @BindView(R.id.verify_customer)
    TextView verify_customer;
    @BindView(R.id.region_name)
    TextView region_name;
    @BindView(R.id.enterprise_msg)
    TextView enterprise_msg;
    @BindView(R.id.delivered_time_info)
    TextView delivered_time_info;

    @BindView(R.id.image_url)
    ImageView image_url;
    @BindView(R.id.bzlicence_url)
    ImageView bzlicence_url;

    @BindView(R.id.created_at)
    TextView created_at;
    @BindView(R.id.verify)
    TextView verify;

    HotelAuditInfo hotelAuditInfo;

    @OnClick({R.id.btn_back, R.id.image_url, R.id.bzlicence_url})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.image_url:
                if (!TextUtils.isEmpty(hotelAuditInfo.image_url))
                    startActivity(hotelAuditInfo.image_url, view);
                break;
            case R.id.bzlicence_url:
                if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url))
                    startActivity(hotelAuditInfo.bzlicence_url, view);
                break;
        }

    }

    @Override
    protected void initView() {
        tv_title.setText("审核详情");
    }

    @Override
    protected void initData() {

        Map<String, Object> map = new ArrayMap<>();
        map.put("customerId", getIntent().getIntExtra("customerId", -1));

        getVM().loadingWithNewLiveData(HotelAuditInfo.class, map)
                .observe(this, hotelAuditInfo -> {
                    System.out.println("hotelAuditInfo = " + hotelAuditInfo);
                    this.hotelAuditInfo = hotelAuditInfo;

                    String status = "";
                    switch (hotelAuditInfo.authStatus) {
                        case 0:
                            authStatus.setText("待审核");
                            break;
                        case 1:
                            authStatus.setText("审核成功");
                            authStatus.setBackgroundResource(R.drawable.bg_green_12);
                            status = "通过";
                            break;
                        case 2:
                            authStatus.setText("审核驳回");
                            authStatus.setBackgroundResource(R.drawable.bg_grey_12);
                            status = "驳回";
                            break;
                        default:
                            authStatus.setText("未知状态");
                    }

                    bd.setText("创客：" + hotelAuditInfo.BD);
                    nick_name.setText(hotelAuditInfo.nick_name);
                    verify_customer.setText("负责人：" + hotelAuditInfo.nick_name + " " + hotelAuditInfo.phone);
                    region_name.setText("区域：" + hotelAuditInfo.region_name);
                    enterprise_msg.setText("地址：" + hotelAuditInfo.enterprise_msg);
                    delivered_time_info.setText("收货时间：" + hotelAuditInfo.delivered_time_info);

                    if (!TextUtils.isEmpty(hotelAuditInfo.image_url))
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.image_url, image_url);
                    if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url)) ImageLoad.loadImageViewLoding(hotelAuditInfo.bzlicence_url, bzlicence_url);

                    created_at.setText("提交时间：" + hotelAuditInfo.created_at);

                    String html = hotelAuditInfo.verify_customer + "<br/>";
                    html += status + "时间：" + hotelAuditInfo.verify_time + "<br/>";
                    html += "备注：" + hotelAuditInfo.verify_info;
                    verify.setText(Html.fromHtml(html, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_detail_activity;
    }

    private void startActivity(String path, View view) {
        Intent intent = new Intent(this, FullPictureActivity.class);
        intent.putExtra("url", path);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "share");//单共享对象
        startActivity(intent, options.toBundle());
    }
}
