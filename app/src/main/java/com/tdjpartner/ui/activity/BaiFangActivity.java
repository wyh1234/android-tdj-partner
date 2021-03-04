package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.BaiFangPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.CameraUtils;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static com.tdjpartner.utils.CameraUtils.CROP_REQUEST_CODE;
import static com.tdjpartner.utils.CameraUtils.REQUEST_PERMISSION_CAMERA;
import static com.tdjpartner.utils.CameraUtils.captureFile;
import static com.tdjpartner.utils.CameraUtils.cropFile;

public class BaiFangActivity extends BaseActivity<BaiFangPresenter>  {
    @BindView(R.id.rl_dk)
    RelativeLayout rl_dk;
    @BindView(R.id.rl_location)
    RelativeLayout rl_location;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_laction_name)
    TextView tv_laction_name;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.ed_callName)
    TextView ed_callName;
    @BindView(R.id.ed_callMobile)
    TextView ed_callMobile;
    @BindView(R.id.ed_matters)
    EditText ed_matters;
    @BindView(R.id.ed_results)
    EditText ed_results;
    @BindView(R.id.iv_upload)
    ImageView iv_upload;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private RxPermissions rxPermissions;
    private FollowUpPopuWindow followUpPopuWindow;
    private boolean f;
    private float distance;
    private String path;
    private  ClientDetails clientDetails;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @OnClick({R.id.rl_dk,R.id.rl_location,R.id.btn,R.id.btn_back,R.id.iv_upload})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_dk:

                break;
            case R.id.rl_location:
                if (f){
                    Intent intent=new Intent(this,CallLocationActivity.class);
                    intent.putExtra("clientDetails",clientDetails);
                    startActivity(intent);
                }else {
                    location();
                }


                break;
            case R.id.btn:
                if (!f){
                    GeneralUtils.showToastshort("请开启定位相关权限");
                    location();
                    return;
                }
                if (tv_state.getText().toString().equals("超区打卡")){
                    GeneralUtils.showToastshort("您不在打卡范围");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_callName.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访人姓名");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_callMobile.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访人联系方式");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_matters.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访的主要事宜");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(ed_results.getText().toString())){
                    GeneralUtils.showToastshort("请输入拜访的结果");
                    return;
                }
                if (GeneralUtils.isNullOrZeroLenght(getPath())){
                    GeneralUtils.showToastshort("请上传拜访图片");
                    return;
                }

                Map<String,Object> map=new HashMap<>();
                map.put("buyId",clientDetails.getCustomerId());//酒店id;
                map.put("buyName",clientDetails.getName());//酒店名称,从客户详情带入
                map.put("address",clientDetails.getAddress());//拜访地点
                map.put("callId",UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("callName",ed_callName.getText().toString());//拜访人
                map.put("callMobile",ed_callMobile.getText().toString());//拜访人电话
                map.put("userId","");//酒店负责人id,可为空
                map.put("userName",clientDetails.getBoss());//酒店负责人名称
                map.put("matters",ed_matters.getText().toString());//主要事宜
                map.put("results",ed_results.getText().toString());//拜访结果
                map.put("callPic",getPath());//拜访图片
                map.put("buyPic",clientDetails.getHeadUrl()==null?"":clientDetails.getHeadUrl());//门店门头照,从客户详情带入，可不传

                mPresenter.call_insert(map);


                break;
            case R.id.btn_back:
                finish();

                break;
            case R.id.iv_upload:

                    CameraUtils.getImageCamera(rxPermissions,this);
//                GeneralUtils.getImage(rxPermissions,this);

                break;
        }
    }

    @Override
    protected BaiFangPresenter loadPresenter() {
        return new BaiFangPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        rxPermissions = new RxPermissions(this);
        location();
         clientDetails=  ((ClientDetails)getIntent().getSerializableExtra("clientDetails"));
        tv_name.setText(clientDetails.getName());
        ed_callName.setText(UserUtils.getInstance().getLoginBean().getRealname());
        ed_callMobile.setText(UserUtils.getInstance().getLoginBean().getPhoneNumber());

    }

    public void  location(){
        rxPermissions.request( Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Exception {
                f=b;
                if (b){
                    LocationUtils.getInstance().startLocalService("");
                }else {
                    rl_dk.setBackgroundResource(R.mipmap.dakashibai);
                    tv_state.setText("无法打卡");
                    tv_state.setText(GeneralUtils.getColor(BaiFangActivity.this,R.color.gray_69));
                    tv_laction_name.setText("请开启定位相关权限");
                    iv.setImageResource(R.mipmap.gantanhao);

                }
            }
        });
    }

    @Subscribe
    public void eventCode(LocationBean locationBean) {
        LogUtils.e(locationBean);//根据酒店的经纬度计算距离
        distance =AMapUtils.calculateLineDistance(
                new LatLng(locationBean.getLatitude(),locationBean.getLongitude()),
                new LatLng(Double.parseDouble(clientDetails.getLat()),Double.parseDouble(clientDetails.getLon())));
        LogUtils.e(distance);
        if (distance>clientDetails.getPunchDistance()){//根据配置的距离计算是否在打卡范围
            rl_dk.setBackgroundResource(R.mipmap.daka_two);
            tv_state.setText("超区打卡");
            tv_state.setTextColor(GeneralUtils.getColor(this,R.color.white));
            tv_laction_name.setText("您不在打卡范围，"+locationBean.getAddress());
            iv.setImageResource(R.mipmap.gantanhao);
        }else {
            rl_dk.setBackgroundResource(R.mipmap.daka_one);
            tv_state.setText("正常打卡");
            tv_state.setTextColor(GeneralUtils.getColor(getContext(),R.color.white));
            tv_laction_name.setText("已进入考勤范围"+locationBean.getAddress());
            iv.setImageResource(R.mipmap.dakazc);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.baifang_layout;
    }

    public void call_insert_Success() {
        GeneralUtils.showToastshort("提交成功");
        finish();
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE_GRIDE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            LogUtils.i(Matisse.obtainPathResult(data).get(0));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainPathResult(data).get(0)));
            mPresenter.imageUpload(Matisse.obtainPathResult(data).get(0));
        }
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PERMISSION_CAMERA:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".fileProvider", captureFile);
                        CameraUtils.cropPhoto(contentUri,this);
                    } else {
                        CameraUtils. cropPhoto(Uri.fromFile(captureFile),this);
                    }


                    break;
                case CROP_REQUEST_CODE:
                    mPresenter.imageUpload( CameraUtils.saveImage(cropFile.getPath()));
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getImage_Success(String data) {
        setPath(data);
        ImageLoad.loadImageView(this,data,iv_upload);
    }
}
