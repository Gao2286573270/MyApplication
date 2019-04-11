package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OldInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_info);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("oldphone");
        String name = intent.getStringExtra("oldname");
        init(phone,name);
    }

    public void oldinfo_skip_secure(View view) {
        Intent intent = new Intent();
        intent.setClass(OldInfoActivity.this,OldSecureActivity.class);
        startActivity(intent);
        finish();
    }

    public void oldinfo_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldInfoActivity.this, OldPageActivity.class);
        startActivity(intent);
        finish();
    }

    private void init(String phone,String name)
    {

        TextView name1 = (TextView)findViewById(R.id.text_name);
        TextView phonenumber = (TextView)findViewById(R.id.text_phone);

        name1.setText(name);
        phonenumber.setText(phone);
    }

}
