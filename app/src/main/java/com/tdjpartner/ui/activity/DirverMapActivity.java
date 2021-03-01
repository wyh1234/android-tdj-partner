package com.tdjpartner.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DegreeOfSatisfaction;
import com.tdjpartner.model.DriverLocation;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.mvp.presenter.DirverMapPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_DEFAULT;


public class DirverMapActivity extends BaseActivity<DirverMapPresenter>implements RouteSearch.OnRouteSearchListener {
    @BindView(R.id.tv_delivery_status)
    TextView tv_delivery_status;
    @BindView(R.id.tv_delivery_date)
    TextView tv_delivery_date;
    @BindView(R.id.giv_driver_icon)
    ImageView giv_driver_icon;
    @BindView(R.id.ll_call_driver)
    LinearLayout ll_call_driver;
    @BindView(R.id.tv_driver_name)
    TextView tv_driver_name;

    @BindView(R.id.tv_driver_rate)
    TextView tv_driver_rate;
    @BindView(R.id.mv_dirver_map)
    MapView mv_dirver_map;
    @BindView(R.id.tv_distance)
    TextView tv_distance;
    private AMap mAMap;
   private OrderDetail orderDetail;
    private RouteSearch routeSearch;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    @OnClick({R.id.tv_delivery_status,R.id.ll_call_driver})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_delivery_status:
                finish();
                break;
            case R.id.ll_call_driver:
                GeneralUtils.action_call(new RxPermissions(this), orderDetail.getItems().get(0).getDriverTel(),this);

                break;

        }
    }
    @Override
    protected DirverMapPresenter loadPresenter() {
        return new DirverMapPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap(savedInstanceState);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(ACCESS_COARSE_LOCATION, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
                .subscribe(b -> {
                    if (!b) {
                        GeneralUtils.showToastshort("请开启定位权限和存储权限!");
                        finish();
                    }
                });


        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
    }

    @Override
    protected void initData() {
        orderDetail = (OrderDetail) getIntent().getSerializableExtra("orderDetail");
        tv_driver_name.setText(orderDetail.getItems().get(0).getDriverName());
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_delivery_date.setText("当前距离收货地址还有");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dirver_map;
    }
    private void initMap(Bundle savedInstanceState) {
        mv_dirver_map.onCreate(savedInstanceState);
        if (mAMap == null){
            mAMap = mv_dirver_map.getMap();
        }
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(17)); //缩放比例

        //设置amap的属性
        UiSettings settings = mAMap.getUiSettings();
        settings.setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        settings.setZoomControlsEnabled(false);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        initDriverMap();
    }
    public void initDriverMap(){
        String orderNo = orderDetail.getOrderNo();
        String driverTel = orderDetail.getItems().get(0).getDriverTel();
        String extOrderId = orderDetail.getExtOrderId();
        int addressId = orderDetail.getCustomerAddrId();
        int driverId = orderDetail.getItems().get(0).getDriverId();
        mPresenter.getDriverLocation(orderNo, driverTel, addressId);
    }
    public void getDriverLocationSuccess(DriverLocation driverLocation) {

            switch (driverLocation.getDriverStatus()){
                case 5:
                    tv_delivery_status.setText("已送达");
                    tv_distance.setVisibility(View.GONE);
                    tv_delivery_date.setText("已于"+driverLocation.getDeliveryTime()+"送达");
                    break;
                case 6:
                    tv_delivery_status.setText("配送中");
                    break;
                default:break;
            }
            if (driverLocation.getCustomerLat()>0&&driverLocation.getCustomerLng()>0) {
                MarkerOptions store = new MarkerOptions().anchor(0.5f,0.5f)
                        .position(new LatLng(driverLocation.getCustomerLat(), driverLocation.getCustomerLng()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.store));
                mAMap.addMarker(store);
            }else {
                GeneralUtils.showToastshort("无店铺坐标");
            }
            if (driverLocation.getDriverLat()>0&&driverLocation.getDriverLng()>0){
                MarkerOptions driver = new MarkerOptions().anchor(0.5f,0.5f)
                        .position(new LatLng(driverLocation.getDriverLat(),driverLocation.getDriverLng()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.delivery_car));
                mAMap.addMarker(driver);
            }else {
                GeneralUtils.showToastshort("无司机坐标");
            }
            if (driverLocation.getDriverStatus()==6&&driverLocation.getCustomerLat()>0&&driverLocation.getCustomerLng()>0&&driverLocation
                    .getDriverLat()>0&&driverLocation.getDriverLng()>0){
                LatLng storePoint = new LatLng(driverLocation.getCustomerLat(), driverLocation.getCustomerLng());
                LatLng driverPoint = new LatLng(driverLocation.getDriverLat(),driverLocation.getDriverLng());
                float distanceNum = AMapUtils.calculateLineDistance(storePoint,driverPoint);
                if ((int)distanceNum>1000){
                    tv_distance.setText((int)distanceNum/1000+"公里");
                }else {
                    tv_distance.setText((int)distanceNum + "米");
                }
                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(driverPoint,setMapZoom((int)distanceNum)));
                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(driverPoint.latitude, driverPoint.longitude),
                        new LatLonPoint(storePoint.latitude, storePoint.longitude));
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, DRIVING_SINGLE_DEFAULT, null, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
            }
    }

    public int setMapZoom(int distanceNum){
        if (distanceNum>50000) {
            return 9;
        }else if (distanceNum>30000){
            return 10;
        }else if (distanceNum>20000){
            return 10;
        }else if (distanceNum>10000){
            return 11;
        }else if (distanceNum>5000){
            return 12;
        }else if (distanceNum>2000){
            return 13;
        }else if (distanceNum>1000){
            return 14;
        }else if (distanceNum>500){
            return 15;
        }else if (distanceNum>200){
            return 16;
        }else if (distanceNum>100){
            return 17;
        }else if (distanceNum>50){
            return 18;
        }else {
            return 19;
        }
    }

    //公交路线
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {}

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        if (i==1000) {
            List<DrivePath> pathList = driveRouteResult.getPaths();
            List<LatLng> driverPath = new ArrayList<>();
            for (DrivePath dp : pathList) {
                List<DriveStep> stepList = dp.getSteps();
                for (DriveStep ds : stepList) {
                    List<LatLonPoint> points = ds.getPolyline();
                    for (LatLonPoint llp : points) {
                        driverPath.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                    }
                }
            }
            mAMap.addPolyline(new PolylineOptions()
                    .addAll(driverPath)
                    .width(10)
                    //绘制成大地线
                    .geodesic(false)
                    //设置画线的颜色
                    .color(Color.argb(200, 39, 152, 235)));
        }else {
            GeneralUtils.showToastshort("errorCode:"+i);
        }
    }

    //步行路线
    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) { }
    //骑行路线
    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) { }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv_dirver_map.onSaveInstanceState(outState);
    }

    protected void onResume() {
        super.onResume();
        mv_dirver_map.onResume();
        initDriverMap();
    }

    protected void onPause() {
        super.onPause();
        mv_dirver_map.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        // 销毁定位
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mv_dirver_map.onDestroy();
    }

    public void getDegreeOfSatisfactionSuccess(DegreeOfSatisfaction degreeOfSatisfaction) {

           tv_driver_rate.setText("满意度："+degreeOfSatisfaction.getData().getDegreeOfSatisfaction());
        }
}
