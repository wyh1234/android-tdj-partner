package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.AfterDetailData;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;
import com.tdjpartner.utils.glide.GlideApp;
import com.tdjpartner.utils.glide.ImageLoad;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tdjpartner.ui.fragment.NetSupportFragment.REFUND;
import static com.tdjpartner.ui.fragment.NetSupportFragment.REPLACE;
import static com.tdjpartner.ui.fragment.NetSupportFragment.REPLENISH;

/**
 * Created by LFM on 2021/3/17.
 */
public class NetSupportDetailActivity extends NetworkActivity {

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
    @BindView(R.id.order_no)
    TextView order_no;
    @BindView(R.id.create_time)
    TextView create_time;
    @BindView(R.id.original)
    TextView original;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.num_title)
    TextView num_title;
    @BindView(R.id.price_title)
    TextView price_title;
    @BindView(R.id.remark)
    Spinner remark;

    @BindView(R.id.num)
    EditText num;
    @BindView(R.id.price)
    EditText price;


    @BindView(R.id.problem_description)
    TextView problem_description;

    @BindView(R.id.rc_certificate_photos)
    RecyclerView rc_certificate_photos;

    BaseQuickAdapter adapter;
    ArrayAdapter<String> arrayAdapter;

    String receiveUserTel, supplierTel, spinnerContent, type;
    Map<Integer, String> imageUrl;
    boolean isLoading = false;
    int uploadIndex;
    int entityId;

    File captureFile = null;

    @OnClick({R.id.tv_title, R.id.receive_user_name, R.id.button, R.id.upload_1, R.id.upload_2, R.id.upload_3, R.id.tv_remove})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                finish();
                break;
            case R.id.receive_user_name:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + receiveUserTel)));
                break;
            case R.id.supplier_tel:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + supplierTel)));
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
                if (imageUrl.isEmpty()) {
                    error = "请至少上传一张图片";
                } else {
                    map.put("images", imageUrl.values().stream().reduce((s1, s2) -> s1 + ", " + s2).get());
                }

                if (type == REPLACE || type == REPLENISH) {
                    if (price.getText().toString().isEmpty()) {
                        error = "请输入实际金额";
                    } else {
                        map.put("price", Double.parseDouble(price.getText().toString()));
                    }

                    if (num.getText().toString().isEmpty()) {
                        error = "请输入实际数量";
                    } else {
                        map.put("num", Double.parseDouble(num.getText().toString()));
                    }

                    String v = money.getText().toString();
                    map.put("money", Double.parseDouble(v.substring(v.indexOf('：') + 1, v.indexOf('元'))));
                    if (type == REPLACE) map.put("remark", spinnerContent);
                }


                System.out.println("map = " + map);

                if (error.isEmpty()) {
                    getVM().loadingWithNewLiveData(String.class, map)
                            .observe(this, s -> {
                                GeneralUtils.showToastshort(s);
                            });
                } else {
                    GeneralUtils.showToastshort(error);
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

        original.setText("平台下单：" + getIntent().getStringExtra("original"));
        money.setText("折算后单价：" + getIntent().getStringExtra("money"));
        num_title.setText("实际数量：");
        price_title.setText("实际金额：");


        switch (type) {
            case REPLENISH:
                amount.setText("要求补货：" + getIntent().getStringExtra("amount"));
                num.setHint("请输入实际补货数量");
                price.setHint("请输入实际补货金额");
                break;
            case REPLACE:
                amount.setText("要求换货：" + getIntent().getStringExtra("amount"));
                num.setHint("请输入实际换货数量");
                price.setHint("请输入实际换货金额");


                //换货证明
                adapter = new BaseQuickAdapter<String, BaseViewHolder>(0) {
                    @Override
                    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
                        ImageView imageView = new ImageView(NetSupportDetailActivity.this);
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        return new BaseViewHolder(imageView);
                    }

                    @Override
                    protected void convert(BaseViewHolder baseViewHolder, String s) {
                        ImageLoad.loadImageViewLoding(s, (ImageView) baseViewHolder.itemView);
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
                findViewById(R.id.ll_info).setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void initData() {
        entityId = getIntent().getIntExtra("entityId", -1);
//        entityId = 72084;
        if (entityId == -1) return;

        imageUrl = new ArrayMap<>();

        Map<String, Object> map = new ArrayMap<>();
        map.put("entityId", entityId);

        showLoading();
        getVM().loadingWithNewLiveData(AfterDetailData.class, map)
                .observe(this, afterDetailData -> {
                    dismissLoading();
                    receiveUserTel = afterDetailData.order.receive_user_tel;
                    supplierTel = afterDetailData.order.supplier_tel;

                    customer_name.setText("门店：" + afterDetailData.order.customer_name);
                    shipping_line_code.setText("编号：" + afterDetailData.order.shipping_line_code);
                    receive_user_name.setText("收货人：" + afterDetailData.order.receive_user_name + "（" + afterDetailData.order.receive_user_tel + "）");
                    customer_address.setText("地址：" + afterDetailData.order.customer_address);
                    supplier_name.setText("供应商名称：" + afterDetailData.order.supplier_name);
                    supplier_tel.setText("供应商电话：" + afterDetailData.order.supplier_tel);
                    order_pay_time.setText("下单时间：" + afterDetailData.order.order_pay_time);
                    order_no.setText("商品单号：" + afterDetailData.order.order_no);
                    create_time.setText("退货时间：" + afterDetailData.order.create_time);

                    ((TextView) findViewById(R.id.product_criteria)).setText(afterDetailData.order.product_criteria.equals("1") ? "通" : "精");
                    ((TextView) findViewById(R.id.name)).setText(afterDetailData.order.name + (afterDetailData.order.nick_name.isEmpty() ? "" : "（" + afterDetailData.order.nick_name + "）"));
                    ((TextView) findViewById(R.id.store_name)).setText(afterDetailData.order.store_name.length() > 3 ? afterDetailData.order.store_name.substring(0, 3) : afterDetailData.order.store_name);
                    findViewById(R.id.type).setVisibility(View.GONE);
                    ImageLoad.loadImageViewLoding(afterDetailData.order.product_img, findViewById(R.id.product_img));

                    if (type.equals(REPLACE))
                        adapter.setNewData(Arrays.asList(afterDetailData.order.certificate_photos.split(",")));
//                    adapter.setNewData(Arrays.asList("http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2104101404050e4db32e.jpg", "http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805144018b97ed293.jpg"));


                    switch (type) {
                        case REPLENISH:
                            problem_description.setText(REPLENISH.substring(2, 4) + "原因：" + afterDetailData.order.problem_description);
                            break;
                        case REPLACE:
                            problem_description.setText(REPLACE.substring(2, 4) + "原因：" + afterDetailData.order.problem_description);
                            break;
                        case REFUND:
                            break;
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
                getVM().loadingWithNewLiveData(String.class, file, BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmap(captureFile.toString()), true));
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
                            .observe(this, s -> {
                                RequestOptions requestOptions = new RequestOptions();
                                requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(12));

                                GlideApp.with(this)
                                        .load(s)
                                        .error(R.mipmap.head_portrait)
                                        .apply(requestOptions)
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                dismissLoading();
                                                isLoading = false;
                                                GeneralUtils.showToastshort("图片上传失败！");
                                                LogUtils.e("upload failure, file is " + s);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                dismissLoading();
                                                isLoading = false;
                                                imageUrl.put(uploadIndex, s);
                                                getParent(uploadIndex).findViewById(R.id.tv_remove).setVisibility(View.VISIBLE);
                                                return false;
                                            }
                                        })
                                        .into(updateUploadImage());
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

        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            captureFile = File.createTempFile("JPEG_", null, getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断版本 如果在Android7.0以上,使用FileProvider获取Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            capture.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            LogUtils.e(getPackageName());
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", captureFile);
            System.out.println("contentUri = " + contentUri + ", captureFile = " + captureFile);
            capture.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

        } else {
            //否则使用Uri.fromFile(file)方法获取Uri
            capture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(captureFile));
        }

        Intent chooser = Intent.createChooser(capture, "请选择方式");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)});
        startActivityForResult(chooser, GeneralUtils.REQUEST_CODE_CHOOSE);
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
}
