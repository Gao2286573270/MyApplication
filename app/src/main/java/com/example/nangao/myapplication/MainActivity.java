package com.example.nangao.myapplication;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.nangao.myapplication.MyTable;


import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
                        judgeold = 1;
                    } else {
                        judgeold = 2;
                    }
                }
            });

        }

        //根据身份的不同，跳转到不同的登录页面
        public void main_skip_oldpage (View view) {
            final String phone1 = phoneNums.getText().toString();
            final String pass1 = password.getText().toString();

            //当输入框为空的时候，点击登录，做一个提示
            if (TextUtils.isEmpty(phone1) || TextUtils.isEmpty(pass1)) {
                Toast.makeText(MainActivity.this, "还没有输入内容", Toast.LENGTH_LONG).show();
            }//确定输入框有数值
            else {
                MessageManager.getInstance().getMytable();

                BmobQuery<MyTable> query2 = new BmobQuery<MyTable>();
                query2.findObjects(new FindListener<MyTable>()
                {
                    @Override
                    public void done(List<MyTable> list, BmobException e) {
                        int panduan = 1;
                        for (int i = 0; i < list.size(); i++) {
                            String sonnumber = list.get(i).getSonphonenumber();
                            String sonpass = list.get(i).getSonpassword();
                            String oldname = list.get(i).getOldname();
                            String oldpass = list.get(i).getOldpassword();

                            Log.e("user", "唯一 id:" + list.get(i).getObjectId() + "----" + sonnumber + "---" + sonpass);

                            if (sonnumber.equals(phone1) && sonpass.equals(pass1) && (judgeold == 2)) {
                                Toast.makeText(MainActivity.this, "子女登录成功", Toast.LENGTH_SHORT).show();
                                panduan = 2;
                                //成功后panduan等于2,则跳出该循环,并且把输入快都清空,跳转到指定页面
                                phoneNums.setText("");
                                password.setText("");
                                String objectid  =  list.get(i).getObjectId();

                                //传递参数(手机号、邮箱)，用于设置个人信息
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, SonHomeActivity.class);
                                intent.putExtra("sonphone", phone1);
                                intent.putExtra("sonpass",sonpass);
                                intent.putExtra("oldname",oldname);
                                intent.putExtra("objectid", objectid);//对应的行数
                                startActivity(intent);
                                break;
                            }
                            if(phone1.equals(oldname) && pass1.equals(oldpass) && (judgeold == 1))
                            {
                                Toast.makeText(MainActivity.this, "老人登录成功", Toast.LENGTH_SHORT).show();
                                panduan = 2;

                                //成功后panduan等于2,则跳出该循环,并且把输入快都清空,跳转到指定页面
                                phoneNums.setText("");
                                password.setText("");
                                String name = list.get(i).getOldname();
                                String blood = list.get(i).getBlood();
                                String heartbeat = list.get(i).getHeartbeat();
                                String objectid  =  list.get(i).getObjectId();
                                Log.e("user", "老人所在行的 id:" + list.get(i).getObjectId());


                                //传递参数(手机号、邮箱)，用于设置个人信息
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, OldPageActivity.class);
                                intent.putExtra("oldname", name);
                                intent.putExtra("oldpass",oldpass);
                                intent.putExtra("blood", blood);
                                intent.putExtra("heartbeat", heartbeat);
                                intent.putExtra("objectid", objectid);//对应的行数
                                startActivity(intent);
                                break;
                            }

                        }
                        if(panduan==1){
                            Toast.makeText(MainActivity.this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                            //登录失败，把输入框都清空,跳转到指定页面
                            phoneNums.setText("");
                            password.setText("");
                        }
                    }
                });

            }
        }

        //只需要子女注册
        public void main_skip_registers (View view)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RegistersActivity.class);
            startActivity(intent);
        }
}
