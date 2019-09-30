package com.tdjpartner.ui.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceClient;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientMapAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.fragment.ClientMapFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class CallLocationActivity extends BaseActivity  implements LocationSource, GeoFenceListener, AMap.OnMapLoadedListener, AMap.InfoWindowAdapter {
    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    private LocationSource.OnLocationChangedListener mlistener;
    private MyLocationStyle myLocationStyle;
    public RxPermissions rxPermissions;
    private boolean aBoolean;
    private ClientMapAdapter clientMapAdapter;
    private Circle circle;
    private GeoFenceClient mGeoFenceClient;

    //定义接收广播的action字符串
    public static final String GEOFENCE_BROADCAST_ACTION = "com.location.apis.geofencedemo.broadcast";
    private MyBroadcastReceiver mBReceiver;
    private MarkerOptions markerOption;
    private Marker screenMarker;
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        Eyes.setStatusBarColor(this, GeneralUtils.getColor(this,R.color.white));
    }

    @Override
    protected void initView() {

        if (aMap==null)
        {
            aMap=mapView.getMap();
        }
        mGeoFenceClient = new GeoFenceClient(AppAplication.getAppContext());
        mGeoFenceClient.setGeoFenceListener(this);
        mGeoFenceClient.setActivateAction(GeoFenceClient.GEOFENCE_IN| GeoFenceClient.GEOFENCE_OUT| GeoFenceClient.GEOFENCE_STAYED);
        mGeoFenceClient.createPendingIntent(GEOFENCE_BROADCAST_ACTION);
        //接受定位广播
        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(GEOFENCE_BROADCAST_ACTION);
        mBReceiver = new MyBroadcastReceiver();
        registerReceiver(mBReceiver, filter);

        aMap.setOnMapLoadedListener(this);//覆盖物监听
        aMap.setInfoWindowAdapter(this);//定义气泡

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ziji_laction));
        CreateCircle(30.593291,114.33751);

    }

    public void CreateCircle(double a,double b){
        circle=aMap.addCircle(new CircleOptions().
                center(new LatLng(a,b)).
                radius(167).
                fillColor(GeneralUtils.getColor(getContext(),R.color.view_bg3)).
                strokeColor(GeneralUtils.getColor(getContext(),R.color.view_bg2)).
                strokeWidth(2));

        DPoint centerPoint = new DPoint(a,b);
        mGeoFenceClient.addGeoFence(centerPoint, 167f, "1");
    }
    @Override
    public void onGeoFenceCreateFinished(List<GeoFence> list, int i, String s) {
        if (i == GeoFence.ADDGEOFENCE_SUCCESS) {//判断围栏是否创建成功
            LogUtils.e("ee", "创建围栏成功");

        } else {
            LogUtils.e("ee", "创建围栏失敗");
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.call_location_layout;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(LocationUtils.mLocationClient!=null){
            LocationUtils.mLocationClient.onDestroy();
        }
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mlistener = onLocationChangedListener;
        rxPermissions.request( Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Exception {
                aBoolean=b;
                if (b){
                    LocationUtils.getInstance().startLocalService();
                }

            }
        });
    }
    /*code 不同事件接受處理*/
    @Subscribe( threadMode = ThreadMode.MAIN)
    public void eventCode(LocationBean locationBean) {
        LogUtils.e(locationBean);
        if (aBoolean){
            mlistener.onLocationChanged(locationBean.getaMapLocation());


        }

    }

    @Override
    public void deactivate() {
        mlistener = null;
        LocationUtils.getInstance().stopLocalService();
    }

    @Override
    public void onMapLoaded() {
        addMarkerInScreenCenter();
    }

    private void addMarkerInScreenCenter() {

        LatLng latLng = new LatLng(30.593291,114.33751);
        markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title("武汉").snippet("武汉：30.593291,114.33751");
        markerOption.anchor(0.5f, 0.5f);
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ziji_laction)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        screenMarker=aMap.addMarker(markerOption);
        screenMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        screenMarker.showInfoWindow();//设置气泡

    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        View infoWindow = getLayoutInflater().inflate(R.layout.display, null);//display为自定义layout文件
        //此处省去长篇代码
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GEOFENCE_BROADCAST_ACTION)) {
                //解析广播内容
                Bundle bundle = intent.getExtras();
                //获取当前有触发的围栏对象：
                GeoFence fence = bundle.getParcelable(GeoFence.BUNDLE_KEY_FENCE);
                Toast.makeText(context, fence.getStatus() + "-----------", Toast.LENGTH_SHORT).show();
                int status = bundle.getInt(GeoFence.BUNDLE_KEY_FENCESTATUS);
                StringBuffer sb = new StringBuffer();
                switch (status) {
                    case GeoFence.STATUS_LOCFAIL:
                        sb.append("定位失败");
                        break;
                    case GeoFence.STATUS_IN:
                        sb.append("进入围栏 ");
                        Toast.makeText(context, "enter围栏", Toast.LENGTH_SHORT).show();
                        LogUtils.e("tttt", "进入围栏");
                        break;
                    case GeoFence.STATUS_OUT:
                        sb.append("离开围栏 ");
                        Toast.makeText(context, "离开围栏", Toast.LENGTH_SHORT).show();
                        LogUtils.e("tttt", "离开围栏");
                        break;
                    case GeoFence.STATUS_STAYED:
                        sb.append("停留在围栏内 ");
                        LogUtils.e("tttt", "停留围栏");
                        Toast.makeText(context, "停留围栏", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }


}
