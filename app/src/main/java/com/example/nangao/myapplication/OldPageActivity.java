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



import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;



public class OldPageActivity extends AppCompatActivity {
    //定位需要的参数
    String objectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_home);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();

        //带bmob的参数表示是从后端云获取的
        String bmobblood = intent.getStringExtra("blood");
        String bmobheartbeat = intent.getStringExtra("heartbeat");
        init(bmobblood,bmobheartbeat);

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
        String objectid = intent1.getStringExtra("objectid");
        //传递参数(手机号、姓名)，用于设置个人信息
        String oldname = intent1.getStringExtra("oldname");
        String oldpassword = intent1.getStringExtra("oldpass");

        intent.putExtra("oldname",oldname);
        intent.putExtra("oldpass",oldpassword);
        intent.putExtra("objectid", objectid);
        startActivity(intent);
        finish();
    }

    public void oldhome_skip_secure(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldSecureActivity.class);
        startActivity(intent);
    }

    public void oldhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldInfoActivity.class);

        //传递参数(手机号、姓名)，用于设置个人信息
        final Intent intent1 = getIntent();
        String name = intent1.getStringExtra("oldname");
        String oldpass = intent1.getStringExtra("oldpass");
        String objectid = intent1.getStringExtra("objectid");

        intent.putExtra("oldname",name);
        intent.putExtra("oldpass",oldpass);
        intent.putExtra("objectid",objectid);

        startActivity(intent);
    }

}
