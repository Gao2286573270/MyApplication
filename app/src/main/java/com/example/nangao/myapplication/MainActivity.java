package com.example.nangao.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class MainActivity extends AppCompatActivity {

    private RadioButton oldRb;//单选按钮
    private RadioButton sonRb;
    private Button loginBt;
    private Button registerBt;
    private int judgeold = 1;
    private EditText phoneNums;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"b4548ed366e8ebe3312dbc064469f99e");

        initView();

        MyTable mytable = new MyTable();
        mytable.setOldname("oldname");
        mytable.setOldphonenumber("oldphone");
        mytable.setOldpassword("oldpassword");
        mytable.setSonemail("email");
        mytable.setSonphonenumber("sonphone");
        mytable.setSonpassword("sonpassword");


        //bmobSDK对云端数据库的操作是异步回调
        mytable.save(new SaveListener<String>()
        {
            @Override
            public void done(String s, BmobException e)
            {//s插入，会生成唯一的标识
                if (e != null) {//插入异常
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "succsee", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        private void initView () {
            //初始化按钮对象(单选按钮)
            RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioButton);
            loginBt = (Button) findViewById(R.id.login);
            registerBt = (Button) findViewById(R.id.register);
            phoneNums = findViewById(R.id.text_userid);//app首页的输入框
            password = findViewById(R.id.text_userpwd);


            //给按钮对象设置监听器，如果选择老人，标签设为1
            radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    if (checkedId == R.id.old) {
                        //Toast.makeText(MainActivity.this,"old",Toast.LENGTH_LONG).show();
                        judgeold = 1;
                    } else {
                        //Toast.makeText(MainActivity.this,"son",Toast.LENGTH_LONG).show();
                        judgeold = 2;
                    }
                }
            });

        }

        //根据身份的不同，跳转到不同的登录页面
        public void main_skip_oldpage (View view)
        {
            if (judgeold == 1)
            {
                String num = phoneNums.getText().toString();
                String pass = password.getText().toString();

                //当输入框为空的时候，点击登录，
                if (TextUtils.isEmpty(num) || TextUtils.isEmpty(pass)) {

                    //toast

                } else
                  {

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, OldPageActivity.class);
                    startActivity(intent);
                }
            } else if (judgeold == 2)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SonHomeActivity.class);
                startActivity(intent);
            }

        }

        //只需要子女注册
        public void main_skip_registero (View view)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RegistersActivity.class);
            startActivity(intent);
        }


    }
