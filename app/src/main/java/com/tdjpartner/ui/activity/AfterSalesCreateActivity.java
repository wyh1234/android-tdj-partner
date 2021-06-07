package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.ProblemType;
import com.tdjpartner.mvp.presenter.AfterSalesCreatePresenter;
import com.tdjpartner.utils.ColorUtil;
import com.tdjpartner.utils.DensityUtil;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.MySpecialStyle;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.BlurBitmapUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.AfterSalesTypePopuWindow;
import com.tdjpartner.utils.popuwindow.ProblemTypePopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;
import com.zyinux.specialstring.relase.SpecialStringBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class AfterSalesCreateActivity extends BaseActivity<AfterSalesCreatePresenter> implements AfterSalesTypePopuWindow.AfterSalesTypePopuWindowListener, ProblemTypePopuWindow.ProblemTypePopuWindowListener {
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.goods_image)
    ImageView goods_image;
    @BindView(R.id.special_offer)
    ImageView special_offer;
    @BindView(R.id.jinpin)
    ImageView jinpin;
    @BindView(R.id.tv_isP)
    TextView tv_isP;
    @BindView(R.id.goods_name)
    TextView goods_name;
    @BindView(R.id.goods_price)
    TextView goods_price;
    @BindView(R.id.goods_unit)
    TextView goods_unit;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.level2Value)
    TextView level2Value;
    @BindView(R.id.level2Unit)
    TextView level2Unit;
    @BindView(R.id.specification_split)
    TextView specification_split;

    @BindView(R.id.level3Value)
    TextView level3Value;
    @BindView(R.id.level3Unit)
    TextView level3Unit;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.goods_count_x)
    TextView goods_count_x;
    @BindView(R.id.goods_count)
    TextView goods_count;
    @BindView(R.id.goods_unit2)
    TextView goods_unit2;
    @BindView(R.id.line_total_price)
    LinearLayout line_total_price;
    @BindView(R.id.cart_price)
    TextView cart_price;
    @BindView(R.id.after_sales_type)
    TextView after_sales_type;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.problem_type)
    TextView problem_type;
    @BindView(R.id.tv_after_sales)
    TextView tv_after_sales;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.delete_image)
    ImageView delete_image;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.delete_image1)
    ImageView delete_image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.delete_image2)
    ImageView delete_image2;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.description)
    EditText description;
    private BigDecimal cou = BigDecimal.ZERO;//可以退换的总数
    private BigDecimal priceSum = BigDecimal.ZERO; //可以退款总数
    private BigDecimal goodscount = BigDecimal.ZERO;
    private OrderDetail.ItemsBean orderBean;
    private AfterSalesTypePopuWindow afterSalesTypePopuWindow;
    private ProblemTypePopuWindow problemTypePopuWindow;
    private List<String> strings = new ArrayList<>();
    private int index;
    File captureFile;
    RxPermissions rxPermissions;

    @OnClick({R.id.btn_back, R.id.after_sales_type, R.id.image, R.id.image1, R.id.image2, R.id.delete_image, R.id.delete_image1, R.id.delete_image2, R.id.btn, R.id.problem_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.after_sales_type:
                if (afterSalesTypePopuWindow != null) {
                    if (afterSalesTypePopuWindow.isShowing()) {
                        return;
                    }
                    afterSalesTypePopuWindow.showPopupWindow();
                } else {

                    afterSalesTypePopuWindow = new AfterSalesTypePopuWindow(this);
                    afterSalesTypePopuWindow.setDismissWhenTouchOutside(false);
                    afterSalesTypePopuWindow.setInterceptTouchEvent(false);
                    afterSalesTypePopuWindow.setPopupWindowFullScreen(true);//铺满
                    afterSalesTypePopuWindow.setAfterSalesTypListener(this);
                    afterSalesTypePopuWindow.showPopupWindow();
                }
                break;
            case R.id.problem_type:
                if (problemTypePopuWindow != null) {
                    if (problemTypePopuWindow.isShowing()) {
                        return;
                    }
                    problemTypePopuWindow.showPopupWindow();
                } else {

                    problemTypePopuWindow = new ProblemTypePopuWindow(this);
                    problemTypePopuWindow.setDismissWhenTouchOutside(false);
                    problemTypePopuWindow.setInterceptTouchEvent(false);
                    problemTypePopuWindow.setPopupWindowFullScreen(true);//铺满
                    problemTypePopuWindow.setProblemTypePopuWindowListener(this);
                    problemTypePopuWindow.showPopupWindow();
                }

                break;
            case R.id.image:
                index = 1;
                updateFile();
                break;
            case R.id.image1:
                index = 2;
                updateFile();
                break;
            case R.id.image2:
                index = 3;
                updateFile();
                break;
            case R.id.delete_image:
                strings.remove(0);
                delete_image.setVisibility(View.GONE);
                image.setImageResource(R.mipmap.sahngchuantupian);
                break;
            case R.id.delete_image1:
                strings.remove(1);
                delete_image1.setVisibility(View.GONE);
                image1.setImageResource(R.mipmap.sahngchuantupian);
                break;
            case R.id.delete_image2:
                strings.remove(2);
                delete_image2.setVisibility(View.GONE);
                image2.setImageResource(R.mipmap.sahngchuantupian);
                break;
            case R.id.btn:
                int applyIndex = PublicCache.getAfterSaleType().idOfValue(after_sales_type.getText().toString());
                if (goodscount.compareTo(BigDecimal.ZERO) == 0) {
                    GeneralUtils.showToastshort("请输入需要退换的数量");
                    return;
                }
                if (count.getText().toString().equals("")) {
                    GeneralUtils.showToastshort("请输入需要退换的数量");
                    tv_after_sales.setText("0.00");
                    return;
                }

                if (description.getText().length() == 0) {
                    GeneralUtils.showToastshort("请输入具体的问题描述");
                    return;
                }
                if (applyIndex < 0) {
                    GeneralUtils.showToastshort("请选择售后类型");
                    return;
                }
                int problemIndex = PublicCache.getAfterSaleProblem().idOfValue(problem_type.getText().toString());
                if (problemIndex < 0) {
                    GeneralUtils.showToastshort("请选择售后原因");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("customerId", Integer.parseInt(getIntent().getStringExtra("buyId")));
                map.put("storeId", orderBean.getStoreId());
                map.put("orderId", orderBean.getOrderId());
                map.put("orderItemId", orderBean.getItemId());
                map.put("productImg", orderBean.getImage());
                map.put("sku", orderBean.getSku());
                map.put("unit", orderBean.getUnit());
                map.put("name", orderBean.getName());
                map.put("nickName", orderBean.getNickName());
                map.put("price", orderBean.getPrice());
                map.put("amount", goodscount);
                map.put("problemDescription", description.getText().toString());
                map.put("certificatePhotos", getImageStr());
                map.put("status", 1);
                int applyType = PublicCache.getAfterSaleType().keyOfValue(after_sales_type.getText().toString());
                int problemType = PublicCache.getAfterSaleProblem().keyOfValue(problem_type.getText().toString());
                map.put("applyType", applyType);
                map.put("problemType", problemType);
                map.put("submitterName", UserUtils.getInstance().getLoginBean().getRealname());
                map.put("submitterTel", UserUtils.getInstance().getLoginBean().getPhoneNumber());

                map.put("customerName", UserUtils.getInstance().getLoginBean().getRealname());
                map.put("site", UserUtils.getInstance().getLoginBean().getSite());
                mPresenter.afterSalesApplication(map);

                break;
        }
    }

    public String getImageStr() {
        if (strings.size() < 1) return "";
        String gallery = "";
        for (int i = 0; i <= strings.size() - 1; i++) {
            if (strings.get(i) != null) {
                gallery += strings.get(i) + ",";
            }
        }
        if (gallery.length() > 0) gallery = gallery.substring(0, gallery.length() - 1);
        return gallery;
    }

    @Override
    protected AfterSalesCreatePresenter loadPresenter() {
        return new AfterSalesCreatePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);
        tv_title.setText("申请售后");
        after_sales_type.setHint("请选择售后类型");


    }

    @Override
    protected int getLayoutId() {
        return R.layout.after_sales_create_layout;
    }

    //详情入口
    @Subscribe(sticky = true)
    public void onEvent(OrderDetail.ItemsBean event) {
        orderBean = event;
        LogUtils.e(orderBean);
//        EventBus.getDefault().removeStickyEvent(event);
        if (orderBean != null) {
            ImageLoad.loadImageViewLoding(orderBean.getImage(), goods_image);
            goods_unit.setText("" + orderBean.getUnit());
            goods_price.setText("" + orderBean.getPrice());
            cart_price.setText("" + orderBean.getTotalPrice());
            level2Value.setText("" + orderBean.getLevel2Value());
            level2Unit.setText("" + orderBean.getLevel2Unit());
            level3Unit.setText("" + orderBean.getLevel3Unit());
            level3Value.setText("" + orderBean.getLevel3Value());
            tv_isP.setText("" + orderBean.getIsP());
            goods_count_x.setText("X" + orderBean.getAmount());
            goods_unit2.setText("" + orderBean.getAvgUnit());

            if (orderBean.getLevelType() == 1) {
                level3Value.setVisibility(View.GONE);
                specification_split.setVisibility(View.GONE);
                level3Unit.setVisibility(View.GONE);

                level2Value.setVisibility(View.GONE);
                level2Unit.setVisibility(View.GONE);

                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
            } else if (orderBean.getLevelType() == 2) {
                level3Value.setVisibility(View.GONE);
                specification_split.setVisibility(View.GONE);
                level3Unit.setVisibility(View.GONE);

                level2Value.setVisibility(View.VISIBLE);
                level2Unit.setVisibility(View.VISIBLE);

                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
            } else {
                level3Value.setVisibility(View.VISIBLE);
                specification_split.setVisibility(View.VISIBLE);
                level3Unit.setVisibility(View.VISIBLE);

                level2Value.setVisibility(View.VISIBLE);
                level2Unit.setVisibility(View.VISIBLE);

                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
            }


            //如果均价单位是最小单
            if (PublicCache.specification_unit_base.contains(orderBean.getAvgUnit())) {
                if (orderBean.getLevelType() == 1) {
                    goods_count.setText(orderBean.getAmount() + "");
                } else if (orderBean.getLevelType() == 2) {
                    goods_count.setText(orderBean.getLevel2Value().multiply(orderBean.getAmount()) + "");
                } else if (orderBean.getLevelType() == 3) {
                    goods_count.setText(orderBean.getLevel3Value().multiply(orderBean.getLevel2Value().multiply(orderBean.getAmount())) + "");
                }
            } else {
                if (orderBean.getLevelType() == 2) {
                    goods_count.setText(orderBean.getAmount() + "");
                } else if (orderBean.getLevelType() == 3) {
                    goods_count.setText(orderBean.getLevel2Value().multiply(orderBean.getAmount()) + "");
                }
            }
            SpecialStringBuilder sb = getTitleName(
                    orderBean.getName(), orderBean.getNickName(), orderBean.getProductType(), orderBean.getProductCriteria(), orderBean.getIsP());
            goods_name.setText(sb.getCharSequence());
            String unit = "";
            cou = orderBean.getAmount();
            priceSum = orderBean.getTotalPrice();
            unit = orderBean.getAvgUnit();
            if (orderBean.getLevelType() == 2) {
                if (PublicCache.specification_unit_base.contains(unit) && orderBean.getLevel2Unit().equals(unit))
                    cou = cou.multiply(orderBean.getLevel2Value());
            } else if (orderBean.getLevelType() == 3) {
                if (PublicCache.specification_unit_base.contains(unit) && orderBean.getLevel3Unit().equals(unit))
                    cou = cou.multiply(orderBean.getLevel2Value()).multiply(orderBean.getLevel3Value());
                else cou = cou.multiply(orderBean.getLevel2Value());
            }


            count.setHint("最多可退 " + String.valueOf(cou.stripTrailingZeros().toPlainString()) + " " + unit);
            count.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String str = s.length() == 0 ? "0" : s.toString();
                    // if (str.startsWith(".")) str = "0" + str;
                    if (s.length() == 0) {
                        tv_after_sales.setText("0");
                        return;
                    }


                    if ((goodscount = new BigDecimal(str)).compareTo(cou) > 0) {
                        count.setError("您没有这么多商品需要退换");
                        goodscount = BigDecimal.ZERO;
                        return;
                    }
                    LogUtils.i(priceSum);
                    if (orderBean.getAvgPrice().multiply(goodscount).compareTo(priceSum) > 0) {
                        tv_after_sales.setText(String.valueOf(priceSum));
                    } else {
                        if (goodscount.compareTo(cou) == 0)
                            tv_after_sales.setText(String.valueOf(priceSum));
                        else
                            tv_after_sales.setText(String.valueOf(priceSum.divide(cou, 2, BigDecimal.ROUND_HALF_UP).multiply(goodscount).setScale(2, BigDecimal.ROUND_HALF_UP)));
                    }
                }
            });

        }
    }

    public SpecialStringBuilder getTitleName(String name, String nickName, int productType, int productCriteria, int isP) {

        MySpecialStyle style = new MySpecialStyle();
        SpecialStringBuilder sb = new SpecialStringBuilder();


        int size = DensityUtil.sp2px(16f) + 4;

        //是否是热销
        if (productType == 3 || productType == 4) {
            Drawable drawable_rexiao;
            if (productType == 3) {
                drawable_rexiao = getResources().getDrawable(R.mipmap.rexiao);
            } else {
                drawable_rexiao = getResources().getDrawable(R.mipmap.bao);

            }

            drawable_rexiao.setBounds(0, 0, size, size);
            ImageSpan imageSpan_rexiao = new ImageSpan(drawable_rexiao);


            style.setImage(imageSpan_rexiao);
            sb.append("1", style);
        }
        sb.append(" ", style);
        //商品标准“1”：通货商品 “2”：精品商品 '
        if (productCriteria == 2) {
            Drawable drawable_jin = getResources().getDrawable(R.mipmap.icon_jin_red);
            drawable_jin.setBounds(0, 0, size, size);
            ImageSpan imageSpan_jin = new ImageSpan(drawable_jin);

            style.setImage(imageSpan_jin);
            sb.append("2", style);
            sb.append(" ", style);
            // spannableString.setSpan(imageSpan_jin, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE  );
        } else if (productCriteria == 1) {
            Drawable drawable_tong = getResources().getDrawable(R.mipmap.icon_tong_blue);
            drawable_tong.setBounds(0, 0, size, size);//drawable_tong.getIntrinsicWidth(), drawable_tong.getIntrinsicHeight()
            ImageSpan imageSpan_tong = new ImageSpan(drawable_tong);

            style.setImage(imageSpan_tong);
            sb.append("2", style);
            sb.append(" ", style);
        }

        //零售 0   整件批  1
        if (isP == 1) {
            style.setColor(ColorUtil.getColor(R.color.orange_yellow_ff7d01));
            sb.append("P", style);
            sb.append(" ", style);
        }
        style.setColor(ColorUtil.getColor(R.color.black_4b));
        style.setAbsoluteSizeStyle(DensityUtil.sp2px(16f));
        sb.append(name, style);
        if (!TextUtils.isEmpty(nickName)) {
            style.setColor(ColorUtil.getColor(R.color.gray_68));
            style.setAbsoluteSizeStyle(DensityUtil.sp2px(14f));
            sb.append("(" + nickName + ")", style);
        }


        return sb;
    }


    @Override
    public void onOk(AfterSalesType afterSalesType) {
        afterSalesTypePopuWindow.dismiss();
        after_sales_type.setText(afterSalesType.getTypeName());
    }

    @Override
    public void onProblemTypeOk(ProblemType problemType) {
        problem_type.setText(problemType.getTypeName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                String file;
                if (data == null) {
                    file = captureFile.toString().substring(captureFile.toString().lastIndexOf("/") + 1).replace("tmp", "png");
                    System.out.println("file = " + file);
                    mPresenter.imageUpload(file, BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmap(captureFile.toString()), true));

                } else {
                    file = Uri.decode(data.getData().getEncodedPath());
                    System.out.println("file = " + file);
                    file = file.substring(file.lastIndexOf("/") + 1);
                    System.out.println("file = " + file);
                    if (!file.endsWith(".jpg") && !file.endsWith(".gif") && !file.endsWith(".png")) {
//                    GeneralUtils.showToastshort("文件格式不正确");
                        if (file.lastIndexOf('.') == -1) file += ".tmp";
                    }

                    try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(data.getData(), "r")) {
                        mPresenter.imageUpload(file.substring(0, file.length() - 3) + "png", BlurBitmapUtils.bitmapTobyte(BlurBitmapUtils.getSmallBitmapFromFileDescriptor(pfd.getFileDescriptor()), true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                LogUtils.i(Matisse.obtainPathResult(data).get(0));
                Log.e("OnActivityResult ", String.valueOf(Matisse.obtainPathResult(data).get(0)));
                mPresenter.imageUpload(Matisse.obtainPathResult(data).get(0));
            }
        }
    }

    public void getImage_Success(String data) {
        strings.add(data);
        if (index == 1) {
            ImageLoad.loadImageView(this, data, image);
            delete_image.setVisibility(View.VISIBLE);
        } else if (index == 2) {
            ImageLoad.loadImageView(this, data, image1);
            delete_image1.setVisibility(View.VISIBLE);
        } else {
            ImageLoad.loadImageView(this, data, image2);
            delete_image2.setVisibility(View.VISIBLE);
        }
    }

    public void getAfterSales_Success(AfterSales data) {
        data.setStatus(6);
        GeneralUtils.showToastshort("售后申请已提交");//
        EventBus.getDefault().post(data);
        finish();


    }

    private void updateFile() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (rxPermissions == null) rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.CAMERA)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean b) throws Exception {
                            if (b) {
                                captureFile = GeneralUtils.startCamera(AfterSalesCreateActivity.this);
                            } else {
                                GeneralUtils.showToastshort("摄像头启动失败，请从相册中选择图片！");
                            }

                        }
                    });

        } else {
            GeneralUtils.getImage(new RxPermissions(this), this);
        }

    }
}
