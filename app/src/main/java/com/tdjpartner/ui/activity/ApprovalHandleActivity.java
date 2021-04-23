package com.tdjpartner.ui.activity;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.utils.DialogUtils;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.viewmodel.NetworkViewModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/16.
 */
public class ApprovalHandleActivity extends AppCompatActivity {


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

    @BindView(R.id.image_url)
    ImageView image_url;
    @BindView(R.id.bzlicence_url)
    ImageView bzlicence_url;

    Dialog dialog;
    HotelAuditInfo hotelAuditInfo;
    Map<String, Object> map = new ArrayMap<>();

    @OnClick({R.id.btn_yes, R.id.btn_no})
    public void onClick(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".onClick~~");
        System.out.println("view = " + view);

        switch (view.getId()) {
            case R.id.btn_yes:
                map.clear();
                map.put("markCode", hotelAuditInfo.mark_code);
                map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
                ViewModelProviders.of(this)
                        .get(NetworkViewModel.class)
                        .loadingWithNewLiveData(String.class, map)
                        .observe(this, GeneralUtils::showToastshort);
                break;
            case R.id.btn_no:
                if (dialog == null)
                    dialog = DialogUtils.getResourceDialog(this, R.layout.common_dialog, this::onClick, this::onClick);
                dialog.show();
                break;
            case R.id.dialog_btn_yes:
                String refuse = ((EditText) dialog.findViewById(R.id.tv_refuse)).getText().toString();
                if (dialog.isShowing() && !refuse.isEmpty()) {
                    dialog.dismiss();

                    map.clear();
                    map.put("api", "hotelAuditReject");
                    map.put("markCode", hotelAuditInfo.mark_code);
                    map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
//                    map.put("passPhone", "12345678901");
                    map.put("verifyInfo", refuse);
                    ViewModelProviders.of(this)
                            .get(NetworkViewModel.class)
                            .loadingWithNewLiveData(String.class, map)
                            .observe(this, GeneralUtils::showToastshort);
                }
                break;
            case R.id.dialog_btn_no:
                if (dialog.isShowing()) dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.iron_approval_handle_activity);
        Eyes.translucentStatusBar(this, true);
        ButterKnife.bind(this);
        tv_title.setText("审核处理");

        Map<String, Object> map = new ArrayMap<>();
        map.put("customerId", getIntent().getIntExtra("customerId", -1));
        map.put("customerId", 258693);

        ViewModelProviders.of(this)
                .get(NetworkViewModel.class)
                .loadingWithNewLiveData(HotelAuditInfo.class, map)
                .observe(this, hotelAuditInfo -> {
                    System.out.println("hotelAuditInfo = " + hotelAuditInfo);

                    this.hotelAuditInfo = hotelAuditInfo;

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

                });


    }
}
