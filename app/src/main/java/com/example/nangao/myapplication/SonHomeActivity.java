package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class SonHomeActivity extends AppCompatActivity {
    String oldname;
    String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_home);
        //Bmob.initialize(this,"b4548ed366e8ebe3312dbc064469f99e");

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("sonphone");
        String oldname = intent.getStringExtra("oldname");
        objectid = intent.getStringExtra("objectid");
        init(oldname);
    }

    private void init(String oldname)
    {
        Button toldname = (Button) findViewById(R.id.oldname1);
        toldname.setText(oldname);
    }

//跳转到老人的信息页：血压心跳
    public void sonhome_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonOldInfoActivity.class);

        intent.putExtra("objectid",objectid);

        startActivity(intent);
    }

//跳转到子女的个人信息页，获取首页输入的值
    public void sonhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this, SonInfoActivity.class);

        //传递参数(手机号、邮箱)，用于设置个人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("sonphone");
        String sonpass = intent1.getStringExtra("sonpass");
        intent.putExtra("sonphone",phone);
        intent.putExtra("sonpass",sonpass);

        startActivity(intent);
    }

//跳转到绑定老人页，获取首页输入的值
    public void sonhome_skip_add(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonAddActivity.class);

        //传递参数(手机号、邮箱)，用于绑定老人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("sonphone");
        intent.putExtra("sonphone",phone);

        startActivity(intent);
        finish();   //结束当前页面
    }

}
