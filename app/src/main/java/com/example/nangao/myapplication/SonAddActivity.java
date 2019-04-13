package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class SonAddActivity extends AppCompatActivity {
    private EditText oldname;
    private EditText oldphone;
    private EditText oldpassword;
    private Button sure;
    String son_phone;
    String default_blood;
    String default_heartbeat;
    String default_longitude;
    String default_latitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_add_old);

        //接收传递过来的参数(手机号、邮箱)，用于设置绑定老人的信息
        final Intent intent = getIntent();
        son_phone = intent.getStringExtra("sonphone");
        String i = intent.getStringExtra("order");

        oldname = findViewById(R.id.text_oldname);
        oldphone = findViewById(R.id.text_oldphone);
        oldpassword = findViewById(R.id.text_oldpassword);

        default_blood = String.valueOf(60);   //默认的血压值
        default_heartbeat = String.valueOf(60);   //默认的心跳值
        default_latitude = String.valueOf(0.0);//默认的经度
        default_longitude = String.valueOf(0.0);//默认的纬度

    }

    public void bind_skip_sonhome(View view) {
        final String name = oldname.getText().toString();//取输入的值
        final String phone = oldphone.getText().toString();
        final String password = oldpassword.getText().toString();

        //当输入框为空的时候，点击确认绑定
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
        {
            Toast.makeText(SonAddActivity.this, "还没有输入内容", Toast.LENGTH_LONG).show();
        }
        else {
            //从控制台获取表，上传输入的值
            MessageManager.getInstance().getMytable();

            BmobQuery<MyTable> query=new BmobQuery<MyTable>();
            query.findObjects(new FindListener<MyTable>() {
                @Override
                public void done(List<MyTable> list, BmobException e) {
                    int panduan=1;
                    for(int i=0;i<list.size();i++){
                        String phonenumber=list.get(i).getSonphonenumber();

                        Log.e("user","唯一 id:"+list.get(i).getObjectId()+"----"+phonenumber);

                        if(phonenumber.equals(son_phone)){
                            Toast.makeText(SonAddActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                            panduan=2;
                            //成功后panduan等于2,则跳出该循环,并且把输入快都清空,跳转到指定页面

                            //生成老人信息列（姓名、手机号、密码、血压心跳、经纬度）
                            list.get(i).setOldname(name);
                            list.get(i).setOldphonenumber(phone);
                            list.get(i).setOldpassword(password);
                            list.get(i).setBlood(default_blood);
                            list.get(i).setHeartbeat(default_heartbeat);
                            list.get(i).setLongitude(default_longitude);
                            list.get(i).setLatitude(default_latitude);


                            list.get(i).save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e != null) {//插入异常
                                        Toast.makeText(SonAddActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                    } else
                                    {//插入成功，跳转回首页
                                        Intent intent = new Intent();
                                        intent.setClass(SonAddActivity.this, SonHomeActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                            break;
                        }
                    }
                    if(panduan==1){
                        Toast.makeText(SonAddActivity.this, "绑定失败", Toast.LENGTH_SHORT).show();
                        //登录失败，把输入快都清空,跳转到指定页面
                       /* phoneNums.setText("");
                        password.setText("");*/

                    }

                }
            });
        }
    }
}