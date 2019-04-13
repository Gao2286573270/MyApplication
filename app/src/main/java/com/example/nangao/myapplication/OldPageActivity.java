package com.example.nangao.myapplication;

import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class OldPageActivity extends AppCompatActivity implements AMapLocationListener{
    //定位需要的参数
    String objectid;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String[] strMsg;
    private String longitude;
    private String latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_home);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("oldphone");
        objectid = intent.getStringExtra("objectid");

        //带bmob的参数表示是从后端云获取的
        String bmobblood = intent.getStringExtra("blood");
        String bmobheartbeat = intent.getStringExtra("heartbeat");
        init(bmobblood,bmobheartbeat);
        Location();

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
                        Toast.makeText(OldPageActivity.this, "定位成功", Toast.LENGTH_LONG).show();
                        Log.e("老人","经度："  + strMsg[1] + "————维度：" + strMsg[2]);

                        longitude = strMsg[1];
                        latitude = strMsg[2];

                        MessageManager.getInstance().getMytable();
                        MessageManager.getInstance().getMytable().setLongitude(longitude);
                        MessageManager.getInstance().getMytable().setLatitude(latitude);

                        MessageManager.getInstance().getMytable().update(objectid, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(OldPageActivity.this, "位置信息更新成功", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(OldPageActivity.this, "位置信息更新失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        // textView.setText("地址：" + strMsg[0] + "\n" + "经    度：" + strMsg[1] + "\n" + "纬    度：" + strMsg[1]);
                    } catch (Exception e) {
                        Toast.makeText(OldPageActivity.this, "定位失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        };

    };

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
            locationOption.setOnceLocation(true);//设置为单次定位
            locationClient.setLocationOption(locationOption);// 给客户端对象设置定位参数
            // 启动定位
            locationClient.startLocation();
            mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
        } catch (Exception e) {
            Toast.makeText(OldPageActivity.this, "定位失败", Toast.LENGTH_LONG).show();
        }
    }














    //接收到登录后的老人信息，给身体健康参数赋值
    private void init(String bmobblood,String bmobheartbeat)
    {
        TextView blood = (TextView)findViewById(R.id.blood_value);
        TextView heartbeat = (TextView)findViewById(R.id.heartbeat_value);

        blood.setText(bmobblood);
        heartbeat.setText(bmobheartbeat);
    }


    public void oldhome_skip_edit_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldEditHeartbeatActivity.class);

        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("oldphone");
        String objectid = intent1.getStringExtra("objectid");
        intent.putExtra("oldphone",phone);
        intent.putExtra("objectid", objectid);
        startActivity(intent);
        finish();
    }

    public void oldhome_skip_secure(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldSecureActivity.class);
        startActivity(intent);
        finish();
    }

    public void oldhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldInfoActivity.class);

        //传递参数(手机号、姓名)，用于设置个人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("oldphone");
        String name = intent1.getStringExtra("oldname");
        intent.putExtra("oldphone",phone);
        intent.putExtra("oldname",name);

        startActivity(intent);
        finish();
    }

}
