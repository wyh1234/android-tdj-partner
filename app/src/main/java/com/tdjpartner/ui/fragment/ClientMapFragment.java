package com.tdjpartner.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientMapAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientFragmentType;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.mvp.presenter.ClientMapPresenter;
import com.tdjpartner.ui.activity.ClientDetailsActivity;
import com.tdjpartner.ui.activity.ClientListSeachActivity;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.LocationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ClientMapFragment extends BaseFrgment<ClientMapPresenter> implements LocationSource, AMap.OnMapLoadedListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.search_text)
    TextView search_text;
    private AMap aMap;
    private OnLocationChangedListener mlistener;
    private MyLocationStyle myLocationStyle;
    public RxPermissions rxPermissions;
    private boolean aBoolean;
    private ClientMapAdapter clientMapAdapter;
    private Marker screenMarker;
    private MarkerOptions markerOption;
    private LocationBean locationBean;

    public LocationBean getLocationBean() {
        return locationBean;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

    private List<ClientInfo> clientMapInfoList=new ArrayList<>();
    @OnClick({R.id.tv_list_type,R.id.search_text})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.tv_list_type:
                onDestroy();
                EventBus.getDefault().post(new ClientFragmentType("list"));
                break;
            case R.id.search_text:
                Intent intent=new Intent(getContext(), ClientListSeachActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void initView(View view) {
//        rxPermissions=new RxPermissions(getActivity());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layout);
        clientMapAdapter=new ClientMapAdapter(R.layout.map_info_list_layout,clientMapInfoList);
        clientMapAdapter.setOnItemClickListener(this);
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



    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
    /*    rxPermissions.request( Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Exception {
                aBoolean=b;
                if (b){
                    if (getLocationBean()==null){
                        LocationUtils.getInstance().startLocalService("MAP");
                    }

                }

            }
        });*/


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
    protected ClientMapPresenter loadPresenter() {
        return new ClientMapPresenter();
    }
    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mlistener = listener;

    }
    /*code 不同事件接受處理*/
    @Subscribe( threadMode = ThreadMode.MAIN)
    public void eventCode(LocationBean locationBean) {
        LogUtils.e(locationBean);
        if (locationBean.getTag().contains("MAP")){
            setLocationBean(locationBean);
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.setLocationSource(this);
            aMap.setMyLocationEnabled(true);
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜
            mlistener.onLocationChanged(locationBean.getaMapLocation());


            LatLng latLng = new LatLng(getLocationBean().getLatitude(),getLocationBean().getLongitude());
            LogUtils.e(latLng);
            //设置中心点和缩放比例
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

            aMap.setOnMapLoadedListener(this);//覆盖物监听
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


        Map<String,Object> map=new HashMap<>();
        map.put("userId",21);
        map.put("latitude",getLocationBean().getLatitude());
        map.put("longitude",getLocationBean().getLongitude());
        map.put("keyword","");
        mPresenter.hotelMap(map);

    }

    private void addMarkerInScreenCenter() {
        //多个覆盖物用for循环
        for (ClientInfo clientInfo:clientMapInfoList){
            LatLng latLng = new LatLng(Double.parseDouble(clientInfo.getLat()),Double.parseDouble(clientInfo.getLon()));
            markerOption = new MarkerOptions();
            markerOption.position(latLng);
            markerOption.title(clientInfo.getName());
            markerOption.anchor(0.5f, 0.5f);
            markerOption.icon(BitmapDescriptorFactory.fromView(getMarkerCountView(clientInfo)));
/*        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ziji_laction)));*/
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
            screenMarker=aMap.addMarker(markerOption);
        }


    }
    public View getMarkerCountView(ClientInfo clientInfo) {
        View view = this.getLayoutInflater().inflate(R.layout.ll_map_count_info, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.iv_maker_bg);
        if (clientInfo.getUserType()==1){
            imageView.setImageResource(R.mipmap.jiudianone_bg);
        }else if (clientInfo.getUserType()==2){
            imageView.setImageResource(R.mipmap.jiudiantwo_bg);
        }else if (clientInfo.getUserType()==3){
            imageView.setImageResource(R.mipmap.jiudianthree_bg);
        }else {
            imageView.setImageResource(R.mipmap.huangse);
        }

        TextView txt_count = (TextView) view.findViewById(R.id.txt_count);
        txt_count.setText(clientInfo.getName());
        return view;
    }


    public void hotelMap_Success(List<ClientInfo> clientInfoList) {

        if (!ListUtils.isEmpty(clientInfoList)) {
            LogUtils.e("aMap");
            recyclerview.setVisibility(View.VISIBLE);
            clientMapInfoList.addAll(clientInfoList);
            addMarkerInScreenCenter();
            clientMapAdapter.setNewData(clientMapInfoList);
        }else {
            recyclerview.setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (clientMapInfoList.get(i).getUserType()==1||clientMapInfoList.get(i).getUserType()==2){
            Intent intent=new Intent(getContext(), ClientDetailsActivity.class);
            intent.putExtra("customerId",clientMapInfoList.get(i).getCustomerId()+"");
            startActivity(intent);
        }

    }
}
