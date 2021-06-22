package com.tdjpartner.ui.activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.ApprovalInfo;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.utils.DialogUtils;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.viewmodel.NetworkViewModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LFM on 2021/3/16.
 */
public class ApprovalHandleActivity extends NetworkActivity {


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
    @BindView(R.id.tv_image)
    TextView tv_image;
    @BindView(R.id.tv_bzlicence)
    TextView tv_bzlicence;
    @BindView(R.id.switch_image)
    Switch switch_image;
    @BindView(R.id.switch_bzlicence)
    Switch switch_bzlicence;
    @BindView(R.id.image_url)
    ImageView image_url;
    @BindView(R.id.bzlicence_url)
    ImageView bzlicence_url;

    Dialog dialog, dialogImage;
    boolean isbzlicence;
    HotelAuditInfo hotelAuditInfo;
    Map<String, Object> map = new ArrayMap<>();

    int userId;

    @OnClick({R.id.btn_yes, R.id.btn_no, R.id.btn_back, R.id.image_url, R.id.bzlicence_url})
    public void onClick(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".onClick~~");
        System.out.println("view = " + view);

        switch (view.getId()) {
            case R.id.image_url:
                if (!TextUtils.isEmpty(hotelAuditInfo.image_url))
                    startActivity(hotelAuditInfo.image_url, view);
                break;
            case R.id.bzlicence_url:
                if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url))
                    startActivity(hotelAuditInfo.bzlicence_url, view);
                break;
            case R.id.btn_yes:
                map.clear();
                map.put("api", "hotelAuditPass");
                map.put("markCode", hotelAuditInfo.mark_code);
                map.put("userId", userId);
                ViewModelProviders.of(this)
                        .get(NetworkViewModel.class)
                        .loadingWithNewLiveData(String.class, map)
                        .observe(this, s -> {
                            GeneralUtils.showToastshort("操作成功！");
                            finish();
                        });
                break;
            case R.id.btn_no:
                if (dialog == null) {
                    dialog = DialogUtils.getResourceDialog(this, R.layout.common_dialog, this::onClick, this::onClick);
                    EditText editText = dialog.findViewById(R.id.et_content);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            int n = 30;
                            if (s.length() > n) {
                                editText.setText(s.subSequence(0, n));
                                editText.setSelection(editText.getText().length());
                                GeneralUtils.showToastshort("字数不能超过30！");
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

                dialog.show();
                break;
            case R.id.dialog_btn_yes:
                String refuse = ((EditText) dialog.findViewById(R.id.et_content)).getText().toString();
                if (dialog.isShowing() && !refuse.isEmpty()) {
                    dialog.dismiss();

                    map.clear();
                    map.put("api", "hotelAuditReject");
                    map.put("markCode", hotelAuditInfo.mark_code);
                    map.put("userId", userId);
                    map.put("verifyInfo", refuse);
                    ViewModelProviders.of(this)
                            .get(NetworkViewModel.class)
                            .loadingWithNewLiveData(String.class, map)
                            .observe(this, s -> {
                                GeneralUtils.showToastshort("操作成功！");
                                finish();
                            });
                }
                break;
            case R.id.dialog_btn_no:
                if (dialog.isShowing()) dialog.dismiss();
                switch_image.setChecked(false);
                break;
            case R.id.dialog_tv_yes:
                switch_image.setChecked(true);
                if(hotelAuditInfo.img_check_status == 2)approvalPic(1);
                if (dialogImage.isShowing()) dialogImage.dismiss();
                break;
            case R.id.dialog_tv_no:
                switch_image.setChecked(false);
                if(hotelAuditInfo.img_check_status == 1)approvalPic(2);
                if (dialogImage.isShowing()) dialogImage.dismiss();
                break;

            case R.id.btn_back:
                finish();
                break;
        }
    }


    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        userId = UserUtils.getInstance().getLoginBean().getLoginUserId();
        tv_title.setText("审核处理");

        Map<String, Object> map = new ArrayMap<>();
        map.put("customerId", getIntent().getIntExtra("customerId", -1));

        getVM().loadingWithNewLiveData(HotelAuditInfo.class, map)
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

                    bd.setText("创客：" + hotelAuditInfo.BD);
                    nick_name.setText(hotelAuditInfo.nick_name);
                    verify_customer.setText("负责人：" + hotelAuditInfo.nick_name + " " + hotelAuditInfo.phone);
                    region_name.setText("区域：" + hotelAuditInfo.region_name);
                    enterprise_msg.setText("地址：" + hotelAuditInfo.enterprise_msg);
                    delivered_time_info.setText("收货时间：" + hotelAuditInfo.delivered_time_info);

                    if (!TextUtils.isEmpty(hotelAuditInfo.image_url)) {
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.image_url, image_url);
                        if (hotelAuditInfo.img_check_status == 1) {
                            switch_image.setChecked(true);
                            tv_image.setText("采用");

                        }
                        switch_image.setOnCheckedChangeListener(ApprovalHandleActivity.this::onCheckedChanged);
                    } else {
                        switch_image.setEnabled(false);
                        tv_image.setText("暂无图片");
                    }
                    if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url)) {
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.bzlicence_url, bzlicence_url);
                        if (hotelAuditInfo.licence_url_check_status == 1) {
                            switch_bzlicence.setChecked(true);
                            tv_bzlicence.setText("采用");
                        }
                        switch_bzlicence.setOnCheckedChangeListener(ApprovalHandleActivity.this::onCheckedChanged);
                    } else {
                        switch_bzlicence.setEnabled(false);
                        tv_bzlicence.setText("暂无图片");
                    }

                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_handle_activity;
    }

    private void startActivity(String path, View view) {
        Intent intent = new Intent(this, FullPictureActivity.class);
        intent.putExtra("url", path);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "share");//单共享对象
        startActivity(intent, options.toBundle());
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        System.out.println("ApprovalHandleActivity.onCheckedChanged");
        System.out.println("buttonView = " + buttonView + ", isChecked = " + isChecked);

        isbzlicence = buttonView.getId() == R.id.switch_bzlicence;
        if (dialogImage == null) {
            dialogImage = DialogUtils.getResourceDialog(this, R.layout.pic_approval_dialog, this::onClick, this::onClick);
        }
        dialogImage.show();
    }

    private void approvalPic(int authStatus) {

        map.clear();
        map.put("markCode", hotelAuditInfo.mark_code);
        map.put("userId", userId);
        map.put("authStatus", authStatus);

        if (isbzlicence) {
            ViewModelProviders.of(this)
                    .get(NetworkViewModel.class)
                    .loadingWithNewLiveData(ApprovalInfo.Licence.class, map)
                    .observe(this, s -> {
                        GeneralUtils.showToastshort("操作成功！");
                    });
        } else {
            ViewModelProviders.of(this)
                    .get(NetworkViewModel.class)
                    .loadingWithNewLiveData(ApprovalInfo.Image.class, map)
                    .observe(this, s -> {
                        GeneralUtils.showToastshort("操作成功！");
                    });
        }


    }
}
