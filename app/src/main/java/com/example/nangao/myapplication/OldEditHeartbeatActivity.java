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
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class OldEditHeartbeatActivity extends AppCompatActivity {
    private EditText blood;
    private EditText heartbeat;
    String objectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_edit_heartbeat);

        final Intent intent = getIntent();
        objectid = intent.getStringExtra("objectid");
        Log.e("user", "老人所在行的 id:" + objectid);


        blood = findViewById(R.id.blood_edit);
        heartbeat = findViewById(R.id.heart_edit);
    }



    //编辑老人血压和心跳信息的时候，点击"保存"
    public void oldeditsave_skip_home(View view) {
        final String blood1 = blood.getText().toString();
        final String heartbeat1 = heartbeat.getText().toString();


        //当输入框为空的时候，点击保存，做一个提示
        if (TextUtils.isEmpty(blood1) || TextUtils.isEmpty(heartbeat1)) {
            Toast.makeText(OldEditHeartbeatActivity.this, "还没有输入更新的内容", Toast.LENGTH_LONG).show();
        }//确定输入框有数值
        else {

            //表的数组不OK
            MessageManager.getInstance().getMytable();
            MessageManager.getInstance().getMytable().setBlood(blood1);
            MessageManager.getInstance().getMytable().setHeartbeat(heartbeat1);

            MessageManager.getInstance().getMytable().update(objectid, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Intent intent = new Intent();
                        intent.setClass(OldEditHeartbeatActivity.this, OldPageActivity.class);

                        intent.putExtra("blood", blood1);
                        intent.putExtra("heartbeat", heartbeat1);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(OldEditHeartbeatActivity.this, "老人的身体健康参数更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
