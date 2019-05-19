package com.example.nangao.myapplication;

import android.app.ActionBar;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nangao.myapplication.Constant;
import com.example.nangao.myapplication.ChartView;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;



public class OldPageActivity extends AppCompatActivity {

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
        myView.SetInfo(new String[]{"8-11", "11-14", "14-17", "17-20", "20-23",
                        "23-2", "2-5", "5-8"}, // X轴刻度
                new String[]{"50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"}, // Y轴刻度
                new String[]{"123", "139", "134", "120", "117", "113", "126", "138"}, // 高血压数据
                new String[]{"82", "85", "90", "88", "83", "80", "89", "84"}, //低血压数据
                new String[]{"53", "50", "57", "60", "59", "54", "59", "60"}, //心跳
                "身体健康参数曲线");
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