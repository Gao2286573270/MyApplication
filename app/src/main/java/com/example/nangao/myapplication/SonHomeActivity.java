package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class SonHomeActivity extends AppCompatActivity {
    String oldname;
    String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_home);
        //接收传递过来的参数(手机号、邮箱)，用于设置个人信息
        final Intent intent = getIntent();
        String phone = intent.getStringExtra("sonphone");
        String email = intent.getStringExtra("sonemail");
        objectid = intent.getStringExtra("objectid");


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
                        oldname = list.get(i).getOldname();
                        init(oldname);
                        break;
                    }
                }
                if(panduan==1)
                {
                    Toast.makeText(SonHomeActivity.this, "没有此objectid对应的值", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void init(String oldname)
    {

        Button toldname = (Button) findViewById(R.id.oldname1);

        toldname.setText(oldname);

    }


    public void sonhome_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonOldInfoActivity.class);

        intent.putExtra("objectid",objectid);

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
