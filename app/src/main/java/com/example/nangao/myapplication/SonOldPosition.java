package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.amap.api.maps.MapView;
import com.amap.api.maps.AMap;

public class SonOldPosition extends AppCompatActivity {
    MapView mMapView = null;

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


        //初始化地图控制器对象
        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

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
                    if (objectid.equals(bobjectid))
                    {
                        panduan=2;
                        longitude = list.get(i).getLongitude();
                        latitude = list.get(i).getLatitude();
                        //传入经纬度数据，在此页面定位老人的信息

                        break;
                    }
                }
                if(panduan==1)
                {
                    Toast.makeText(SonOldPosition.this, "没有此objectid对应的值", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void position_skip_tracking(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldPosition.this, SonOldTracking.class);
        startActivity(intent);
        finish();
    }

    public void position_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldPosition.this,SonOldInfoActivity.class);
        startActivity(intent);
        finish();
    }
}
