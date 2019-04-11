package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OldPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_home);

        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("oldphone");

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
        String phone = intent1.getStringExtra("oldphone");
        intent1.putExtra("oldphone",phone);

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

        //传递参数(手机号、姓名)，用于设置个人信息
        final Intent intent1 = getIntent();
        String phone = intent1.getStringExtra("oldphone");
        String name = intent1.getStringExtra("oldname");
        intent.putExtra("oldphone",phone);
        intent.putExtra("oldname",name);

        startActivity(intent);
        finish();
    }

}
