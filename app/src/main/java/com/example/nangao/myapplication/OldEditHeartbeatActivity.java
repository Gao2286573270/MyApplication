package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OldEditHeartbeatActivity extends AppCompatActivity {
    private EditText blood;
    private EditText heartbeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_edit_heartbeat);

        blood = findViewById(R.id.blood_edit);
        heartbeat = findViewById(R.id.heart_edit);
    }



    //编辑老人血压和心跳信息的时候，点击"保存"
    public void oldeditsave_skip_home(View view) {
        final String blood1 = blood.getText().toString();
        final String heartbeat1 = heartbeat.getText().toString();

        final Intent intent1 = getIntent();
        final String phone = intent1.getStringExtra("oldphone");

        //当输入框为空的时候，点击登录，做一个提示
        if (TextUtils.isEmpty(blood1) || TextUtils.isEmpty(heartbeat1)) {
            Toast.makeText(OldEditHeartbeatActivity.this, "还没有输入内容", Toast.LENGTH_LONG).show();
        }//确定输入框有数值
        else {
            Intent intent = new Intent();
            intent.setClass(OldEditHeartbeatActivity.this, OldPageActivity.class);
            intent.putExtra("blood", blood1);
            intent.putExtra("heartbeat", heartbeat1);
            startActivity(intent);
            finish();
        }
    }



    //编辑老人血压和心跳信息的时候，点击"取消"
    public void oldeditcancel_skip_home(View view) {
        Intent intent = new Intent();
        intent.setClass(OldEditHeartbeatActivity.this, OldPageActivity.class);
        startActivity(intent);
        finish();
    }
}
