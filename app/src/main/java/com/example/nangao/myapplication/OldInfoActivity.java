package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.location.LocationManager;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class OldInfoActivity extends AppCompatActivity implements AMapLocationListener{
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String[] strMsg;
    private String longitude;
    private String latitude;
    String objectid;
    private TextView position;
    int i=0;
    int j =0;
    //String phone;
    List<PositionPoint> track = new ArrayList<PositionPoint>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_info);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String name = intent.getStringExtra("oldname");
        String oldpass = intent.getStringExtra("oldpass");
        objectid = intent.getStringExtra("objectid");

        init(name,oldpass);
        Location();
    }

    //用户名和密码赋值
    private void init(String name,String pass)
    {
        TextView name1 = (TextView)findViewById(R.id.text_name);
        TextView oldpass = (TextView)findViewById(R.id.text_password);

        name1.setText(name);
        oldpass.setText(pass);
    }

    //声明定位回调监听器
    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = Utils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }

    }
    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //定位完成
                case Utils.MSG_LOCATION_FINISH:
                    String result = "";
                    try {
                        AMapLocation loc = (AMapLocation) msg.obj;
                        result = Utils.getLocationStr(loc, 1);
                        strMsg = result.split(",");
                        Toast.makeText(OldInfoActivity.this, "定位成功", Toast.LENGTH_LONG).show();
                        Log.e("老人","经度："  + strMsg[1] + "————维度：" + strMsg[2]);
                        position = (TextView)findViewById(R.id.position);
                        position.setText("地址：" + strMsg[0] + "\n\n" + "经    度：" + strMsg[1] + "\n" + "纬    度：" + strMsg[2]);

                        longitude = strMsg[1];
                        latitude = strMsg[2];

                        MessageManager.getInstance().getMytable();
                        MessageManager.getInstance().getMytable().setLatitude(latitude);
                        MessageManager.getInstance().getMytable().setLongitude(longitude);


                        MessageManager.getInstance().getMytable().add("track",new PositionPoint(latitude,longitude));
                        i=i+1;

                        //正常数组的移位.数组内存5个数，一旦有新的值进来，依次往前挪。（如何控制在同一个位置，却还在不停的上报数据？）
                        if(i>6)
                        {
                            for(j=0;j<5;j++)
                            {
                                track.get(j).setLatitude(track.get(j+1).getLatitude());
                                track.get(j).setLongitude(track.get(j+1).getLongitude());
                            }
                            track.add(new PositionPoint(latitude,longitude));
                        }





                        MessageManager.getInstance().getMytable().update(objectid, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(OldInfoActivity.this, "位置信息更新成功", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(OldInfoActivity.this, "位置信息更新失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(OldInfoActivity.this, "定位失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        };

    };


    //定位函数（设置定位时间间隔等）
    public void Location() {
        // TODO Auto-generated method stub
        try {
            //初始化定位
            locationClient = new AMapLocationClient(this);
            //初始化定位参数
            locationOption = new AMapLocationClientOption();
            // 设置定位模式为低功耗模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            // 设置定位监听
            locationClient.setLocationListener(this);
            locationOption.setOnceLocation(false);
            // 每1min定位一次
            locationOption.setInterval(60 * 1000);
            locationClient.setLocationOption(locationOption);// 给客户端对象设置定位参数
            // 启动定位
            locationClient.startLocation();
            mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
        } catch (Exception e) {
            Toast.makeText(OldInfoActivity.this, "定位失败", Toast.LENGTH_LONG).show();
        }
    }

    public void old_skip_health (View view)
    {
        Intent intent = new Intent();
        intent.setClass(OldInfoActivity.this, OldPageActivity.class);
        startActivity(intent);
    }

}
