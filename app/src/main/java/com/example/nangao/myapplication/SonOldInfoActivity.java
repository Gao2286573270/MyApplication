package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SonOldInfoActivity extends AppCompatActivity  {
    String objectid;
    String name;
    String oldpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_info);

        final Intent intent = getIntent();//接收从新增页传来的objectid
        objectid = intent.getStringExtra("objectid");
        setvalue(objectid);

    }

    protected void setvalue(final String objectid)
    {
        //根据objectid，获取对应老人的血压值、心跳值
        MessageManager.getInstance().getMytable();

        final BmobQuery<MyTable> Query = new BmobQuery<MyTable>();
        Query.findObjects(new FindListener<MyTable>() {
            @Override
            public void done(List<MyTable> list, BmobException e) {
                int panduan = 1;
                for (int i = 0; i < list.size(); i++) {
                    String bobjectid = list.get(i).getObjectId();
                    if (bobjectid.equals(objectid))
                    {
                        panduan=2;
                        name = list.get(i).getOldname();
                        oldpass = list.get(i).getOldpassword();
                        init(name,oldpass);
                        break;
                    }
                }
                if(panduan==1)
                {
                    Toast.makeText(SonOldInfoActivity.this, "没有此objectid对应的值", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


  //给老人的信息赋值
    private void init(String name,String oldpass)
    {
        TextView tname = (TextView)findViewById(R.id.blood_value);
        TextView toldpass = (TextView)findViewById(R.id.heartbeat_value);

        tname.setText(name);
        toldpass.setText(oldpass);
    }

    public void oldinfo_skip_health(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldInfoActivity.this, SonOldHealth.class);
        startActivity(intent);
    }


    public void oldinfo_skip_positon(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldInfoActivity.this, SonOldPosition.class);
        intent.putExtra("objectid",objectid);
        startActivity(intent);
    }

    public void oldinfo_skip_tracking(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldInfoActivity.this,SonOldTracking.class);
        intent.putExtra("objectid",objectid);
        startActivity(intent);
    }
}
