package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OldPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_home);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("oldphone");
        String name = intent.getStringExtra("oldname");
    }

    public void oldhome_skip_edit_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldEditHeartbeatActivity.class);
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

        //传递参数(手机号、邮箱)，用于设置个人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("oldphone");
        String name = intent1.getStringExtra("oldname");
        intent.putExtra("oldphone",phone);
        intent.putExtra("oldname",name);

        startActivity(intent);
        finish();
    }

}
