package com.example.nangao.myapplication;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import android.widget.Toast;


//一个数据对象（APP中创建的BmobObject类的子类）对应于Bmob后台的一个数据表。
public class MyTable extends BmobObject {
    private String oldname;//老人用户名
    private String oldpassword;//老人密码
    private String sonphonenumber;//子女手机号
    private String sonpassword;//子女密码
    private String blood;//老人的血压
    private String heartbeat;//老人的心跳
    private String longitude;   //经度
    private String latitude;    //纬度




    // 仅在客户端使用，不希望被gson序列化提交到后端云，记得用transient修饰
    // (Java序列化是指把Java对象转换为可传输的字节序列的过程；而Java反序列化是指把传输的字节序列恢复为Java对象的过程。这两个过程使我们非常方便的存储和传输数据)
    //private transient Integer count;

    //只有调用注册方法的时候才能自动新增字段。

    //老人数据的相关函数
    public String getOldname()
    {
        return oldname;
    }

    public void setOldname(String oldname)
    {
        this.oldname = oldname;
    }


    public String getOldpassword()
    {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword)
    {
        this.oldpassword = oldpassword;
    }


    public String getSonphonenumber()
    {
        return sonphonenumber;
    }

    public void setSonphonenumber(String sonphonenumber)
    {
        this.sonphonenumber = sonphonenumber;
    }


    public String getSonpassword()
    {
        return sonpassword;
    }

    public void setSonpassword(String sonpassword)
    {
        this.sonpassword = sonpassword;
    }


    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }


    public String getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
