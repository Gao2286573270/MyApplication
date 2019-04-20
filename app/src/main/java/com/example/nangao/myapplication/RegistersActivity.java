package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegistersActivity extends AppCompatActivity {
    private EditText sonphone;
    private EditText sonpassword;
    private EditText ssurepassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_son);

        sonphone = findViewById(R.id.stext_userid);
        sonpassword = findViewById(R.id.stext_userpwd);
        ssurepassword = findViewById(R.id.sstext_userpwd);

    }


    public void register_skip_mian(View view) {
        String phone = sonphone.getText().toString();
        String password = sonpassword.getText().toString();
        String spassword = ssurepassword.getText().toString();

        //当输入框为空的时候，点击注册
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(spassword)) {
            Toast.makeText(RegistersActivity.this, "还没有输入内容", Toast.LENGTH_LONG).show();
        }
        else if(password.equals(spassword))
        {
        //从控制台获取表，上传输入的值
            MessageManager.getInstance().getMytable();
            MessageManager.getInstance().setSonMessage(phone,password);

            MessageManager.getInstance().getMytable().save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {//s插入，会生成唯一的标识
                    if (e != null) {//插入异常
                        Toast.makeText(RegistersActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    } else
                        {//插入成功，跳转回首页
                        Intent intent = new Intent();
                        intent.setClass(RegistersActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        else{
            Toast.makeText(RegistersActivity.this, "两次输入密码不一致", Toast.LENGTH_LONG).show();
            sonpassword.setText("");
            ssurepassword.setText("");
        }
    }
}
