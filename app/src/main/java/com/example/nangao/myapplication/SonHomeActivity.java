package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_home);
        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("sonphone");
        String email = intent.getStringExtra("sonemail");
    }



    public void sonhome_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonOldInfoActivity.class);
        startActivity(intent);
    }

    //获取首页输入的值
    public void sonhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this, SonInfoActivity.class);

        //传递参数(手机号、邮箱)，用于设置个人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("sonphone");
        String email = intent1.getStringExtra("sonemail");
        intent.putExtra("sonphone",phone);
        intent.putExtra("sonemail",email);

        startActivity(intent);
    }

    public void sonhome_skip_add(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonAddActivity.class);

        //传递参数(手机号、邮箱)，用于绑定老人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("sonphone");
        intent.putExtra("sonphone",phone);

        startActivity(intent);
    }

}
