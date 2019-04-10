package com.example.nangao.myapplication;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MessageManager {

    private MyTable mytable;

    private static MessageManager instance;

    private MessageManager(){
        mytable = new MyTable();
    }

    public static MessageManager getInstance(){
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    //上传输入的值（子女的信息）
    public void setSonMessage(String sonphonenumber,String sonemail,String sonpassword)
    {
        mytable.setSonphonenumber(sonphonenumber);
        mytable.setSonemail(sonemail);
        mytable.setSonpassword(sonpassword);
    }

    //上传老人的信息
    public void setOldMessage(String oldname,String oldphone,String oldpassword)
    {
        mytable.setOldname(oldname);
        mytable.setOldphonenumber(oldphone);
        mytable.setOldpassword(oldpassword);
    }



    public MyTable getMytable(){
        return mytable;
    }
}