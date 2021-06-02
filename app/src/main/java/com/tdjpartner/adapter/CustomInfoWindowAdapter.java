package com.tdjpartner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.tdjpartner.R;

import io.reactivex.annotations.NonNull;

public class CustomInfoWindowAdapter implements AMap.InfoWindowAdapter {
    private Context mContext;

    private String mSnippet,mTitle;


    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
    private void initData(Marker marker) {
        //当前点位带的消息信息  也可通过这个传输数据把数据转成json
        mSnippet = marker.getSnippet();
        //当前点位带的标题信息
        mTitle = marker.getTitle();
    }

    @NonNull
    private View initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_map_infowindow, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(mTitle);
        return view;
    }
}
