package com.example.nangao.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.telephony.SmsManager;
import java.util.List;
import com.example.nangao.myapplication.Constant;
import com.example.nangao.myapplication.ChartView;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


//老人的正常血压范围 140/80   正常心跳55-75/min
public class SonOldHealth extends AppCompatActivity {
    String x[] = new String[]{"8-11", "11-14", "14-17", "17-20", "20-23",
            "23-2", "2-5", "5-8"};// X轴刻度
    String y[] = new String[]{"40","50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"} ;// Y轴刻度
    String hblood[] = new String[]{"123", "139", "134", "142", "117", "113", "126", "138"}; // 高血压数据
    String lblood[] = new String[]{"76", "80", "72", "79", "77", "75", "78", "74"}; //低血压数据
    String heartbeat[] = new String[]{"53", "50", "48", "60", "59", "54", "59", "65"}; //心跳
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 显示App icon左侧的back键 */

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Constant.point = new Point();
        getWindowManager().getDefaultDisplay().getSize(Constant.point);//获取屏幕分辨率

        ChartView myView = new ChartView(this);
        setContentView(myView);

        myView.SetInfo(x, y, hblood, lblood, heartbeat, "身体健康参数曲线");

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}