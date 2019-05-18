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


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.listener.UpdateListener;



public class RegistersActivity extends AppCompatActivity {
    private EditText sonphone;
    private EditText sonpassword;
    private EditText ssurepassword;
    private EditText surecode;
    private Button sure;
    String phone;
    String password;
    String spassword;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_son);

        sonphone = findViewById(R.id.stext_userid);
        sonpassword = findViewById(R.id.stext_userpwd);
        ssurepassword = findViewById(R.id.sstext_userpwd);
        surecode = findViewById(R.id.stext_sure);
        sure = findViewById(R.id.sure);
    }
//短信验证，发送短信
    public void register_sure(View view){
        phone = sonphone.getText().toString();
            BmobSMS.requestSMSCode(phone, "register", new QueryListener<Integer>() {
                @Override
                public void done(Integer smsId, BmobException e) {
                    if (e == null) {
                        Toast.makeText(RegistersActivity.this, "发送验证码成功，短信ID：" + smsId + "\n", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistersActivity.this, "发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    public void register_skip_mian(View view) {
        phone = sonphone.getText().toString();
        password = sonpassword.getText().toString();
        spassword = ssurepassword.getText().toString();
        code = surecode.getText().toString();

        //当输入框为空的时候，点击注册
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(spassword)) {
            Toast.makeText(RegistersActivity.this, "还没有输入内容", Toast.LENGTH_LONG).show();
        }
        else if(password.equals(spassword))
        {
        //从控制台获取表，上传输入的值
            BmobQuery<MyTable> query2 = new BmobQuery<MyTable>();
            query2.findObjects(new FindListener<MyTable>()
            {
                @Override
                public void done(List<MyTable> list, BmobException e) {
                    for (int i = 0; i < list.size(); i++) {
                        //判断用户是否已存在
                        if (list.get(i).getSonphonenumber() == phone) {
                            Toast.makeText(RegistersActivity.this, "此用户已存在，请重新输入", Toast.LENGTH_LONG).show();
                            sonpassword.setText("");
                            ssurepassword.setText("");
                        } else
                         {
                             BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
                                 @Override
                                 public void done(BmobException e) {
                                     if (e == null) {
                                         Toast.makeText(RegistersActivity.this, "手机号验证成功", Toast.LENGTH_LONG).show();
                                         MessageManager.getInstance().getMytable();
                                         MessageManager.getInstance().setSonMessage(phone, password);

                                         MessageManager.getInstance().getMytable().save(new SaveListener<String>() {
                                             @Override
                                             public void done(String s, BmobException e) {//s插入，会生成唯一的标识
                                                 if (e != null) {//插入异常
                                                     Toast.makeText(RegistersActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                     } else {//插入成功，跳转回首页
                                                        Intent intent = new Intent();
                                                        intent.setClass(RegistersActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                                finish();
                                                             }
                                                         }
                                                     });
                                     }
                                     else {
                                         Toast.makeText(RegistersActivity.this, "验证失败", Toast.LENGTH_LONG).show();
                                     }
                                 }
                             });
                        }
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
