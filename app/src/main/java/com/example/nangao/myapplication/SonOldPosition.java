package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.location.Location;
import android.widget.Toast;
import android.content.Context;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

public class SonOldPosition extends AppCompatActivity implements LocationSource,AMapLocationListener{
    MapView mMapView = null;
    AMap aMap = null;

    private UiSettings mUiSettings;
    private CameraUpdate mUpdata;
    Marker marker;

    String objectid;
    String longitude;
    String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_position);

       //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        //接收从新增页传来的objectid，获取经纬度
        final Intent intent = getIntent();
        objectid = intent.getStringExtra("objectid");

        //根据objectid，获取对应老人的血压值、心跳值
        MessageManager.getInstance().getMytable();

        final BmobQuery<MyTable> Query = new BmobQuery<MyTable>();
        Query.findObjects(new FindListener<MyTable>() {
            @Override
            public void done(List<MyTable> list, BmobException e) {
                int panduan = 1;
                for (int i = 0; i < list.size(); i++) {
                    String bobjectid = list.get(i).getObjectId();
                    if (objectid.equals(bobjectid)) {
                        panduan = 2;
                        longitude = list.get(i).getLongitude();
                        latitude = list.get(i).getLatitude();
                        //传入经纬度数据，在此页面定位老人的信息
                        double lon = Double.parseDouble(longitude);
                        double lat = Double.parseDouble(latitude);
                        init(lat,lon);
                        break;
                    }
                }
                if (panduan == 1) {
                    Toast.makeText(SonOldPosition.this, "没有此objectid对应的值", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void init(double latitude,double longitude)
    {
        if (aMap == null) {
            aMap = mMapView.getMap();

            LatLng latLng = new LatLng(latitude,longitude);
            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));
        }
    }

     /**
          * 方法必须重写
          */
     @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
/**
     * 方法必须重写
     */
        @Override
        protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    public void onLocationChanged(Location location) {
// TODO Auto-generated method stub


    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
// TODO Auto-generated method stub

    }

    public void onProviderEnabled(String provider) {
// TODO Auto-generated method stub

    }

    public void onProviderDisabled(String provider) {
// TODO Auto-generated method stub

    }
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
// TODO Auto-generated method stub

    }
    @Override
    public void activate(OnLocationChangedListener arg0) {
// TODO Auto-generated method stub

    }
    @Override
    public void deactivate() {
// TODO Auto-generated method stub

    }
}