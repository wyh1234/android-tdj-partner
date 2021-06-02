package com.tdjpartner.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.AfterDetailData;
import com.tdjpartner.utils.DialogUtils;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static android.text.Html.FROM_HTML_MODE_LEGACY;
import static com.tdjpartner.ui.fragment.NetSupportFragment.REFUND;
import static com.tdjpartner.ui.fragment.NetSupportFragment.REPLACE;
import static com.tdjpartner.ui.fragment.NetSupportFragment.REPLENISH;

/**
 * Created by LFM on 2021/3/17.
 */
public class NetSupportDetailActivity extends NetworkActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.customer_name)
    TextView customer_name;
    @BindView(R.id.shipping_line_code)
    TextView shipping_line_code;
    @BindView(R.id.receive_user_name)
    TextView receive_user_name;
    @BindView(R.id.customer_address)
    TextView customer_address;
    @BindView(R.id.supplier_name)
    TextView supplier_name;
    @BindView(R.id.supplier_tel)
    TextView supplier_tel;
    @BindView(R.id.order_pay_time)
    TextView order_pay_time;
    @BindView(R.id.pick_finish_time)
    TextView pick_finish_time;
    @BindView(R.id.order_no)
    TextView order_no;
    @BindView(R.id.original)
    TextView original;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.discount_price)
    TextView discount_price;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.num_title)
    TextView num_title;
    @BindView(R.id.num_unit)
    TextView num_unit;
    @BindView(R.id.price_title)
    TextView price_title;
    @BindView(R.id.refund_amount)
    TextView refund_amount;
    @BindView(R.id.difficulty)
    TextView difficulty;
    @BindView(R.id.remark)
    Spinner remark;

    @BindView(R.id.et_num)
    EditText et_num;
    @BindView(R.id.et_money)
    EditText et_money;


    @BindView(R.id.problem_description)
    TextView problem_description;

    @BindView(R.id.rc_certificate_photos)
    RecyclerView rc_certificate_photos;

    RxPermissions rxPermissions;
    BaseQuickAdapter adapter;
    ArrayAdapter<String> arrayAdapter;

    String receiveUserTel, supplierTel, spinnerContent, type;
    Map<Integer, String> imageUrl;
    Dialog dialog;
    boolean isLoading = false;
    int uploadIndex;
    int entityId;
    File captureFile;
    float amountFloatExtra, originalAmountFloatExtra;
    String unitStringExtra, originalStringExtra;
    int title;
    AfterDetailData afterDetailData;

    @OnClick({R.id.tv_title, R.id.receive_user_name, R.id.customer_address, R.id.supplier_tel, R.id.button, R.id.upload_1, R.id.upload_2, R.id.upload_3, R.id.tv_remove, R.id.difficulty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                finish();
                break;
            case R.id.receive_user_name:
                GeneralUtils.action_call(getRxPermissions(), receiveUserTel, this);
                break;
            case R.id.supplier_tel:
                GeneralUtils.action_call(getRxPermissions(), supplierTel, this);
                break;
            case R.id.upload_1:
                uploadIndex = 0;
                startChooser();
                break;
            case R.id.upload_2:
                uploadIndex = 1;
                startChooser();
                break;
            case R.id.upload_3:
                uploadIndex = 2;
                startChooser();
                break;
            case R.id.tv_remove:
                view.setVisibility(View.GONE);
                View parent = (View) view.getParent();
                parent.findViewById(R.id.image).setVisibility(View.GONE);
                parent.findViewById(R.id.tv_one).setVisibility(View.VISIBLE);
                parent.findViewById(R.id.tv_two).setVisibility(View.VISIBLE);
                imageUrl.remove(view.getTag());
                break;
            case R.id.button:
                String error = "";
                Map<String, Object> map = new ArrayMap<>();
                map.put("api", "modifyAfterSalePhoto");
                map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
                map.put("entityId", entityId);

                System.out.println("imageUrl = " + imageUrl);
                if (!type.equals(REFUND) && imageUrl.isEmpty()) {
                    error = "请至少上传一张图片";
                } else {
                    map.put("images", TextUtils.join(",", imageUrl.values()));
                }

                if (!type.equals(REFUND)) {
                    if (et_money.getText().toString().isEmpty()) {
                        error = "请输入实际金额";
                    } else {
                        map.put("price", Double.parseDouble(et_money.getText().toString()));
                    }

                    if (et_num.getText().toString().isEmpty()) {
                        error = "请输入实际数量";
                    } else {
                        map.put("num", Double.parseDouble(et_num.getText().toString()));
                    }

                    String v = discount_price.getText().toString();
                    map.put("money", Double.parseDouble(v.substring(v.indexOf('：') + 1, v.indexOf('元'))));
                    if (type.equals(REPLACE)) map.put("remark", spinnerContent);
                }


                System.out.println("map = " + map);

                if (error.isEmpty()) {
                    getVM().loadingWithNewLiveData(String.class, map)
                            .observe(this, s -> {
                                GeneralUtils.showToastshort(s);
                                finish();
                            });
                } else {
                    GeneralUtils.showToastshort(error);
                }
                break;
            case R.id.difficulty:
                if (dialog == null)
                    dialog = DialogUtils.getResourceDialog(this, R.layout.common_dialog, REPLACE + "问题反映", "请输入遇到的问题", this::onClick, this::onClick);
                if (!dialog.isShowing()) dialog.show();
                break;
            case R.id.dialog_btn_yes:
                String remark = ((EditText) dialog.findViewById(R.id.et_content)).getText().toString();
                if (dialog.isShowing() && !remark.isEmpty()) {
                    dialog.dismiss();

                    if (typeToInt() == -1) {
                        GeneralUtils.showToastshort("无法处理");
                        return;
                    }
                    Map<String, Object> args = new ArrayMap<>();
                    args.put("api", "difficulty");
                    args.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
                    args.put("entityId", entityId);
                    args.put("tab", typeToInt());
                    args.put("remark", remark);

                    System.out.println("args = " + args);
                    getVM().loadingWithNewLiveData(String.class, args)
                            .observe(this, s -> {
                                GeneralUtils.showToastshort(s);
                                setResult(RESULT_OK);
                                finish();
                            });
                }
                break;
            case R.id.dialog_btn_no:
                if (dialog.isShowing()) dialog.dismiss();
                break;
            case R.id.customer_address:
                if (afterDetailData.order.lon == 0.0 || afterDetailData.order.lat == 0.0) return;
                String uri = "androidamap://route?sourceApplication=" + getString(R.string.app_name);
                uri += "&dev=1";
                uri += "&poiname=" + afterDetailData.order.customer_address;
                uri += "&dlat=" + afterDetailData.order.lat;
                uri += "&dlon=" + afterDetailData.order.lon;
                uri += "&name=1";
                uri += "&style=0";
                System.out.println("uri = " + uri);

                try {
                    GeneralUtils.startIntent(Intent.parseUri(uri, 0), this, "无法启动高德地图");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.net_support_detail_activity;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra("type");
        title = getIntent().getIntExtra("title", 0);
        amountFloatExtra = getIntent().getFloatExtra("amount", 0);
        unitStringExtra = getIntent().getStringExtra("unit");
        originalStringExtra = getIntent().getStringExtra("original");
        originalAmountFloatExtra = getIntent().getFloatExtra("original_amount", 0);
        original.setText("平台下单：" + getIntent().getStringExtra("original"));
//        money.setText("折算后单价：" + getIntent().getStringExtra("money"));
        num_title.setText("实际数量：");
        num_unit.setText(unitStringExtra);
        price_title.setText("实际金额：");
        et_num.setOnFocusChangeListener(this::onFocusChange);
        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("s = " + s + ", start = " + start + ", before = " + before + ", count = " + count);
                if (!TextUtils.isEmpty(s) && s.charAt(s.length() - 1) != '.' && Float.parseFloat(s.toString()) > amountFloatExtra * 2)
                    GeneralUtils.showToastshort("实际数量不能超过要求数量2倍，请重新输入！");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_money.setOnFocusChangeListener(this::onFocusChange);
//        et_money.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(TextUtils.isEmpty(et_num.getText()))return;
//                if (!TextUtils.isEmpty(s) && s.charAt(s.length() - 1) != '.' && Float.parseFloat(s.toString()) > originalAmountFloatExtra * Float.parseFloat(et_num.getText().toString()) * 3)
//                    GeneralUtils.showToastshort("实际金额不能超过3倍，请重新输入！");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        switch (type) {
            case REPLENISH:
                tv_title.setText(REPLENISH.substring(2, 4) + "详情");
                amount.setText(Html.fromHtml("要求补货：<font color='red'>" + GeneralUtils.trimZero(amountFloatExtra) + unitStringExtra + "</font>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                et_num.setHint("请输入实际补货数量");
                et_money.setHint("请输入实际补货金额");
                break;
            case REPLACE:
                tv_title.setText(REPLACE.substring(2, 4) + "详情");
                amount.setText("要求换货：" + amountFloatExtra + unitStringExtra);
                et_num.setHint("请输入实际换货数量");
                et_money.setHint("请输入实际换货金额");
                difficulty.setPaintFlags(difficulty.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                difficulty.setVisibility(View.VISIBLE);

                //换货证明
                findViewById(R.id.tv_title_replace).setVisibility(View.VISIBLE);
                adapter = new BaseQuickAdapter<String, BaseViewHolder>(0) {
                    @Override
                    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
                        ImageView imageView = new ImageView(NetSupportDetailActivity.this);
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        return new BaseViewHolder(imageView);
                    }

                    @Override
                    protected void convert(BaseViewHolder baseViewHolder, String s) {
                        ImageLoad.loadImageViewLoding(s, (ImageView) baseViewHolder.itemView, R.mipmap.yingyezhao_bg);
                    }
                };
                rc_certificate_photos.setVisibility(View.VISIBLE);
                rc_certificate_photos.setAdapter(adapter);
                rc_certificate_photos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

                //处理方式
                arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item);
                arrayAdapter.addAll(Arrays.asList("已取到货物", "未取到货物"));
                findViewById(R.id.ll_remark).setVisibility(View.VISIBLE);
                remark.setAdapter(arrayAdapter);
                remark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
                        spinnerContent = ((TextView) view).getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;
            case REFUND:
                tv_title.setText(REFUND.substring(2, 4) + "详情");
                findViewById(R.id.ll_info).setVisibility(View.GONE);
                findViewById(R.id.ll_uplaod).setVisibility(View.GONE);
                refund_amount.setVisibility(View.VISIBLE);
                difficulty.setVisibility(View.VISIBLE);
                refund_amount.setText(Html.fromHtml("商品退货：<font color='red'>" + GeneralUtils.trimZero(amountFloatExtra) + "</font>" + unitStringExtra, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                ((TextView) findViewById(R.id.button)).setText("确认收到退货");
                break;
        }

        if (title == 1) {
//            findViewById(R.id.ll_uplaod).setVisibility(View.GONE);
            findViewById(R.id.button).setVisibility(View.GONE);
            difficulty.setVisibility(View.GONE);
            et_num.setEnabled(false);
            et_money.setEnabled(false);
            remark.setEnabled(false);
            discount_price.setText("折算后单价：");
        } else {
            pick_finish_time.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {
        entityId = getIntent().getIntExtra("entityId", -1);
        if (entityId == -1) return;

        imageUrl = new ArrayMap<>();

        Map<String, Object> map = new ArrayMap<>();
        map.put("entityId", entityId);

        showLoading();
        getVM().loadingWithNewLiveData(AfterDetailData.class, map)
                .observe(this, afterDetailData -> {
                    dismissLoading();
                    this.afterDetailData = afterDetailData;
                    receiveUserTel = afterDetailData.order.receive_user_tel;
                    supplierTel = afterDetailData.order.supplier_tel;


                    customer_name.setText("门店：" + afterDetailData.order.customer_name);
                    shipping_line_code.setText("编号：" + afterDetailData.order.shipping_line_code);
                    receive_user_name.setText("收货人：" + afterDetailData.order.receive_user_name + "（" + afterDetailData.order.receive_user_tel + "）");
                    customer_address.setText("地址：" + afterDetailData.order.customer_address);
                    supplier_name.setText("供应商名称：" + afterDetailData.order.store_name);
                    supplier_tel.setText("供应商电话：" + afterDetailData.order.supplier_tel);
                    order_pay_time.setText("下单时间：" + afterDetailData.order.order_pay_time);
                    order_no.setText("商品单号：" + afterDetailData.order.order_no);

                    String level3 = TextUtils.isEmpty(afterDetailData.order.level_3_unit) ? "" : "*" + GeneralUtils.trimZero(afterDetailData.order.level_3_value) + afterDetailData.order.level_3_unit;
                    String level2 = TextUtils.isEmpty(afterDetailData.order.level_2_unit) ? "" : "（" + GeneralUtils.trimZero(afterDetailData.order.level_2_value) + afterDetailData.order.level_2_unit + level3 + "）";
                    String value = afterDetailData.order.price + "元/" + afterDetailData.order.unit + (afterDetailData.order.level_type == 3 ? "" : level2);
                    String styledText = "<font color='grey'>" + value + "</font>" + "<font color='red'>×" + GeneralUtils.trimZero(afterDetailData.order.original_amount) + "</font>";
                    unit.setText(Html.fromHtml(styledText, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);


                    value = GeneralUtils.trimZero(afterDetailData.order.original_amount) + afterDetailData.order.unit + "/共" + GeneralUtils.trimZero(afterDetailData.order.price * afterDetailData.order.original_amount) + "元";
                    price.setText(value);


                    ((TextView) findViewById(R.id.product_criteria)).setText(afterDetailData.order.product_criteria.equals("1") ? "通" : "精");
                    ((TextView) findViewById(R.id.name)).setText(afterDetailData.order.name + (afterDetailData.order.nick_name.isEmpty() ? "" : "（" + afterDetailData.order.nick_name + "）"));
                    ((TextView) findViewById(R.id.store_name)).setText(afterDetailData.order.store_name);
                    ImageLoad.loadRoundImage(afterDetailData.order.product_img, 25, findViewById(R.id.product_img), R.mipmap.baifangjiudain_bg);

                    switch (type) {
                        case REPLENISH:
                            value = REPLENISH.substring(2, 4);
                            findViewById(R.id.type).setVisibility(View.GONE);
                            break;
                        case REPLACE:
                            adapter.setNewData(Arrays.asList(afterDetailData.order.certificate_photos.split(",")));
                            value = REPLACE.substring(2, 4);
                            findViewById(R.id.type).setVisibility(View.GONE);
                            break;
                        case REFUND:
                            value = REFUND.substring(2, 4);
                            ((TextView) findViewById(R.id.type)).setText("退货");
                            break;
                    }
                    problem_description.setText(value + "原因：" + afterDetailData.order.problem_description);
                    pick_finish_time.setText(value + "时间：" + afterDetailData.order.pick_finish_time);

                    if (title == 1) {
                        if (afterDetailData.afterSale == null || TextUtils.isEmpty(afterDetailData.afterSale.images)) {
                            ((TextView) findViewById(R.id.tv_uplaod_images)).setText("未上传任何价格凭证！");
                            findViewById(R.id.ll_uplaod_images).setVisibility(View.GONE);
                            return;
                        }

                        et_money.setHint(afterDetailData.afterSale.purchasePrice);
                        et_num.setHint(afterDetailData.afterSale.purchaseNum);
                        num_unit.setText(unitStringExtra);
                        updateMoney(afterDetailData.afterSale.purchaseNum, afterDetailData.afterSale.purchasePrice);


                        String[] urls = afterDetailData.afterSale.images.split(",");
//                        String[] urls = new String[]{"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2105282256388484a2d1.png", "http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2105282256388484a2d1.png", "http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2105282256388484a2d1.png"};
                        LinearLayout linearLayout = findViewById(R.id.ll_uplaod_images);
                        linearLayout.removeAllViews();
                        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
                        if (urls.length == 3) layoutParams.weight = 1;

                        for (String url : urls) {
                            ImageView imageView = new ImageView(this);
                            imageView.setLayoutParams(layoutParams);
                            imageView.setPadding(8, 8, 8, 8);
                            linearLayout.addView(imageView);
                            ImageLoad.loadRoundImage(url, 25, imageView, R.mipmap.baifangjiudain_bg);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("~~" + getClass().getSimpleName() + ".onActivityResult~~");
        System.out.println("requestCode = " + requestCode + ", resultCode = " + resultCode + ", data = " + data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            String file;
            if (data == null) {
                file = captureFile.toString().substring(captureFile.toString().lastIndexOf("/") + 1).replace("tmp", "png");
                System.out.println("file = " + file);
                getVM().loadingWithNewLiveData(String.class, file, BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmap(captureFile.toString()), true))
                        .observe(this, url -> {
                            ImageLoad.loadRoundImageWithListen(this, url, 12, updateUploadImage(),
                                    new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            dismissLoading();
                                            isLoading = false;
                                            GeneralUtils.showToastshort("图片上传失败！");
                                            LogUtils.e("upload failure, file is " + url);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            dismissLoading();
                                            isLoading = false;
                                            imageUrl.put(uploadIndex, url);
                                            getParent(uploadIndex).findViewById(R.id.tv_remove).setVisibility(View.VISIBLE);
                                            return false;
                                        }
                                    });
                        });
            } else {
                file = Uri.decode(data.getData().getEncodedPath());
                System.out.println("file = " + file);
                file = file.substring(file.lastIndexOf("/") + 1);
                System.out.println("file = " + file);
                if (!file.endsWith(".jpg") && !file.endsWith(".gif") && !file.endsWith(".png")) {
                    GeneralUtils.showToastshort("文件格式不正确");
                    return;
                }

                try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(data.getData(), "r")) {
                    showLoading("图片上传中...");
                    getVM().loadingWithNewLiveData(String.class, file.substring(0, file.length() - 3) + "png", BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmapFromFileDescriptor(pfd.getFileDescriptor()), true))
                            .observe(this, url -> {
                                ImageLoad.loadRoundImageWithListen(this, url, 12, updateUploadImage(),
                                        new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                dismissLoading();
                                                isLoading = false;
                                                GeneralUtils.showToastshort("图片上传失败！");
                                                LogUtils.e("upload failure, file is " + url);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                dismissLoading();
                                                isLoading = false;
                                                imageUrl.put(uploadIndex, url);
                                                getParent(uploadIndex).findViewById(R.id.tv_remove).setVisibility(View.VISIBLE);
                                                return false;
                                            }
                                        });
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void startChooser() {
        if (isLoading) {
            GeneralUtils.showToastshort("正在上传，请稍等...");
            return;
        }
        if (rxPermissions == null) rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        if (b) {
                            captureFile = GeneralUtils.startCamera(NetSupportDetailActivity.this);
                        } else {
                            GeneralUtils.showToastshort("摄像头启动失败，请从相册中选择图片！");
                        }

                    }
                });
    }

    private ImageView updateUploadImage() {
        ViewGroup viewGroup = getParent(uploadIndex);
        viewGroup.findViewById(R.id.tv_one).setVisibility(View.GONE);
        viewGroup.findViewById(R.id.tv_two).setVisibility(View.GONE);
        viewGroup.findViewById(R.id.tv_remove).setTag(Integer.valueOf(uploadIndex));
        viewGroup.findViewById(R.id.tv_remove).setOnClickListener(this::onClick);
        ImageView uploadImage = viewGroup.findViewById(R.id.image);
        uploadImage.setVisibility(View.VISIBLE);
        return uploadImage;
    }

    private ViewGroup getParent(int uploadIndex) {
        switch (uploadIndex) {
            case 0:
                return findViewById(R.id.upload_1);
            case 1:
                return findViewById(R.id.upload_2);
            case 2:
                return findViewById(R.id.upload_3);
            default:
                return null;
        }
    }


    private int typeToInt() {
        switch (type) {
            case REPLENISH:
                return 0;
            case REPLACE:
                return 1;
            case REFUND:
                return 2;
            default:
                return -1;
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        System.out.println("v = " + v + ", hasFocus = " + hasFocus);
        if (!hasFocus) check(v);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            System.out.println("view = " + view);
            if (view != null) check(view);
            if (GeneralUtils.isHideInput(view, ev)) {
                GeneralUtils.hideSoftInput(view.getWindowToken(), this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void check(View v) {
        switch (v.getId()) {
            case R.id.et_num:
                if (TextUtils.isEmpty(et_num.getText())) return;
                float num = Float.parseFloat(et_num.getText().toString());
                System.out.println("num = " + num);
                if (num > amountFloatExtra * 2) {
                    et_num.setText("");
                    GeneralUtils.showToastshort("实际数量不能超过要求数量的2倍，请重新输入！");
                }
                break;

            case R.id.et_money:
//                if (TextUtils.isEmpty(et_money.getText())) return;
//                float price = Float.parseFloat(et_money.getText().toString());
//                System.out.println("price = " + price);
//                if (!TextUtils.isEmpty(et_num.getText().toString()) && price > originalAmountFloatExtra * Float.parseFloat(et_num.getText().toString()) * 3) {
//                    et_money.setText("");
//                    GeneralUtils.showToastshort("实际金额不能超过3倍，请重新输入！");
//                }
                break;
        }

        updateMoney(et_num.getText().toString(), et_money.getText().toString());
    }

    private void updateMoney(String num, String money) {
        if (!TextUtils.isEmpty(num) && !TextUtils.isEmpty(money)) {
            float n = Float.parseFloat(num);
            float m = Float.parseFloat(money);
            discount_price.setText("折算后单价：" + GeneralUtils.trimZero((float) (Math.round(m / n * 100) / 100.0)) + "元/" + unitStringExtra);
        }
    }
}
