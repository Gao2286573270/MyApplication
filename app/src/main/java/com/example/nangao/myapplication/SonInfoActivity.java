package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SonInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_info);

//接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("sonphone");
        String sonpass = intent.getStringExtra("sonpass");
        init(phone,sonpass);
    }


    //接收到登录后的子女信息，给info赋值
    private void init(String phone,String sonpass)
    {
        TextView phonenumber = (TextView)findViewById(R.id.stext_phone);
        TextView password = (TextView)findViewById(R.id.stext_password);

        phonenumber.setText(phone);
        password.setText(sonpass);
    }



}
