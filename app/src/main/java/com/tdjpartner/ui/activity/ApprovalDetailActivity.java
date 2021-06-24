package com.tdjpartner.ui.activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.util.ArrayMap;
import android.util.Pair;
import android.view.View;
import android.view.Window;
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
    @BindView(R.id.tv_image)
    TextView tv_image;
    @BindView(R.id.tv_bzlicence)
    TextView tv_bzlicence;
    @BindView(R.id.switch_image)
    Switch switch_image;
    @BindView(R.id.switch_bzlicence)
    Switch switch_bzlicence;

    Dialog dialog, dialogImage;
    boolean isbzlicence, isDialog;

    @BindView(R.id.created_at)
    TextView created_at;
    @BindView(R.id.verify)
    TextView verify;

    HotelAuditInfo hotelAuditInfo;
    int userId, img_check_status, licence_url_check_status;
    Map<String, Object> map = new ArrayMap<>();


    @OnClick({R.id.btn_yes, R.id.btn_no, R.id.btn_back, R.id.image_url, R.id.bzlicence_url})
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
                    dialog = DialogUtils.getResourceDialog(this, R.layout.comment_dialog, this::onClick, this::onClick, this::onClick);
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
                isDialog = true;
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
                (isbzlicence? switch_bzlicence : switch_image).setChecked(true);
                approvalPic(1);
                break;
            case R.id.dialog_tv_no:
                (isbzlicence? switch_bzlicence : switch_image).setChecked(false);
                approvalPic(2);
                break;
            case R.id.tv_close:
                if (isDialog) {
                    if (dialog.isShowing()) dialog.dismiss();
                } else {
                    if (isbzlicence) {
                        switch_bzlicence.toggle();
                    } else {
                        switch_image.toggle();
                    }
                    DialogUtils.dismissDelay(dialogImage, 200L);
                }
                break;
        }

    }

    @Override
    protected void initView() {
        userId = UserUtils.getInstance().getLoginBean().getLoginUserId();
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

                    if (!TextUtils.isEmpty(hotelAuditInfo.image_url)) {
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.image_url, image_url);
                        if ((img_check_status = hotelAuditInfo.img_check_status) == 1) {
                            switch_image.setChecked(true);
                            tv_image.setText("采用");

                        }
                        switch_image.setOnCheckedChangeListener(ApprovalDetailActivity.this::onCheckedChanged);
                    } else {
                        switch_image.setEnabled(false);
                        tv_image.setText("暂无图片");
                    }
                    if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url)) {
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.bzlicence_url, bzlicence_url);
                        if ((licence_url_check_status = hotelAuditInfo.licence_url_check_status) == 1) {
                            switch_bzlicence.setChecked(true);
                            tv_bzlicence.setText("采用");
                        }
                        switch_bzlicence.setOnCheckedChangeListener(ApprovalDetailActivity.this::onCheckedChanged);
                    } else {
                        switch_bzlicence.setEnabled(false);
                        tv_bzlicence.setText("暂无图片");
                    }
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

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        System.out.println("ApprovalHandleActivity.onCheckedChanged");
        System.out.println("buttonView = " + buttonView + ", isChecked = " + isChecked);

        isbzlicence = buttonView.getId() == R.id.switch_bzlicence;
        if (dialogImage == null) {
            dialogImage = DialogUtils.getResourceDialog(this, R.layout.pic_approval_dialog, isbzlicence ? "是否采用“营业照”" : "是否采用“形象照”", null, this::onClick, this::onClick, this::onClick);
        }
        DialogUtils.setTitle(isbzlicence ? "是否采用“营业照”" : "是否采用“形象照”", dialogImage);
        dialogImage.show();
        isDialog = false;
    }

    private void approvalPic(int authStatus) {

        map.clear();
        map.put("markCode", hotelAuditInfo.mark_code);
        map.put("userId", userId);
        map.put("authStatus", authStatus);

        if (isbzlicence) {
            if (licence_url_check_status == authStatus) {
                DialogUtils.dismissDelay(dialogImage, 200L);
                return;
            }
            ViewModelProviders.of(this)
                    .get(NetworkViewModel.class)
                    .loadingWithNewLiveData(ApprovalInfo.Licence.class, map)
                    .observe(this, licence -> {
                        licence_url_check_status = licence.licenceUrlCheckStatus;
                        tv_bzlicence.setText(licence_url_check_status == 1 ? "采用" : "不采用");
                        if (dialogImage.isShowing()) dialogImage.dismiss();
                    });
        } else {
            if (img_check_status == authStatus) {
                DialogUtils.dismissDelay(dialogImage, 200L);
                return;
            }
            ViewModelProviders.of(this)
                    .get(NetworkViewModel.class)
                    .loadingWithNewLiveData(ApprovalInfo.Image.class, map)
                    .observe(this, image -> {
                        img_check_status = image.imgCheckStatus;
                        tv_image.setText(img_check_status == 1 ? "采用" : "不采用");
                        if (dialogImage.isShowing()) dialogImage.dismiss();
                    });
        }
    }
}
