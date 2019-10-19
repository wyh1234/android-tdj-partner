package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.Intent;
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
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.BaiFangPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class BaiFangActivity extends BaseActivity<BaiFangPresenter> implements FollowUpPopuWindow.FollowUpListener {
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
    EditText ed_callName;
    @BindView(R.id.ed_callMobile)
    EditText ed_callMobile;
    @BindView(R.id.ed_matters)
    EditText ed_matters;
    @BindView(R.id.ed_results)
    EditText ed_results;
    @BindView(R.id.iv_upload)
    ImageView iv_upload;
    private RxPermissions rxPermissions;
    private FollowUpPopuWindow followUpPopuWindow;
    private boolean f;
    private float distance;
    private String path;

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
                map.put("buyId","");//酒店id;
                map.put("buyName","");//酒店名称,从客户详情带入
                map.put("address","");//拜访地点
                map.put("callId",UserUtils.getInstance().getLoginBean().getEntityId());//	拜访地点
                map.put("callName",ed_callName.getText().toString());//拜访人
                map.put("callMobile",ed_callMobile.getText().toString());//拜访人电话
                map.put("userId","");//酒店负责人id,可为空
                map.put("userName","");//酒店负责人名称
                map.put("matters",ed_matters.getText().toString());//主要事宜

                map.put("results",ed_results.getText().toString());//拜访结果
                map.put("callPic",getPath());//拜访图片
                map.put("buyPic","");//门店门头照,从客户详情带入，可不传

                mPresenter.call_insert(map);


                break;
            case R.id.btn_back:
                finish();

                break;
            case R.id.iv_upload:
                GeneralUtils.getImage(rxPermissions,this);

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
        ed_callName.setText(UserUtils.getInstance().getLoginBean().getRealname());
        ed_callMobile.setText(UserUtils.getInstance().getLoginBean().getPhoneNumber());
        rxPermissions = new RxPermissions(this);
        location();


    }

    public void  location(){
        rxPermissions.request( Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
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

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void eventCode(LocationBean locationBean) {
        LogUtils.e(locationBean);//根据酒店的经纬度计算距离
        distance =AMapUtils.calculateLineDistance(new LatLng(locationBean.getLatitude(),locationBean.getLongitude()),null);
        LogUtils.e(distance);
        if (distance>120){//根据配置的距离计算是否在打卡范围
            rl_dk.setBackgroundResource(R.mipmap.daka_two);
            tv_state.setText("超区打卡");
            tv_state.setText(GeneralUtils.getColor(BaiFangActivity.this,R.color.white));
            tv_laction_name.setText("您不在打卡范围，"+locationBean.getAddress());
            iv.setImageResource(R.mipmap.gantanhao);
        }else {
            rl_dk.setBackgroundResource(R.mipmap.daka_one);
            tv_state.setText("正常打卡");
            tv_state.setText(GeneralUtils.getColor(getContext(),R.color.white));
            tv_laction_name.setText("已进入考勤范围"+locationBean.getAddress());
            iv.setImageResource(R.mipmap.dakazc);
            GeneralUtils.showToastshort("您已打卡成功");
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.baifang_layout;
    }

    public void call_insert_Success() {

        if (followUpPopuWindow!=null){
            if (followUpPopuWindow.isShowing()){
                return;
            }
            followUpPopuWindow.showPopupWindow();
        }else {

            followUpPopuWindow = new FollowUpPopuWindow(this,"BAIFANG");
            followUpPopuWindow.setDismissWhenTouchOutside(false);
            followUpPopuWindow.setInterceptTouchEvent(false);
            followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
            followUpPopuWindow.showPopupWindow();
        }
    }

    @Override
    public void onCancel() {
        followUpPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GeneralUtils.REQUEST_CODE_CHOOSE_GRIDE && resultCode == RESULT_OK) {//storage/emulated/0/Pictures/JPEG_20181011_155709.jpg
            LogUtils.i(Matisse.obtainPathResult(data).get(0));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainPathResult(data).get(0)));
            mPresenter.imageUpload(Matisse.obtainPathResult(data).get(0));
        }
    }

    public void getImage_Success(String data) {
        setPath(data);
        ImageLoad.loadImageView(this,data,iv_upload);
    }
}
