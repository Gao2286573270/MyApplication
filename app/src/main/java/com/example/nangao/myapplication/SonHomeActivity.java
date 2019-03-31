package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_home);
    }

    public void sonhome_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonOldInfoActivity.class);
        startActivity(intent);
    }

    public void sonhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this, SonInfoActivity.class);
        startActivity(intent);
    }

    public void sonhome_skip_add(View view) {
        Intent intent = new Intent();
        intent.setClass(SonHomeActivity.this,SonAddActivity.class);
        startActivity(intent);
    }

}
