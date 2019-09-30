package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceClient;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
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
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.adapter.ClientMapAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.model.ClientMapInfo;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.LocationUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ClientMapFragment extends BaseFrgment implements LocationSource, AMap.OnMapLoadedListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    private OnLocationChangedListener mlistener;
    private MyLocationStyle myLocationStyle;
    public RxPermissions rxPermissions;
    private boolean aBoolean;
    private ClientMapAdapter clientMapAdapter;
    private Marker screenMarker;
    private MarkerOptions markerOption;
    private List<ClientMapInfo> clientMapInfoList=new ArrayList<>();
    @OnClick({R.id.tv_list_type})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.tv_list_type:
                EventBus.getDefault().post(new ClientFragmentType("list"));
                break;
        }
    }
    @Override
    protected void initView(View view) {
        rxPermissions=new RxPermissions(getActivity());
        clientMapInfoList.add(new ClientMapInfo());
        clientMapInfoList.add(new ClientMapInfo());
        clientMapInfoList.add(new ClientMapInfo());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layout);
        clientMapAdapter=new ClientMapAdapter(R.layout.map_info_list_layout,clientMapInfoList);
        recyclerview.setAdapter(clientMapAdapter);
    }
    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }


        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));//缩放比例
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜

        aMap.setOnMapLoadedListener(this);//覆盖物监听
    }
    @Override
    protected void loadData() {

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerEventBus(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unregisterEventBus(this);
    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }
    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mlistener = listener;
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
    protected int getContentId() {
        return R.layout.client_map_fragment;
    }


    @Override
    public void onMapLoaded() {
        addMarkerInScreenCenter();
    }

    private void addMarkerInScreenCenter() {
        //多个覆盖物用for循环
        LatLng latLng = new LatLng(30.593291,114.33751);
        markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title("武汉").snippet("武汉：30.593291,114.33751");
        markerOption.anchor(0.5f, 0.5f);
        View view = getMarkerCountView(R.mipmap.ziji_laction);
        markerOption.icon(BitmapDescriptorFactory.fromView(view));
/*        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ziji_laction)));*/
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        screenMarker=aMap.addMarker(markerOption);

    }
    public View getMarkerCountView(int icon) {
        View view = this.getLayoutInflater().inflate(R.layout.ll_map_count_info, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.iv_maker_bg);
        imageView.setImageResource(icon);
        TextView txt_count = (TextView) view.findViewById(R.id.txt_count);

        return view;
    }



}
