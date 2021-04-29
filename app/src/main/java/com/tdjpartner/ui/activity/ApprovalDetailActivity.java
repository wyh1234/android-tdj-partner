package com.tdjpartner.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.viewmodel.NetworkViewModel;
import com.tdjpartner.widget.view.ScrollPickerAdapter;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/16.
 */
public class ApprovalDetailActivity extends NetworkActivity {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.enterprise_code)
    TextView enterprise_code;
    @BindView(R.id.authStatus)
    TextView authStatus;
    @BindView(R.id.person_name)
    TextView person_name;
    @BindView(R.id.enterprise_msg)
    TextView enterprise_msg;
    @BindView(R.id.created_at)
    TextView created_at;
    @BindView(R.id.verify)
    TextView verify;

    @BindView(R.id.image_url)
    ImageView image_url;
    @BindView(R.id.bzlicence_url)
    ImageView bzlicence_url;

    @Override
    protected void initView() {
        tv_title.setText("审核详情");
    }

    @Override
    protected void initData() {

        Map<String, Object> map = new ArrayMap<>();
        map.put("customerId", getIntent().getLongExtra("customerId", -1));
        map.put("customerId", 258693);

        getVM().loadingWithNewLiveData(HotelAuditInfo.class, map)
                .observe(this, hotelAuditInfo -> {
                    System.out.println("hotelAuditInfo = " + hotelAuditInfo);
                    switch (hotelAuditInfo.authStatus) {
                        case 0:
                            authStatus.setText("待审核");
                            break;
                        case 1:
                            authStatus.setText("审核成功");
                            authStatus.setBackgroundResource(R.drawable.bg_green_12);
                            break;
                        case 2:
                            authStatus.setText("审核驳回");
                            authStatus.setBackgroundResource(R.drawable.bg_grey_12);
                            break;
                        default:
                            authStatus.setText("未知状态");
                    }

                    enterprise_code.setText("" + hotelAuditInfo.enterprise_code);
                    person_name.setText(hotelAuditInfo.nick_name + "：" + "" + hotelAuditInfo.phone);
                    enterprise_msg.setText("" + hotelAuditInfo.enterprise_msg);

                    ImageLoad.loadImageViewLoding(hotelAuditInfo.image_url, image_url);
                    ImageLoad.loadImageViewLoding(hotelAuditInfo.bzlicence_url, bzlicence_url);


                    created_at.setText("提交时间：" + hotelAuditInfo.created_at);
                    verify.setText("审核结果：" + hotelAuditInfo.verify_info);

                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_detail_activity;
    }
}
