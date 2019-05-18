package com.example.nangao.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class SonOldTracking extends AppCompatActivity  implements LocationSource,AMapLocationListener {
    MapView mMapView = null;
    AMap aMap = null;
    String objectid;
    List<LatLng> latLngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_tracking);

        final Intent intent = getIntent();//接收从新增页传来的objectid
        objectid = intent.getStringExtra("objectid");

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.maptrack);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        ArrayList<LatLng> latLngs = new ArrayList<>();

        setUpMap(Position.getLatLngs(objectid,latLngs));

    }



    /**
     * 在地图上画线
     */
    private void setUpMap(List<LatLng> list){

        if(list.size()>1){

            PolylineOptions polt=new PolylineOptions();

            for(int i=0;i<list.size();i++){

                polt.add(list.get(i));

            }
            polt.width(5).geodesic(true).color(Color.GREEN);
            aMap.addPolyline(polt);

        }else{

            Toast.makeText(this,"没有移动轨迹", Toast.LENGTH_LONG).show();
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

