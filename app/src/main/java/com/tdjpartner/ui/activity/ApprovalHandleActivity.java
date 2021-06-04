package com.tdjpartner.ui.activity;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
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

    @BindView(R.id.image_url)
    ImageView image_url;
    @BindView(R.id.bzlicence_url)
    ImageView bzlicence_url;

    Dialog dialog;
    HotelAuditInfo hotelAuditInfo;
    Map<String, Object> map = new ArrayMap<>();

    @OnClick({R.id.btn_yes, R.id.btn_no, R.id.btn_back})
    public void onClick(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".onClick~~");
        System.out.println("view = " + view);

        switch (view.getId()) {
            case R.id.btn_yes:
                map.clear();
                map.put("api", "hotelAuditPass");
                map.put("markCode", hotelAuditInfo.mark_code);
                map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
                ViewModelProviders.of(this)
                        .get(NetworkViewModel.class)
                        .loadingWithNewLiveData(String.class, map)
                        .observe(this, GeneralUtils::showToastshort);
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
                    map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
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

                    bd.setText("BD：" + hotelAuditInfo.BD);
                    nick_name.setText(hotelAuditInfo.nick_name);
                    verify_customer.setText("负责人：" + hotelAuditInfo.nick_name + " " + hotelAuditInfo.phone);
                    region_name.setText("区域：" + hotelAuditInfo.region_name);
                    enterprise_msg.setText("地址：" + hotelAuditInfo.enterprise_msg);
                    delivered_time_info.setText("收货时间：" + hotelAuditInfo.delivered_time_info);

                    if (!TextUtils.isEmpty(hotelAuditInfo.image_url))
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.image_url, image_url);
                    if (!TextUtils.isEmpty(hotelAuditInfo.bzlicence_url))
                        ImageLoad.loadImageViewLoding(hotelAuditInfo.bzlicence_url, bzlicence_url);

                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.iron_approval_handle_activity;
    }
}
